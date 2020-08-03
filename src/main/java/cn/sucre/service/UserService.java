package cn.sucre.service;

import cn.sucre.domain.User;

public interface UserService {

    /**
     * @param user
     * @return boolean
     * @author sucre
     * @function 注册用户
     */
    public boolean regist(User user);
}
