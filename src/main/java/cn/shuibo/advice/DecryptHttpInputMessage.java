package cn.shuibo.advice;

import cn.shuibo.annotation.Decrypt;
import cn.shuibo.config.SecretKeyConfig;
import cn.shuibo.exception.EncryptRequestException;
import cn.shuibo.util.Base64Util;
import cn.shuibo.util.RSAUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Author:Bobby
 * DateTime:2019/4/9
 **/
public class DecryptHttpInputMessage implements HttpInputMessage {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private HttpHeaders headers;
    private InputStream body;


    public DecryptHttpInputMessage(HttpInputMessage inputMessage, SecretKeyConfig secretKeyConfig, Decrypt decrypt) throws Exception {

        String privateKey =  secretKeyConfig.getPrivateKey();
        String charset = secretKeyConfig.getCharset();
        boolean showLog = secretKeyConfig.isShowLog();
        boolean timestampCheck = secretKeyConfig.isTimestampCheck();

        if (StringUtils.isEmpty(privateKey)) {
            throw new IllegalArgumentException("privateKey is null");
        }

        this.headers = inputMessage.getHeaders();
        String content = new BufferedReader(new InputStreamReader(inputMessage.getBody()))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        String decryptBody;
        // 未加密内容
        if (content.startsWith("{")) {
            // 必须加密
            if (decrypt.required()) {
                log.error("not support unencrypted content:{}", content);
                throw new EncryptRequestException("not support unencrypted content");
            }
            log.info("Unencrypted without decryption:{}", content);
            decryptBody = content;
        } else {
            StringBuilder json = new StringBuilder();
            content = content.replaceAll(" ", "+");

            if (!StringUtils.isEmpty(content)) {
                String[] contents = content.split("\\|");
                for (String value : contents) {
                    value = new String(RSAUtil.decrypt(Base64Util.decode(value), privateKey), charset);
                    json.append(value);
                }
            }
            decryptBody = json.toString();
            if(showLog) {
                log.info("Encrypted data received：{},After decryption：{}", content, decryptBody);
            }
        }

        // 开启时间戳检查
        if (timestampCheck) {
            // 容忍最小请求时间
            long toleranceTime = System.currentTimeMillis() - decrypt.timeout();
            long requestTime = JSON.parseObject(decryptBody).getLongValue("timestamp");
            // 如果请求时间小于最小容忍请求时间, 判定为超时
            if (requestTime < toleranceTime) {
                log.error("Encryption request has timed out, toleranceTime:{}, requestTime:{}, After decryption：{}", toleranceTime, requestTime, decryptBody);
                throw new EncryptRequestException("request timeout");
            }
        }

        this.body = new ByteArrayInputStream(decryptBody.getBytes());
    }

    @Override
    public InputStream getBody(){
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }
}
