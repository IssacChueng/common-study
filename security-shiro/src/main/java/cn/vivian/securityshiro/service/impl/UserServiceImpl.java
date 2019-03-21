package cn.vivian.securityshiro.service.impl;

import cn.vivian.securityshiro.dto.UserDto;
import cn.vivian.securityshiro.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

/**
 * @author swzhang
 * @date 2018/10/24
 * @description
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public UserDto getUser(String userId) {
        if (userId.equals("123")) {
            UserDto userDto = new UserDto();
            userDto.setUserId("123");
            userDto.setPassword(new SimpleHash("md5", "123", "acd324b2", 1).toHex());
            userDto.setSalt("acd324b2");
            return userDto;
        } else {
            return null;
        }
    }
}
