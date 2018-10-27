package cn.vivian.commontoken.filter;

import cn.vivian.commontoken.constant.AppSecurity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.DigestUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

@WebFilter("/**")
public class AppKeyFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 首先判断是否为login请求,如果不是,判断是否有token,如果没有,判断是否有appkey和appsecret
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if ("/login/cloud".equals(httpRequest.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }
        String appKey = httpRequest.getHeader("AppKey");
        String appSecret = httpRequest.getHeader("AppSecret");
        if (StringUtils.isBlank(appKey) || StringUtils.isBlank(appSecret)) {
            httpResponse.sendError(HttpStatus.BAD_REQUEST.value(), "请求中缺少AppKey和AppSecret");
        } else {
            appSecret = appSecret.trim().toLowerCase();
            String md5Digest = DigestUtils.md5DigestAsHex(((appKey + AppSecurity.AppSecret).getBytes(Charset.forName("UTF-8")))).toLowerCase();
            if (StringUtils.equals(appSecret, md5Digest)) {
                chain.doFilter(request, response);
            } else {
                httpResponse.sendError(HttpStatus.BAD_REQUEST.value(), "请求中的APPSecret不正确");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
