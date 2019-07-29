/*
package com.lhstack.myblog.validcode;

import com.lhstack.myblog.validcode.annotation.EnableValidCode;
import com.lhstack.myblog.validcode.service.ValidCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.FileOutputStream;

@SpringBootApplication
@EnableValidCode
@ComponentScan({"com.lhstack.myblog.api","com.lhstack.myblog.validcode"})
public class MicroserviceMyblogValidcodeApplication implements ApplicationRunner {

    @Autowired
    private ValidCodeService validCodeService;

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceMyblogValidcodeApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String validCode = validCodeService.createValidCode(new FileOutputStream("C:\\Users\\10057\\Desktop\\新建文件夹\\img.jpg"));
        System.out.println(validCode);
    }
}
*/
