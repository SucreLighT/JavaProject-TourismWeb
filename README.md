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

![image-20200805150212235](D:\Java\workplace\JavaProject\README.assets\image-20200805150212235.png)

## 主要实体类结构

|                            User类                            |                         ResultInfo类                         |      |      |      |      |      |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :--: | :--: | :--: | :--- | :--- |
| private int uid;//用户id<br/>    private String username;//用户名，账号<br/>    private String password;//密码<br/>    private String name;//真实姓名<br/>    private String birthday;//出生日期<br/>    private String sex;//男或女<br/>    private String telephone;//手机号<br/>    private String email;//邮箱<br/>    private String status;//激活状态，Y代表激活，N代表未激活<br/>    private String code;//激活码（要求唯一） | private boolean flag;//后端返回结果正常为true，发生异常返回false<br/>    private Object data;//后端返回结果数据对象<br/>    private String errorMsg;//发生异常的错误消息 |      |      |      |      |      |
|                                                              |                                                              |      |      |      |      |      |



## 用户注册功能

|   架构   |                             前端                             |                          Servlet层                           |                          Service层                           |                            Dao层                             |
| :------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|   文件   |                        register.html                         |                      RegistUserServlet                       |                         UserService                          |                           UserDao                            |
| 功能说明 | 1. 使用js完成表单校验<br />2. 使用ajax完成表达提交<br />3. 注册成功后，跳转到register_ok.html | 1. 获取数据<br />2. 将数据封装为User对象<br />3. 调用service完成注册<br />4. 根据service的返回信息，设置提示信息 | registerUser()调用Dao层根据用户名查询用户<br />1. 查询存在，直接返回false说明用户名已经存在；<br />2.查询不存在，则调用Dao层保存用户信息 | 1. findByUsername()根据用户名查询是否存在该用户<br />2. save用户保存用户信息 |

+ 在register.html中编写js代码进行表单校验，在点击提交按钮或是某一输入框失去焦点时触发对应事件。
  1. 用户名：单词字符，8-20位；
  2. 密码：单词字符，8-20位；
  3. email：邮件格式；
  4. 姓名：非空；
  5. 手机号：手机号格式；
  6. 出生日期：非空；
  7. 验证码：非空



## 邮件激活功能

|   架构   |                             前端                             |                          Servlet层                           |                          Service层                           |                            Dao层                             |
| :------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|   文件   |      register_ok.html发送邮件给用户，邮件中附带激活链接      |                     ActivateUserServlet                      |                         UserService                          |                           UserDao                            |
| 功能说明 | 点击激活该用户，跳转到相关的ActivateUserServlet，并带有参数code表示对应激活码 | 1. 获取激活码<br />2. 判断是否有激活码<br />3. 根据激活码查询User对象<br />4. 判断对象是否为null，如果不是null，调用service进行激活 | activate()调用dao层进行对象的激活，首先查询是否存在该用户，若存在则更新状态为激活 | findByCode()根据用户的激活码查询用户<br />updateStatus()更新用户状态为激活 |



## 用户登录功能

|   架构   |                             前端                             |                          Servlet层                           |          Service层           |                        Dao层                        |
| :------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :--------------------------: | :-------------------------------------------------: |
|   文件   |                          login.html                          |                         LoginServlet                         |         UserService          |                       UserDao                       |
| 功能说明 | 设置登录按钮单击事件，异步ajax提交数据，根据返回信息显示错误提示，或者跳转到相关的LoginServlet进行用户登录 | 1. 获取用户信息<br />2. 调用service查询user<br/>3. 判断用户是否存在<br/>4. 判断用户是否激活<br />5.如果用户存在且激活，则登录成功，**将该用户对象写入session** | login()调用dao层进行用户登录 | findByUsernameAndPassword()根据用户名和密码进行登录 |



## 显示用户登录状态和登出功能

|   架构   |                             前端                             |                          Servlet层                           |
| :------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|   文件   |                          head.html                           |              FindUserServlet<br />LogoutServlet              |
| 功能说明 | 1.在页面加载完成后，提交请求到FindUserServlet中获取当前登录的用户对象，并显示在页面上方<br />2.给登出按钮绑定单击事件，点击登出后，跳转到LogoutServlet执行登出操作 | 1.FindUserServlet中获取当前session中存储的登录用户对象，并将该对象写回到客户端浏览器中，前端界面通过ajax获取该用户名并显示<br />2.LogoutServlet销毁当前session并将页面跳转到login.html实现登出功能 |

















