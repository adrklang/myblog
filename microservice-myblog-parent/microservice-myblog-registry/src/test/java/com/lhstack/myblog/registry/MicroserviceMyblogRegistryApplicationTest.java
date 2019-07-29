package com.lhstack.myblog.registry;

import com.lhstack.myblog.model.ucenter.BlogUser;
import com.lhstack.myblog.model.ucenter.response.BlogUserResult;
import com.lhstack.myblog.registry.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/*@SpringBootTest
@RunWith(SpringRunner.class)*/
public class MicroserviceMyblogRegistryApplicationTest {
    @Autowired
    private IUserService userService;
    @Test
    public void testSave(){
        String encode = new BCryptPasswordEncoder().encode("admin");
        System.out.println(encode);
    }
}
