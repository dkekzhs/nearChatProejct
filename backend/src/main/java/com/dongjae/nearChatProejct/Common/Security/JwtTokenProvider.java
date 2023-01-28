package com.dongjae.nearChatProejct.Common.Security;


import com.dongjae.nearChatProejct.Common.Error.Exception.ErrorCode;
import com.dongjae.nearChatProejct.Common.Error.Exception.UnauthorizedRequestException;
import com.dongjae.nearChatProejct.Common.Error.Exception.jwt.ExpiredTokenException;
import com.dongjae.nearChatProejct.Common.Error.Exception.jwt.InvalidTokenException;
import com.dongjae.nearChatProejct.Common.Error.Exception.jwt.WrongTokenException;
import com.dongjae.nearChatProejct.User.Service.UserService;
import com.dongjae.nearChatProejct.User.domain.UserEntity;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.h2.server.web.JakartaWebServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
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


    public boolean validateToken(String token) {
        return this.getClaims(token) != null;
    }

    private Jws<Claims> getClaims(String token) {
        Jws<Claims> claimsJws =null;
        try{
            claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        }
        catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (UnsupportedJwtException | IllegalArgumentException e) {
            throw new InvalidTokenException();
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            throw new WrongTokenException();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return claimsJws;
    }
}
