package cn.jeff.securityshiro.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author swzhang
 * @date 2018/10/24
 * @description
 */
@Data
public class UserDto implements Serializable {
    private String userId;

    private String password;

    private String token;
    private String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
