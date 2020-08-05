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

    /**
     * @param code
     * @return User
     * @author sucre
     * @function 根据用户激活码查找用户
     */
    public User findByCode(String code);

    /**
     * @param user
     * @return void
     * @author sucre
     * @function 更新用户状态
     */
    public void updateStatus(User user);

    /**
     * @param username，password
     * @return User
     * @author sucre
     * @function 根据用户名和密码查询用户对象
     */
    User findByUsernameAndPassword(String username, String password);
}
