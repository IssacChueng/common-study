package cn.vivian.securityshiro.shiro;

import lombok.Data;
import org.apache.shiro.mgt.AbstractRememberMeManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.util.WebUtils;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author swzhang
 * @date 2018/10/24
 * @description
 */
@Data
public class TokenRemeberMeManager extends AbstractRememberMeManager {

    private RedisCacheManager redisCacheManager;

    /**
     * 清除rememberMe标志
     * @param subject
     */
    @Override
    protected void forgetIdentity(Subject subject) {
        if (WebUtils.isHttp(subject)) {
            HttpServletRequest request = WebUtils.getHttpRequest(subject);
            HttpServletResponse response = WebUtils.getHttpResponse(subject);
            forgetIdentity(request, response);
        }
    }

    private void forgetIdentity(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getHeader("userId");
        String token = request.getHeader("token");
        response.setHeader("userId", userId);
        response.setHeader("token",  token);
    }

    @Override
    protected void rememberSerializedIdentity(Subject subject, byte[] serialized) {

    }

    /**
     * 从请求中获取用户信息
     * @param subjectContext
     * @return
     */
    @Override
    protected byte[] getRememberedSerializedIdentity(SubjectContext subjectContext) {
        return new byte[0];
    }

    @Override
    public void forgetIdentity(SubjectContext subjectContext) {
        if (WebUtils.isHttp(subjectContext)) {
            HttpServletRequest request = WebUtils.getHttpRequest(subjectContext);
            HttpServletResponse response = WebUtils.getHttpResponse(subjectContext);
            forgetIdentity(request, response);
        }
    }

    @Override
    protected byte[] convertPrincipalsToBytes(PrincipalCollection principals) {
        return super.convertPrincipalsToBytes(principals);
    }

    @Override
    protected PrincipalCollection convertBytesToPrincipals(byte[] bytes, SubjectContext subjectContext) {
        return super.convertBytesToPrincipals(bytes, subjectContext);
    }
}
