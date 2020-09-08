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
     * 请求qq的登陆API获得openid和session_key，验证用户合理性，并同时获取AccessToken返回。
     *
     * @param code 前端code码
     * @return openid和session_key以及AccessToken
     */
    @ApiOperation(value = "获取AccessToken", notes = "获取AccessToken", produces = "application/json")
    @GetMapping("/getAccessToken")
    public ResultInfo<Object> getAccessToken(String code) {
        ResultInfo<Object> resultInfo = null;
        if (StringUtils.isEmpty(code)) {
            resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, "没有code参数");
        } else {
            //请求code2session
            JSONObject responseSession = getSessionFromQQ(code);
            int errcodeSession = responseSession.getIntValue("errcode");
            //请求getAccessToken
            JSONObject responseAccessToken = getAccessTokenFromQQ();
            int errcodeAccessToken = responseAccessToken.getIntValue("errcode");
            if (errcodeSession == 0 && errcodeAccessToken == 0) {
                //请求成功，得到openid和session_key以及AccessToken
                Map<String, String> results = new HashMap<>();
                results.put("openid", responseSession.getString("openid"));
                results.put("session_key", responseSession.getString("session_key"));
                results.put("access_token", responseAccessToken.getString("access_token"));
                resultInfo = new ResultInfo<>(ResponseEnum.SUCCESS, results);
            } else {
                //返回不为0，请求失败，封装错误信息
                //如果两个都请求失败，只返回AccessToken的错误信息
                String errmsg;
                if (errcodeSession != 0) {
                    errmsg = responseSession.getString("errmsg");
                } else {
                    errmsg = responseAccessToken.getString("errmsg");
                }
                resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, errmsg);
            }
        }
        return resultInfo;
    }

    /**
     * 请求qq的code2session接口
     *
     * @param code js_code
     * @return 请求结果
     */
    private JSONObject getSessionFromQQ(String code) {
        //封装code2session的参数
        Map<String, Object> params = new HashMap<>();
        params.put("appid", APPID);
        params.put("secret", SECRET);
        params.put("js_code", code);
        params.put("grant_type", GRANT_TYPE_SESSION);
        //使用qq提供的api去验证code的正确性
        return okHttpCli.doGetParams(CODE2SESSION_URL, params);
    }

    /**
     * 访问qq的getAccessToken接口
     *
     * @return 请求结果
     */
    private JSONObject getAccessTokenFromQQ() {
        //封装访问getAccessToken的参数
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", GRANT_TYPE_TOKEN);
        params.put("appid", APPID);
        params.put("secret", SECRET);
        return okHttpCli.doGetParams(ACCESS_TOKEN_URL, params);
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
