# 快速搭建我们的SpringBoot 2.0 
这个分支并没有多少代码，基本都是SpringBoot框架的代码。我这里仅仅是创建了一个html文件，用于SpringBoot 项目启动后的测试使用。关于快速创建我们的SpringBoot项目请参考wiki [快速搭建我们的SpringBoot 2.0 ](https://github.com/zhuoqianmingyue/springbootexamples/wiki/%E5%BF%AB%E9%80%9F%E6%90%AD%E5%BB%BA%E6%88%91%E4%BB%AC%E7%9A%84SpringBoot-2.0)

快速搭建主要涉及2个配置在/src/main/resources/ 目录下的**application.properties**中：

```
server.port=8080
server.servlet.context-path=/sbe

```
server.port 配置我们项目访问的端口号，server.servlet.context-path 配置访问的项目名称


