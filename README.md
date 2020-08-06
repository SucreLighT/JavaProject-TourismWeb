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
| 功能说明 | 1. 使用js完成表单校验<br />2. 使用ajax提交表单数据到RegistUserServlet中进行注册<br />3. 根据ajax的相应数据data判断是否注册成功<br />4. 注册成功后，跳转到register_ok.html；否则显示注册失败的信息 | 1. 校验验证码，通过request获取前端的验证码check，与session中的CHECKCODE_SERVER进行比较，如果验证码不一样，直接返回错误信息，不必进行注册功能<br />获取前端表单的数据<br />2. 使用BeanUtils将数据封装为User对象<br />3. 调用service的功能函数完成注册<br />4. 根据service的返回值判断是否注册成功，并将提示信息存储到ResultInfo的对象info中，用于设置提示信息<br />5. 使用json将info对象序列化并发送给前端 | register()调用Dao层根据用户名查询用户<br />1. 查询存在，直接返回false说明用户名已经存在；<br />2.查询不存在，则调用Dao层保存该用户信息，说明注册成功 | 1. findByUsername()根据用户名查询是否存在该用户，返回查询结果user对象，如果不存在则为null<br />2. save()用于保存用户信息 |

+ 在register.html中编写js代码进行表单校验，在点击提交按钮或是某一输入框失去焦点时触发对应事件。
  1. `checkUsername()`校验用户名：单词字符，8-20位；
  2. `checkPassword()`校验密码：单词字符，8-20位；
  3. `checkEmail()`校验email：邮件格式；
  4. `checkName()校验`姓名：非空；
  5. `checkTelephone()`校验手机号：手机号格式；
  6. `checkBirthday()校验`出生日期：非空；
  7. `checkCheck()`校验验证码：非空



## 邮件激活功能

|   架构   |                             前端                             |                          Servlet层                           |                          Service层                           |                            Dao层                             |
| :------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|   文件   |      register_ok.html发送邮件给用户，邮件中附带激活链接      |                     ActivateUserServlet                      |                         UserService                          |                           UserDao                            |
| 功能说明 | 点击激活该用户，跳转到相关的ActivateUserServlet，并带有参数code，表示对应激活码（唯一标识） | 1. 获取激活码<br />2. 如果激活码不为空，使用service方法进行结果，根据返回值判断是否激活成功并显示相应的信息激活码查询对应的User对象 | activate()调用dao层进行对象的激活，首先根据激活码code查询是否存在该用户，若存在则更新该用户状态为激活，不存在则直接返回false | 1. findByCode()根据用户的激活码查询用户，返回查询结果user对象<br />2. updateStatus()更新用户状态status = 'Y'为激活 |



## 用户登录功能

|   架构   |                             前端                             |                          Servlet层                           |          Service层           |                        Dao层                        |
| :------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :--------------------------: | :-------------------------------------------------: |
|   文件   |                          login.html                          |                         LoginServlet                         |         UserService          |                       UserDao                       |
| 功能说明 | 1. 设置登录按钮单击事件<br />2. 异步发送ajax请求，提交数据到LoginServlet，根据返回信息判断是否登录成功<br />3. 登录成功则跳转到index.html界面，否则显示错误提示 | 1. 获取用户信息，使用BeanUtils进行封装<br />2. 调用service中的方法查询user<br/>3. 根据查询返回的user对象，判断该用户是否存在以及是否激活，并保存对应的信息到info对象中<br />4. 如果用户存在且激活，则登录成功，需要**将该用户对象写入session中**<br />5. 使用json将info对象序列化并发送给前端 | login()调用dao层进行用户登录 | findByUsernameAndPassword()根据用户名和密码进行登录 |



## 显示用户登录状态和登出功能

|   架构   |                             前端                             |                          Servlet层                           |
| :------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|   文件   |                          head.html                           |              FindUserServlet<br />LogoutServlet              |
| 功能说明 | 1.在页面加载完成后，提交请求到FindUserServlet中获取当前登录的用户对象，并显示在页面上方<br />2.给登出按钮绑定单击事件，点击登出后，跳转到LogoutServlet执行登出操作 | 1.FindUserServlet接受前端的请求，在页面加载完成后，从当前session中获取存储的已登录的用户对象，并将该对象通过json写回到客户端浏览器中，前端界面通过ajax获取该用户名并显示<br />2.LogoutServlet销毁当前session并将页面跳转到login.html实现登出功能 |

















