package cn.jeff.demosecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author swzhang
 * @date 2018/8/21
 * @description
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerNewUserAccount(UserDto accountDto) throws EmailExistsException {
        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email address" + accountDto.getEmail()
            );
        }
        User user = new User();
        user.setEmail(accountDto.getEmail());
        user.setFirstName(accountDto.getFirstName());
        user.setId("sddd");
        user.setLastName(accountDto.getLastName());
        user.setPassword(accountDto.getPassword());
        user.setRoles("ROLE_USER");
        return userRepository.save(user);
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
}
