package com.lhstack.myblog.registry.service.impl;

import com.lhstack.myblog.commons.ex.RegistryException;
import com.lhstack.myblog.commons.model.response.UserCenterCode;
import com.lhstack.myblog.model.ucenter.BlogUser;
import com.lhstack.myblog.model.ucenter.response.BlogUserResult;
import com.lhstack.myblog.registry.dao.UserRepository;
import com.lhstack.myblog.registry.service.IUserService;
import com.lhstack.myblog.utils.EncryptUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public BlogUserResult registry(BlogUser blogUser) {
        if(blogUser == null){
            throw new RegistryException(0);
        }
        if(StringUtils.isEmpty(blogUser.getUsername()) || StringUtils.isEmpty(blogUser.getPassword())){
            throw new RegistryException(0);
        }
        if(userRepository.findByUsername(blogUser.getUsername()) != null){
            throw new RegistryException(1);
        }
        blogUser.setNickName(blogUser.getUsername())
                .setRoleId(2l)
                .setCreateTime(new Date())
                .setSalt(EncryptUtils.salt())
                .setPassword(new BCryptPasswordEncoder().encode(blogUser.getSalt() + blogUser.getPassword()))
                .setIcon("http://pic.qqkuyou.com/tximg/ipfjijz.jpeg");
        BlogUser resultBlogUser = userRepository.save(blogUser);
        return new BlogUserResult(UserCenterCode.USER_REGISTORY_SUCCESS,resultBlogUser);
    }
}
