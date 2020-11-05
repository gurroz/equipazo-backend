package com.equipazo.adapter.persistence;

import com.equipazo.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    UserJPAEntity mapToJpaEntity(User user) {
        return new UserJPAEntity(
                user.getId(),
                user.getName(),
                user.getMobile());
    }

    User mapToUser(UserJPAEntity user) {
        return new User(
                user.getId(),
                user.getName(),
                user.getMobile());
    }
}
