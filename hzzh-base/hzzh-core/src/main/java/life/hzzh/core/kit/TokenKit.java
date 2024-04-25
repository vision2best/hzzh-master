package life.hzzh.core.kit;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.NonNull;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author ZHANG HUANG
 * 2024/3/1 17:34
 */
public class TokenKit {
    // 秘钥
    private static final String KEY_SECRET = "hzzh@123456#";

    private static final String SUBJECT = "hzzh";

    private static final String CLAIM_KEY_USERID = "userId";

    /**
     * 生成 token 长期有效
     *
     * @param userId 用户 ID
     * @return token
     */
    public static String generateToken(@NonNull Long userId) {
        Algorithm algorithm = Algorithm.HMAC256(KEY_SECRET);
        return JWT.create()
                .withSubject(SUBJECT)
                .withClaim(CLAIM_KEY_USERID, userId)
                .sign(algorithm);

    }

    /**
     * 生成 token 有效期
     *
     * @param userId  用户 ID
     * @param seconds 有效期(秒)
     * @return token
     */
    public static String generateToken(@NonNull Object userId, int seconds) {
        Algorithm algorithm = Algorithm.HMAC256(KEY_SECRET);
        Date expiresAt = DateUtil.offsetSecond(new Date(), seconds);
        return JWT.create()
                .withSubject(SUBJECT)
                .withExpiresAt(expiresAt)
                .withPayload(String.valueOf(userId))
                .sign(algorithm);
    }


    /**
     * 解析 token
     *
     * @param token token
     * @return userId
     */
    public static Object parseToken(@NonNull String token) {
        DecodedJWT jwt = JWT.decode(token);
        String subject = jwt.getSubject();
        String header = jwt.getHeader();
        String payload = jwt.getPayload();
        Map<String, Claim> claims = jwt.getClaims();
        String signature = jwt.getSignature();
        if (!CollectionUtils.isEmpty(claims) && Objects.equals(SUBJECT, subject)) {
            return claims.get(CLAIM_KEY_USERID);
        }
        return null;
    }


    public static Boolean validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(KEY_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
        try {
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
