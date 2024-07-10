package com.jxh.lease.common.utils.jwt;

import com.jxh.lease.common.result.ResultCodeEnum;
import com.jxh.lease.common.exception.LeaseException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * ClassName: JwtUtil
 * Package: com.jxh.lease.common.utils
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/9 15:59
 */
@Component
@EnableConfigurationProperties(JwtProperties.class)
public class JwtUtil {

    private final SecretKey tokenSignKey;

    private final JwtProperties jwtProperties;

    public JwtUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        tokenSignKey = Keys.hmacShaKeyFor(jwtProperties.getSignKey().getBytes());
    }

    public String createToken(Long userId, String username) {
        return Jwts.builder()
                .subject("USER_INFO")
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration().toMillis()))
                .claim("userId", userId)
                .claim("username", username)
                .signWith(tokenSignKey)
                .compact();
    }

    public Claims parseToken(String token) {
        if (token == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }
        try {
            JwtParser jwtParser = Jwts.parser().verifyWith(tokenSignKey).build();
            return jwtParser.parseSignedClaims(token).getPayload();
        } catch (ExpiredJwtException e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
        } catch (JwtException e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }
    }

}
