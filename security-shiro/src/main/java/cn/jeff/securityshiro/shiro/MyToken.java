package cn.jeff.securityshiro.shiro;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author swzhang
 * @date 2018/11/24
 * @description
 */
@Data
public class MyToken extends UsernamePasswordToken {
    private static final long serialVersionUID = 3059862181946673668L;
    private String userId;

    private String token;

    public MyToken(String username, String password) {
        super(username, password);
    }
}
