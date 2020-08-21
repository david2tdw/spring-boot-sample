# mall-tiny-plus环境配置

## 技术选型
myBatis-plus, maven, spring, spring security, token, spring task, redis, swagger

## 工具：
安装IDEA，mysql， navcat，Redis，

## 安装依赖：
``` 
mvn clean install -Dmaven.test.skip=true

```
## 注册为redis服务：
```
  D:\Redis>redis-server.exe redis.windows.conf
```

## 运行mall-admin
1. 启动MySql服务
1. 启动项目：直接运行com.macro.mall.MallAdminApplication的main方法即可
1. 接口文档地址：http://localhost:8080/swagger-ui.html
1. /login
    账号：admin 密码：123456
    
## 文章
[初步理解Spring Security并实践](https://www.jianshu.com/p/e6655328b211)  
[Spring Task定时任务的配置和使用](https://www.jianshu.com/p/25c601f43552)  
[项目原型](https://mp.weixin.qq.com/s/Q9ybpfq8IEdbZmvlaMXJdg)