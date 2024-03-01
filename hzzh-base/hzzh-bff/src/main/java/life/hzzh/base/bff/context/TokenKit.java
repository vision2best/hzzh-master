package life.hzzh.base.bff.context;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author ZHANG HUANG
 * 2024/3/1 17:34
 */
public class TokenKit {
    // 秘钥
    private static final String secret = "hzzh@123456#";

    public static String generateToken(HeaderContext.HeaderData headerData) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("tenantId", headerData.getTenantId()) // 租户 ID
                .withClaim("userId", headerData.getUserId()) // 用户 ID
                .withClaim("roles", Collections.singletonList(headerData.getRoles())) //角色
                .withClaim("loginAt", headerData.getLoginAt()) // 登录时间戳
                .sign(algorithm);

    }

    public static HeaderContext.HeaderData parseToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        String header = jwt.getHeader();
        String payload = jwt.getPayload();
        String signature = jwt.getSignature();
        // 获取Payload中的数据
        Map<String, Claim> claims = jwt.getClaims();    //Key is the Claim name
        String tenantId = claims.get("tenantId").asString();
        String userId = claims.get("userId").asString();
        List<String> roles = claims.get("roles").asList(String.class);
        Long loginAt = claims.get("loginAt").asLong();
        HeaderContext.HeaderData headerData = new HeaderContext.HeaderData();
        headerData.setToken(token);
        headerData.setTenantId(tenantId);
        headerData.setUserId(userId);
        headerData.setRoles(roles);
        headerData.setLoginAt(loginAt);
        return headerData;
    }


    public static Boolean validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
        try {
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
