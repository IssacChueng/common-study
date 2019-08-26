package cn.jeff.demojwt.filter;

import cn.jeff.demojwt.bean.User;
import cn.jeff.demojwt.utils.RSACryptographyUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword(),
                        new ArrayList<>()
                )
        );

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Calendar calendar = new Calendar.Builder().setDate(2018, 6, 14).build();
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        String token = null;
        try {
            token = Jwts.builder()
                    .setSubject(((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername())
                    .setExpiration(calendar.getTime())
                    .signWith(SignatureAlgorithm.RS512, RSACryptographyUtils.genKeyPair(1024).getPrivate())
                    .compact();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }


}
