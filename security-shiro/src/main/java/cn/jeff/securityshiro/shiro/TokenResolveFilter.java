package cn.jeff.securityshiro.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author swzhang
 * @date 2018/10/26
 * @description
 */
public class TokenResolveFilter extends UserFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        boolean logined = false;
        if (isLoginAttempt(request)) {
            logined = doLogin(request, response);
        }
        if (logined) {
            return true;
        } else {
            return super.onAccessDenied(request, response);
        }
    }

    private boolean doLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String userId = httpRequest.getHeader("userId");
        String token = httpRequest.getHeader("token");
        MyToken loginInfo = new MyToken(userId, token);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(loginInfo);
            return true;
        } catch (AuthenticationException e) {
            sendChallenge(request, response);
        }
        return false;

    }

    protected boolean isLoginAttempt(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String userId = httpRequest.getHeader("userId");
        String token = httpRequest.getHeader("token");
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(token)) {
            return false;
        } else {
            return true;
        }
    }

    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return false;
    }
}
