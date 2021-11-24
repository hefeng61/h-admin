package top.hf.hadmin.util;

import cn.hutool.core.util.IdUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

/**
 * @Author hefeng
 * @Description token工具类
 * @Date 2021/11/19 14:18
 */
@Component
public class TokenUtil {

    private static final String CLAIM = "user";

    public Algorithm algorithm() {
        return Algorithm.HMAC256("fsdlkjlfs0");
    }

    /**
     * @description token 生成
     * @date 2021/11/19 14:44
     * @param name
     * @return java.lang.String
     */
    public String createToken(String name) {
        return JWT.create()
                .withJWTId(IdUtil.objectId())
                .withClaim(CLAIM, name)
                .sign(algorithm());
    }

    /**
     * @description token 解析
     * @date 2021/11/19 14:43
     * @param token
     * @return java.lang.String
     */
    public String parseToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm())
                .build();
        DecodedJWT verify = verifier.verify(token);
        return verify.getClaim(CLAIM).asString();
    }

}
