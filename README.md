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
  <version>1.0.1.RELEASE</version>
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
    <version>1.0.1.RELEASE</version>
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
### 3.About author
![](https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/super/pic/item/5366d0160924ab18a5f64be03bfae6cd7a890b00.jpg)
- Blog：https://shuibo.cn
- QQ群：7277991 [点击加入](http://shang.qq.com/wpa/qunwpa?idkey=d919a3676fe81a081cf90698a55b38c162285c92ef3c7a529972f39cd7787ef9)






