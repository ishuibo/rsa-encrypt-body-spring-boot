package cn.shuibo.annotation;

import java.lang.annotation.*;

/**
 * Author:Bobby
 * DateTime:2019/4/9 16:45
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encrypt{

}
