package cn.jeff.commontoken.filter;

import cn.jeff.commontoken.dto.UserDto;
import cn.jeff.commontoken.service.UserService;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Data
@WebFilter(urlPatterns = "/login/cloud")
public class LoginCloudFilter implements Filter {

    @Resource
    private UserService userService;

    public LoginCloudFilter() {
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String requestURI = request.getRequestURI();
        //验证用户名密码
        String userId = request.getParameter("UserId");
        if (StringUtils.isBlank(userId)) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "请求中缺少UserId");
            return;
        }
        String password = request.getParameter("Password");
        if (StringUtils.isBlank(password)) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "请求中缺少Password");
            return;
        }
        UserDto userDto = userService.queryUser(userId);
        if (userDto != null) {
            if (StringUtils.equals(password, userDto.getPassword())) {
                if (StringUtils.isBlank(userDto.getToken())) {
                    String token = UUID.randomUUID().toString();
                    userDto.setToken(token);
                    userService.saveUserToCache(userId, userDto);
                    response.setStatus(HttpStatus.OK.value());
                    response.addHeader("Token", token);
                    return;
                }
            }
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
