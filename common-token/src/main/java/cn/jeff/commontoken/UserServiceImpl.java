package cn.jeff.commontoken;

import cn.jeff.commontoken.dao.ApplicationMapper;
import cn.jeff.commontoken.dao.UserMapper;
import cn.jeff.commontoken.dto.ApplicationDto;
import cn.jeff.commontoken.dto.UserDto;
import cn.jeff.commontoken.model.User;
import cn.jeff.commontoken.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Resource
    private ApplicationMapper applicationMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDto queryUser(String userId) {
        UserDto userDto = (UserDto) redisTemplate.opsForValue()
                .get(userId);
        if (userDto == null) {
            User user = userMapper.selectByPrimaryKey(userId);
            if (user != null) {
                userDto = new UserDto();
                BeanUtils.copyProperties(user, userDto);
                return userDto;
            }
        }
        return userDto;
    }

    @Override
    public void saveUserToCache(@NotNull String userId, UserDto userDto) {
        redisTemplate.opsForValue()
                .set(userId, userDto);
    }

    @Override
    public ApplicationDto queryApplication(String appKey) {
        return null;
    }
}
