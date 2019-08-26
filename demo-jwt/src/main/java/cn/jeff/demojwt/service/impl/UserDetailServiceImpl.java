package cn.jeff.demojwt.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //这一步根据用户名为s查找用户信息并进行比对
        cn.jeff.demojwt.bean.User user = new cn.jeff.demojwt.bean.User();
        user.setUsername("test");
        user.setPassword("test");
        return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
