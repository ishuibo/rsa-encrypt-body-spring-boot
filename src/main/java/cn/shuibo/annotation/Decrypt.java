package cn.shuibo.annotation;

import cn.shuibo.exception.EncryptRequestException;

import java.lang.annotation.*;

/**
 * Author:Bobby
 * DateTime:2019/4/9 16:45
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Decrypt{

    /**
     * 请求参数一定要是加密内容
     */
    boolean required() default false;

    /**
     * 请求数据时间戳校验时间差
     * 超过(当前时间-指定时间)的数据认定为伪造
     * 注意应用程序需要捕获 {@link EncryptRequestException} 异常
     */
    long timeout() default 3000;
}
