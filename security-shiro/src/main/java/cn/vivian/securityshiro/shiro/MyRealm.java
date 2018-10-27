package cn.vivian.securityshiro.shiro;

import cn.vivian.securityshiro.dto.UserDto;
import cn.vivian.securityshiro.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;

/**
 * @author swzhang
 * @date 2018/10/23
 * @description
 */

@Data
@Slf4j
public class MyRealm extends AuthorizingRealm {

    private UserService userService;

/**
     * 权限
     *
     * @param principals
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("authorization -----------");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserDto user = (UserDto) principals.getPrimaryPrincipal();
        //TODO 还需角色表
        UserDto userDto = userService.getUser(user.getUserId());
        authorizationInfo.addStringPermission("userInfo:index");
        return authorizationInfo;
    }

/**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken authToken = (UsernamePasswordToken) token;
        UserDto userDto = userService.getUser(authToken.getUsername());
        if (userDto == null) {
            throw new UnknownAccountException("未知用户");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userDto, userDto.getPassword(),
                new MySimpleByteSource(SimpleByteSource.Util.bytes(userDto.getSalt())),
                getName()
        );
        return authenticationInfo;
    }
}
