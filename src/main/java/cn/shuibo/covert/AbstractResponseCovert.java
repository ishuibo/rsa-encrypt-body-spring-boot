package cn.shuibo.covert;

import cn.shuibo.util.JsonUtils;
import org.springframework.stereotype.Component;

/**
 * 响应转换实现, 可自定义该接口实现自定义json框架转换Javabean对象
 * 考虑到转换时包含enum对象/jsonView等注解的处理, 默认使用Jackson
 * @author imyzt
 * @date 2020/07/12
 */
@Component
public class AbstractResponseCovert {

    /**
     * 响应对象为JSON转换
     * @param body JavaBean对象
     * @return json文本
     * @throws Exception 异常信息
     */
    public String covert(Object body) throws Exception {
        return JsonUtils.writeValueAsString(body);
    }
}