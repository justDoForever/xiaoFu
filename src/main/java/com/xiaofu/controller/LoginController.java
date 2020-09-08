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
import io.swagger.annotations.ApiOperation;
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

    private static final String APPID = "1110779203";
    private static final String SECRET = "iKnid6CcpM1mvXvh";
    private static final String GRANT_TYPE_SESSION = "authorization_code";
    private static final String CODE2SESSION_URL = "https://api.q.qq.com/sns/jscode2session";
    private static final String ACCESS_TOKEN_URL = "https://api.q.qq.com/api/getToken";
    private static final String GRANT_TYPE_TOKEN = "client_credential";

    @Autowired
    OkHttpCli okHttpCli;

    /**
     * 请求qq的登陆API获得openid和session_key，验证用户合理性。
     *
     * @param code 前端code码
     * @return openid和session_key
     */
    @ApiOperation(value = "获取token", notes = "获取token", produces = "application/json")
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
            params.put("grant_type", GRANT_TYPE_SESSION);
            //使用qq提供的api去验证code的正确性
            JSONObject resultJson = okHttpCli.doGetParams(CODE2SESSION_URL, params);
            if (resultJson.getIntValue("errcode") == 0) {
                //请求成功，得到openid和session_key
                Map<String, String> results = new HashMap<>();
                results.put("openid", resultJson.getString("openid"));
                results.put("session_key", resultJson.getString("session_key"));
                resultInfo = new ResultInfo<>(ResponseEnum.SUCCESS, results);
            } else {
                //返回不为0，请求失败
                resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, resultJson.getString("errmsg"));
            }
        }
        return resultInfo;
    }

    /**
     * getAccessToken接口，在这里直接访问qq的api获得AccessToken返回给前端
     * 前端会感知到AccessToken失效，并访问此接口从新获取
     *
     * @return AccessToken
     */
    @ApiOperation(value = "获取AccessToken", notes = "获取AccessToken", produces = "application/json")
    @GetMapping("/getAccessToken")
    public ResultInfo<Object> getAccessToken() {
        ResultInfo<Object> resultInfo = null;
        //封装访问getAccessToken的参数
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", GRANT_TYPE_TOKEN);
        params.put("appid", APPID);
        params.put("secret", SECRET);
        //使用okhttp发送请求
        JSONObject resultJson = okHttpCli.doGetParams(ACCESS_TOKEN_URL, params);
        if (resultJson.getIntValue("errcode") == 0) {
            //请求成功，得到 access_token 和 expires_in
            Map<String, String> results = new HashMap<>();
            results.put("access_token", resultJson.getString("access_token"));
            results.put("expires_in", resultJson.getString("expires_in"));
            resultInfo = new ResultInfo<>(ResponseEnum.SUCCESS, results);
        } else {
            //返回不为0，请求失败
            resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, resultJson.getString("errmsg"));
        }
        return resultInfo;
    }

    /**
     * 验证token的正确性
     *
     * @param token 前端传过来的token
     * @return 验证结果
     */
    @ApiOperation(value = "验证token", notes = "验证token", produces = "application/json")
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
