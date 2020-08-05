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


    /**
     * @param code
     * @return boolean
     * @author sucre
     * @function 根据激活码激活用户
     */
    public boolean activate(String code);

    /**
     * @param user
     * @return User
     * @author sucre
     * @function 登录用户
     */
    User login(User user);
}
