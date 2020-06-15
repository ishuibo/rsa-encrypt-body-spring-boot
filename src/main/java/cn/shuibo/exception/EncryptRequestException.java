package cn.shuibo.exception;


/**
 * @author imyzt
 * @date 2020/06/02
 * @description 加密请求超时异常
 */
public class EncryptRequestException extends RuntimeException {

    public EncryptRequestException(String msg) {
        super(msg);
    }
}
