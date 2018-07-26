package io.exhub.exhub_manager.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

/**
 * token factory
 * @date 2018/7/26
 * @author
 */
public class TokenFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenFactory.class);

    /**
     * 定义secret
     */
    private static final String SECRET = "session_secret";
    /**
     * 定义发布者
     */
    private static final String ISSUER = "hei_li_user";

    /**
     * 获取token
     *
     * @return
     */
    public static String getToken(Map<String, String> claims) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTCreator.Builder builder = JWT.create()
                    .withIssuer(ISSUER)
                    .withExpiresAt(DateUtils.addDays(new Date(), 1));
            //将K，V存入
            claims.forEach((K, V) -> builder.withClaim(K, V));
            return builder.sign(algorithm).toString();
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            LOGGER.error("Invalid Signing configuration / Couldn't convert Claims.");
            throw new RuntimeException("Invalid Signing configuration / Couldn't convert Claims.");
        }
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public static Map<String, String> verifyToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            //获取存值并准换
            Map<String, Claim> claims = jwt.getClaims();
            Map<String, String> result = Maps.newHashMap();
            claims.forEach((K, V) -> result.put(K, V.asString()));
            return result;
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            LOGGER.error("Invalid signature/claims");
            return null;
        }
    }

}
