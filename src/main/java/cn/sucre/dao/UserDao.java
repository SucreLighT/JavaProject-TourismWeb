package cn.sucre.dao;

import cn.sucre.domain.User;

public interface UserDao {

    /**
     * @param username
     * @return User
     * @author sucre
     * @function 根据用户名查询用户信息
     */
    public User findByUsername(String username);

    /**
     * @param user
     * @return void
     * @author sucre
     * @function 保存用户信息
     */
    public void save(User user);
}
