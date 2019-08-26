package cn.jeff.demojwt.filter;

import cn.jeff.demojwt.utils.RSACryptographyUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.startsWithIgnoreCase(header, "Bearer")) {
            chain.doFilter(request, response);
            return;
        } else {
            UsernamePasswordAuthenticationToken authenticationToken = getUsernamePasswordAuthenticationToken(header);
            SecurityContextHolder.getContext()
                    .setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token) {
        String user = null;
        try {
            user = Jwts.parser()
                    .setSigningKey(RSACryptographyUtils.genKeyPair(1024).getPrivate())
                    .parseClaimsJws(token.substring(6).trim())
                    .getBody()
                    .getSubject();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (null != user) {
            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        }
        return null;
    }
}
