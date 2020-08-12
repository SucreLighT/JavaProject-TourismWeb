package cn.sucre.dao.impl;

import cn.sucre.dao.RouteImgDao;
import cn.sucre.domain.RouteImg;
import cn.sucre.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @description:RouteImgDao的实现类
 * @author: sucre
 * @date: 2020/08/12
 * @time: 14:46
 */
public class RouteImgDaoImpl implements RouteImgDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<RouteImg> findByRid(int rid) {
        String sql = "select * from tab_route_img where rid = ? ";
        return template.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid);

    }
}
