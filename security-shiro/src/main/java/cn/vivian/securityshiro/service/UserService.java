package cn.vivian.securityshiro.service;

import cn.vivian.securityshiro.dto.UserDto;

/**
 * @author swzhang
 * @date 2018/10/24
 * @description
 */
public interface UserService {
    UserDto getUser(String userId);
}
