package com.xiaofu.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @Author jinchengll
 * @Date 2020/9/6 6:51 下午
 */
public class JWTUtils {
    //token生成密钥
    private static String SING = "token!=k(2+68*¥GOInf";

    /**
     * 生成token
     *
     * @param map 要放入token的payload
     * @return token字符串
     */
    public static String getToken(Map<String, String> map) {
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim); //放入payload信息
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7); //默认七天过期
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(SING));
    }

    /**
     * 验证和读取token中的payload
     *
     * @param token token
     * @return 包含payload的信息
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }


}
