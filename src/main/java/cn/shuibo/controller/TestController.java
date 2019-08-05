package cn.shuibo.controller;

import cn.shuibo.annotation.Decrypt;
import cn.shuibo.annotation.Encrypt;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        testBean.setName("shuibo.cn45646父亲为法国请问如果gregwerfwergwergwergwergwqergwergwergwergwergqwergwergwqergqerghqaghqe65165165165210651652106a5erg1aergadgbaergb1q56erg16a5e1rg6qa5ergaerg51b6awe5rg1b6ae51tgh6q5e1gb6as5e1rg56sa1erg65w16hw1rt56h1w6g5wa6re5g1b6q5erg1h6wreg1h6w5rg1h6r5t16wr5b1g6w5bg14t4h6w5rt1h6w5rg69h5g56w14r58hw4r9t8hwgqwergwergwerg65465461651werg6wqer5g16wqer5g16qwe51gh6wqe1h6we1trh6w1rth51w6th1w65eh1w6qet1h6w51trh65w1r6th1w6rt1h6wr1th6w5rt1h6w5r1th6wr1th6wfd5bg1w65rt1hb65wr1456hsw1trh65w4r1t6h54wr6b8hw6r5bt1hw6r514h9wr84thb69w5r1tbg65wrt69t4h69694wreth98w4tg8hwg1t84h9w8rt4h96w84rth6914w9t841w8h1wt89h4wr58h4w1r89h4wrthwrt555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555");
        testBean.setAge(18);
        return testBean;
    }

    @Decrypt
    @PostMapping("/test02")
    public String test02(@RequestBody TestBean testBean){
        return JSON.toJSONString(testBean);
    }
}
