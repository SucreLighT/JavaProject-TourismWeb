# JavaWeb-旅游网站项目

## 技术选型
- [x] Web层
  - [ ] Servlet：前端控制器
  - [ ] html：视图
  - [ ] Filter：过滤器
  - [ ] BeanUtils：数据封装
  - [ ] Jackson：json序列化工具
- [x] Service层
  - [ ] Javamail：java发送邮件工具
  - [ ] Redis：nosql内存数据库
  - [ ] Jedis：java的redis客户端
- [x] Dao层
  - [ ] Mysql：数据库
  - [ ] Druid：数据库连接池
  - [ ] JdbcTemplate：jdbc的工具
  
  
## 数据库表结构

使用sql脚本完成数据库表的建立

## 主要实体类结构

|                            User类                            |                         ResultInfo类                         |      |      |      |      |      |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :--: | :--: | :--: | :--- | :--- |
| private int uid;//用户id<br/>    private String username;//用户名，账号<br/>    private String password;//密码<br/>    private String name;//真实姓名<br/>    private String birthday;//出生日期<br/>    private String sex;//男或女<br/>    private String telephone;//手机号<br/>    private String email;//邮箱<br/>    private String status;//激活状态，Y代表激活，N代表未激活<br/>    private String code;//激活码（要求唯一） | private boolean flag;//后端返回结果正常为true，发生异常返回false<br/>    private Object data;//后端返回结果数据对象<br/>    private String errorMsg;//发生异常的错误消息 |      |      |      |      |      |
|                                                              |                                                              |      |      |      |      |      |



## 用户注册功能

|   架构   |                             前端                             |                          Servlet层                           |                          Service层                           |                            Dao层                             |
| :------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|   文件   |                        register.html                         |                      registUserServlet                       |                         UserService                          |                           UserDao                            |
| 功能说明 | 1. 使用js完成表单校验<br />2. 使用ajax完成表达提交<br />3. 注册成功后，跳转到register_ok.html | 1. 获取数据<br />2. 将数据封装为User对象<br />3. 调用service完成注册<br />4. 根据service的返回信息，设置提示信息 | registerUser调用Dao层根据用户名查询用户<br />1. 查询存在，直接返回false说明用户名已经存在；<br />2.差吨不存在，则调用Dao层保存用户信息 | 1. findByUsername根据用户名查询是否存在该用户<br />2. save用户保存用户信息 |
|   备注   |                                                              |  返回信息时需要使用json数据格式，同时设置响应头contentType   |                                                              |                                                              |



1. 在register.html中编写js代码进行表单校验，在点击提交按钮或是某一输入框失去焦点时触发对应事件。
   1. 用户名：单词字符，8-20位；
   2. 密码：单词字符，8-20位；
   3. email：邮件格式；
   4. 姓名：非空；
   5. 手机号：手机号格式；
   6. 出生日期：非空；
   7. 验证码：非空
2. 

























