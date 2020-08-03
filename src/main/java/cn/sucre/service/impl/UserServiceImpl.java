package cn.sucre.service.impl;

import cn.sucre.dao.UserDao;
import cn.sucre.dao.impl.UserDaoImpl;
import cn.sucre.domain.User;
import cn.sucre.service.UserService;

/**
 * @description: UserServive实现类
 * @author: sucre
 * @date: 2020/07/30
 * @time: 14:20
 */
public class UserServiceImpl implements UserService {


    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean regist(User user) {
        User u = userDao.findByUsername(user.getUsername());
        if (u != null) {
            //用户名存在，注册失败
            return false;
        }
        userDao.save(user);
        return true;
    }
}
