package cn.jeff.demosecurity;

/**
 * @author swzhang
 * @date 2018/8/21
 * @description
 */
public interface IUserService {
    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException;
}
