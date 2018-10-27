package cn.vivian.commontoken.service;

import cn.vivian.commontoken.dto.ApplicationDto;
import cn.vivian.commontoken.dto.UserDto;

public interface UserService {
    UserDto queryUser(String userId);

    void saveUserToCache(String userId, UserDto userDto);

    ApplicationDto queryApplication(String appKey);
}
