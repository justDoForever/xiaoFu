package com.xiaofu.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaofu.domain.enums.ResponseEnum;
import com.xiaofu.domain.response.ResultInfo;
import com.xiaofu.util.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author jinchengll
 * @Date 2020/9/6 7:54 下午
 */
public class JWTInterceptor implements HandlerInterceptor {
    /**
     * 在这里验证token的正确性，通过返回true，失败返回false
     *
     * @param request  请求
     * @param response 响应
     * @param handler  handler
     * @return 是否通行
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //结果封装
        ResultInfo<String> resultInfo = null;
        //从请求头中获得token
        String token = request.getHeader("token");
        //开始验证
        try {
            JWTUtils.verify(token);
            return true;
        } catch (SignatureVerificationException e) {
            resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, "token签名无效");
        } catch (TokenExpiredException e) {
            resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, "token过期");
        } catch (AlgorithmMismatchException e) {
            resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, "token算法不一致");
        } catch (Exception e) {
            resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, "token无效");
        }
        //将结果转成json，并返回给界面
        String resultJson = new ObjectMapper().writeValueAsString(resultInfo);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(resultJson);
        return false;
    }
}
