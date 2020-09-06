package com.xiaofu.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xiaofu.domain.enums.ResponseEnum;
import com.xiaofu.domain.response.ResultInfo;
import com.xiaofu.util.JWTUtils;
import com.xiaofu.util.okhttp.OkHttpCli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Created by jinchengll
 * @Date 2020/9/6 16:14
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private static String APPID = "1108100302";
    private static String SECRET = "iKnid6CcpM1mvXvh";
    private static String GRANT_TYPE = "authorization_code";
    private static String CODE2SESSION_URL = "https://api.q.qq.com/sns/jscode2session";

    @Autowired
    OkHttpCli okHttpCli;

    /**
     * 请求qq的登陆API获得openid和session_key，验证用户合理性。
     * 如果成功就构造token返回客户端
     *
     * @param code 前端code码
     * @return token
     */
    @GetMapping("/getToken")
    public ResultInfo<Object> getToken(String code) {
        ResultInfo<Object> resultInfo = null;
        if (StringUtils.isEmpty(code)) {
            resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, "没有code参数");
        } else {
            //封装code2session的参数
            Map<String, Object> params = new HashMap<>();
            params.put("appid", APPID);
            params.put("secret", SECRET);
            params.put("js_code", code);
            params.put("grant_type", GRANT_TYPE);
            //使用qq提供的api去验证code的正确性
            JSONObject resultJson = okHttpCli.doGetParams(CODE2SESSION_URL, params);
            if (resultJson.getIntValue("errcode") == 0) {
                //请求成功，得到openid和session_key
                String openId = resultJson.getString("openid");
                String sessionKey = resultJson.getString("session_key");
                //生成token
                Map<String, String> map = new HashMap<>();
                map.put("openid", openId);
                String token = JWTUtils.getToken(map);
                //组装最后结果
                Map<String, String> results = new HashMap<>();
                results.put("token", token);
                results.put("session_key", sessionKey);
                resultInfo = new ResultInfo<>(ResponseEnum.SUCCESS, results);
            } else {
                //返回不为0，请求失败
                resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, resultJson.getString("errmsg"));
            }
        }
        return resultInfo;
    }

    /**
     * 验证token的正确性
     *
     * @param token 前端传过来的token
     * @return 验证结果
     */
    @GetMapping("/verifyToken")
    public ResultInfo<String> verifyToken(String token) {
        ResultInfo<String> resultInfo = null;
        try {
            DecodedJWT verify = JWTUtils.verify(token);
            resultInfo = new ResultInfo<>(ResponseEnum.SUCCESS, "token验证通过");
        } catch (SignatureVerificationException e) {
            resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, "token签名无效");
        } catch (TokenExpiredException e) {
            resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, "token过期");
        } catch (AlgorithmMismatchException e) {
            resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, "token算法不一致");
        } catch (Exception e) {
            resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, "token无效");
        }
        return resultInfo;
    }

}
