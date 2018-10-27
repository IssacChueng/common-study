package cn.vivian.commontoken;

import cn.vivian.commontoken.dao.ApplicationMapper;
import cn.vivian.commontoken.dao.UserMapper;
import cn.vivian.commontoken.dto.ApplicationDto;
import cn.vivian.commontoken.dto.UserDto;
import cn.vivian.commontoken.model.User;
import cn.vivian.commontoken.service.UserService;
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
    public UserDto queryUser(String userId){
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
