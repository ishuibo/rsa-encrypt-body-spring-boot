package cn.shuibo.advice;

import cn.shuibo.annotation.Decrypt;
import cn.shuibo.config.SecretKeyConfig;
import cn.shuibo.exception.EncryptRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Author:Bobby
 * DateTime:2019/4/9
 **/
@ControllerAdvice
public class EncryptRequestBodyAdvice  implements RequestBodyAdvice {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private boolean encrypt;
    private Decrypt decryptAnnotation;

    @Autowired
    private SecretKeyConfig secretKeyConfig;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        Method method = methodParameter.getMethod();
        if (Objects.isNull(method)) {
            encrypt = false;
            return false;
        }
        if (method.isAnnotationPresent(Decrypt.class) && secretKeyConfig.isOpen()) {
            encrypt = true;
            decryptAnnotation = methodParameter.getMethodAnnotation(Decrypt.class);
            return true;
        }
        // 此处如果按照原逻辑直接返回encrypt, 会造成一次修改为true之后, 后续请求都会变成true, 在不支持时, 需要做修正
        encrypt = false;
        return false;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType){
        if (encrypt) {
            try {
                return new DecryptHttpInputMessage(inputMessage, secretKeyConfig, decryptAnnotation);
            } catch (EncryptRequestException e) {
                throw e;
            } catch (Exception e) {
                log.error("Decryption failed", e);
            }
        }
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
