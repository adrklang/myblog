package com.lhstack.myblog.registry.dao;

import com.lhstack.myblog.model.ucenter.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<BlogUser,Long> {
    BlogUser findByUsername(String username);
    BlogUser findByUsernameOrPasswordOrNickNameOrEmailOrMobile(String username, String password, String nickName, String email, String mobile);
}

