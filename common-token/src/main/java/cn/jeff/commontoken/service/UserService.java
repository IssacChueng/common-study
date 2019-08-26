package cn.jeff.commontoken.service;

import cn.jeff.commontoken.dto.ApplicationDto;
import cn.jeff.commontoken.dto.UserDto;

public interface UserService {
    UserDto queryUser(String userId);

    void saveUserToCache(String userId, UserDto userDto);

    ApplicationDto queryApplication(String appKey);
}
