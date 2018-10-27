package cn.vivian.securityshiro.controller;

import cn.vivian.securityshiro.dto.UserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author swzhang
 * @date 2018/10/24
 * @description
 */
@RestController
@RequestMapping(value = "/")
public class LoginController {

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto) {
        String userId = userDto.getUserId();
        String password = userDto.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(userId, password);
        //token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        System.out.println(subject.getPrincipal() + " " + subject.getPrincipal().getClass().getCanonicalName());
        new UserDto();
        return ResponseEntity.ok(userDto);
    }

    @RequiresPermissions("userInfo:index")
    @GetMapping(value = "index", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok("{\"userId\":\"token\"}");
    }
}
