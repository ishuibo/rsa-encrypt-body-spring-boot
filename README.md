### 1.介绍
**rsa-encrypt-body-spring-boot**  
Spring Boot接口加密，可以对返回值、参数值通过注解的方式自动加解密
。  

 [![](https://img.shields.io/badge/Author-Bobby-ff69b4.svg)]()
### 2.使用方法
**Apache Maven**
```
<dependency>
  <groupId>cn.shuibo</groupId>
  <artifactId>rsa-encrypt-body-spring-boot</artifactId>
  <version>1.0.0.RELEASE</version>
</dependency>
```
**Gradle Groovy DSL**
```
implementation 'cn.shuibo:rsa-encrypt-body-spring-boot:1.0.0.RELEASE'
```
Gradle Kotlin DSL、Scala SBT、Apache Ivy、Groovy Grape、Leiningen、Apache Buildr、Maven Central Badge、PURL、Bazel方式请阅读[Spring Boot接口RSA自动加解密](https://shuibo.cn/05-spring-boot-rsa.html)
- **以Maven为例，在pom.xml中引入依赖**  
```
<dependency>
    <groupId>cn.shuibo</groupId>
    <artifactId>rsa-encrypt-body-spring-boot</artifactId>
    <version>1.0.0.RELEASE</version>
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
    debug: false # true表示开启调试，不加密。（方便开发时测试）
    publicKey: 123456
    privateKey: 123456
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
### 3.About author
![](https://upload-images.jianshu.io/upload_images/16900722-ea645622f5811993.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- Blog：https://shuibo.cn
- QQ群：7277991 [点击加入](http://shang.qq.com/wpa/qunwpa?idkey=d919a3676fe81a081cf90698a55b38c162285c92ef3c7a529972f39cd7787ef9)






