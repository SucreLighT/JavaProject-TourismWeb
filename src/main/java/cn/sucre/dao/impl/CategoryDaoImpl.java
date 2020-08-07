package cn.sucre.dao.impl;

import cn.sucre.dao.CategoryDao;
import cn.sucre.domain.Category;
import cn.sucre.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @description:CategoryDao实现类
 * @author: sucre
 * @date: 2020/08/07
 * @time: 10:12
 */
public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category order by cid asc";

        return template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
    }
}
