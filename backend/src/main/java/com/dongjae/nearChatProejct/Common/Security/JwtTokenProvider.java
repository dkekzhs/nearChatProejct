package com.dongjae.nearChatProejct.Common.Security;


import com.dongjae.nearChatProejct.Common.Error.Exception.jwt.WrongTokenException;
import com.dongjae.nearChatProejct.User.Service.UserService;
import com.dongjae.nearChatProejct.User.domain.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${jwt.token.key}")
    private String SECRET_KEY;

    public String CreateToken(UserEntity user) {
        Date time = Date.from(Instant.now().plus(30, ChronoUnit.MINUTES));

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setSubject(String.valueOf(user.getId()))
                .setIssuer("core")
                .setIssuedAt(new Date())
                .setExpiration(time)
                .compact();
    }
    public Long validateAndGetMember(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }


}
