package com.dongjae.nearChatProejct.Common.Security;

import com.dongjae.nearChatProejct.Common.Error.Exception.ErrorCode;
import com.dongjae.nearChatProejct.Common.Error.Exception.UnauthorizedRequestException;
import com.dongjae.nearChatProejct.User.Service.UserService;
import com.dongjae.nearChatProejct.User.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException{
        String Token = getToken(request);
        try {
            // 유효한 토큰인지 확인합니다. 유효성검사
            if (Token != null && !Token.equalsIgnoreCase("null")) {
                Long id = jwtTokenProvider.validateAndGetMember(Token);
                AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userService.findById(id),
                        null,
                        AuthorityUtils.NO_AUTHORITIES
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
                emptyContext.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(emptyContext);
            }
            chain.doFilter(request, response);
        }

        catch (ExpiredJwtException e) {
            setErrorResponse(response, ErrorCode.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException | IllegalArgumentException e) {
            setErrorResponse(response, ErrorCode.INVALID_TOKEN);
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            setErrorResponse(response, ErrorCode.WRONG_TOKEN);
        } catch (UnauthorizedRequestException e){
            setErrorResponse(response,ErrorCode.UNAUTHORIZED_REQUEST);
        } catch (UserNotFoundException e ){
            setErrorResponse(response, ErrorCode.USER_NOT_FOUND);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void setErrorResponse(HttpServletResponse response, ErrorCode errorCode) {
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType("application/json; charset=UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write(new ObjectMapper()
                    .writeValueAsString(getMapWithResponse(new HashMap(),errorCode)));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HashMap getMapWithResponse(HashMap hashMap, ErrorCode errorCode) {
        hashMap.put("status", errorCode.getHttpStatus().value());
        hashMap.put("error", errorCode.getHttpStatus().name());
        hashMap.put("code", errorCode.name());
        hashMap.put("message", errorCode.getMessage());
        return hashMap;
    }

    public String getToken(HttpServletRequest request){
        return request.getHeader("Authorization");
    }
}
