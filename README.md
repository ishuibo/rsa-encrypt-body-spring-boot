### 1.介绍
**rsa-encrypt-body-spring-boot**  
Spring Boot接口加密，可以对返回值、参数值通过注解的方式自动加解密。  

 [![](https://img.shields.io/badge/Author-Bobby-ff69b4.svg)]()
### 2.使用方法
**Apache Maven**
```
<dependency>
  <groupId>cn.shuibo</groupId>
  <artifactId>rsa-encrypt-body-spring-boot</artifactId>
  <version>1.0.2.RELEASE</version>
</dependency>
```
**Gradle Groovy DSL**
```
implementation 'cn.shuibo:rsa-encrypt-body-spring-boot:1.0.1.RELEASE'
```
**Gradle Kotlin DSL**、**Scala SBT**、**Apache Ivy**、**Groovy Grape**、**Leiningen**、**Apache Buildr**、**Maven Central Badge**、**PURL**、**Bazel**方式请阅读[Spring Boot接口RSA自动加解密](https://www.shuibo.cn/102.html)
- **以Maven为例，在pom.xml中引入依赖**  
```
<dependency>
    <groupId>cn.shuibo</groupId>
    <artifactId>rsa-encrypt-body-spring-boot</artifactId>
    <version>1.0.2.RELEASE</version>
</dependency>
```
- **启动类Application中添加@EnableSecurity注解**

```
@SpringBootApplication
@EnableSecurity
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```
- **在application.yml或者application.properties中添加RSA公钥及私钥**

```
rsa:
  encrypt:
    open: true # 是否开启加密 true  or  false
    showLog: true # 是否打印加解密log true  or  false
    timestampCheck: true # 是否开启时间戳检查 ture or false
    publicKey: # RSA公钥
    privateKey: # RSA私钥
```
- **对返回值进行加密**

```
@Encrypt
@GetMapping("/encryption")
public TestBean encryption(){
    TestBean testBean = new TestBean();
    testBean.setName("shuibo.cn");
    testBean.setAge(18);
    return testBean;
}
```
- **对传过来的加密参数解密**

```
@Decrypt
@PostMapping("/decryption")
public String Decryption(@RequestBody TestBean testBean){
    return testBean.toString();
}
```

- **@Decrypt**
```java
public @interface Decrypt{

    /**
     * 请求参数一定要是加密内容
     */
    boolean required() default false;

    /**
     * 请求数据时间戳校验时间差
     * 超过(当前时间-指定时间)的数据认定为伪造
     * 注意应用程序需要捕获 {@link cn.shuibo.exception.EncryptRequestException} 异常
     */
    long timeout() default 3000;
}

```

- **@EnDecrypt**, 一个注解实现入参/响应加密
```java
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
     * 注意应用程序需要捕获 {@link cn.shuibo.exception.EncryptRequestException} 异常
     */
    @AliasFor(annotation = Decrypt.class, value = "timeout")
    long timeout() default 3000;
}
```

- **AbstractResponseCovert**, 依赖方可自定义JSON响应转换工具
```java
@Component
public class ResponseCovert extends AbstractResponseCovert {

    @Override
    public String covert(Object body) {
        return JSON.toJSONString(body);
    }
}
```

### 3.About author
![码农届首家互娱自媒体](https://images.gitee.com/uploads/images/2020/0529/112357_aac5a702_1674154.jpeg "kxmn.jpg")
- Blog：https://shuibo.cn
- QQ群：7277991 [点击加入](http://shang.qq.com/wpa/qunwpa?idkey=d919a3676fe81a081cf90698a55b38c162285c92ef3c7a529972f39cd7787ef9)






