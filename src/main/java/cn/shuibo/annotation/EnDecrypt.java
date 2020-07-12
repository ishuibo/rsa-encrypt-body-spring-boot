package cn.shuibo.annotation;

import cn.shuibo.exception.EncryptRequestException;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 加解密, 一个注解实现{@link Decrypt} 和 {@link EnDecrypt}
 * @author imyzt
 * @date 2020/07/12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Decrypt
@Encrypt
public @interface EnDecrypt {

    /**
     * 请求参数一定要是加密内容
     */
    @AliasFor(annotation = Decrypt.class, value = "required")
    boolean required() default false;

    /**
     * 请求数据时间戳校验时间差
     * 超过(当前时间-指定时间)的数据认定为伪造
     * 注意应用程序需要捕获 {@link EncryptRequestException} 异常
     */
    @AliasFor(annotation = Decrypt.class, value = "timeout")
    long timeout() default 3000;
}
