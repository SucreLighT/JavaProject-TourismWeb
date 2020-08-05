package cn.sucre.service.impl;

import cn.sucre.dao.UserDao;
import cn.sucre.dao.impl.UserDaoImpl;
import cn.sucre.domain.User;
import cn.sucre.service.UserService;
import cn.sucre.util.MailUtils;
import cn.sucre.util.UuidUtil;

/**
 * @description: UserService实现类
 * @author: sucre
 * @date: 2020/07/30
 * @time: 14:20
 */
public class UserServiceImpl implements UserService {


    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean regist(User user) {
        // 1.根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        if (u != null) {
            //用户名存在，注册失败
            return false;
        }
        // 2.保存用户信息
        // 2.1设置激活码，使用唯一字符串uuid
        user.setCode(UuidUtil.getUuid());
        // 2.2设置激活状态为未激活
        user.setStatus("N");

        userDao.save(user);


        // 3.激活邮件发送
        String content = "<a href='http://localhost:8080/JavaProject/ActivateUserServlet?code=" + user.getCode() +
                "'>点击激活邮件</a>";
        MailUtils.sendMail(user.getEmail(), content, "激活邮件");
        return true;
    }

    @Override
    public boolean activate(String code) {
        User user = userDao.findByCode(code);
        if(user != null){
            userDao.updateStatus(user);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
