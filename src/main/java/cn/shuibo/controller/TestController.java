package cn.shuibo.controller;

import cn.shuibo.annotation.Decrypt;
import cn.shuibo.annotation.Encrypt;
import org.springframework.web.bind.annotation.*;

/**
 * Author:Bobby
 * DateTime:2019/4/9 17:13
 **/
@RestController
public class TestController {

    @Encrypt
    @GetMapping("/test01")
    public TestBean test01(){
        TestBean testBean = new TestBean();
        testBean.setName("shuibo.cn");
        testBean.setAge(18);
        return testBean;
    }

    @Decrypt
    @PostMapping("/test02")
    public String test02(@RequestBody TestBean testBean){
        return testBean.toString();
    }
}
