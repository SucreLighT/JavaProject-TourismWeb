package cn.sucre.dao.impl;

import cn.sucre.dao.SellerDao;
import cn.sucre.domain.Seller;
import cn.sucre.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @description: SellerDao的实现类
 * @author: sucre
 * @date: 2020/08/12
 * @time: 14:49
 */
public class SellerDaoImpl implements SellerDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Seller findById(int sid) {
        String sql = "select * from tab_seller where sid = ? ";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class), sid);
    }
}
