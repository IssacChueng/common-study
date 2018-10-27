package cn.vivian.demosecurity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author swzhang
 * @date 2018/8/22
 * @description
 */
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
