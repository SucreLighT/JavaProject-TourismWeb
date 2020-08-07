package cn.sucre.service.impl;

import cn.sucre.dao.CategoryDao;
import cn.sucre.dao.impl.CategoryDaoImpl;
import cn.sucre.domain.Category;
import cn.sucre.service.CategoryService;
import cn.sucre.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @description:CategoryService实现类
 * @author: sucre
 * @date: 2020/08/07
 * @time: 10:16
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        //1.获取Jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //2.使用sortedset作为数据结构，key为category，0到-1表示所有
        Set<Tuple> redisCategories = jedis.zrangeWithScores("category", 0, -1);

        List<Category> categories = null;

        //3.判断redis缓存中的集合是否为空
        if (redisCategories == null || redisCategories.size() == 0) {
            //如果缓存中为空，从数据库中查询，
            System.out.println("从数据库中查询...");
            categories = categoryDao.findAll();

            for (int i = 0; i < categories.size(); i++) {
                jedis.zadd("category", categories.get(i).getCid(), categories.get(i).getCname());
            }
        } else {
            //4.如果不为空,将set的数据存入list
            System.out.println("从缓存中中查询...");
            categories = new ArrayList<Category>();
            for (Tuple tuple : redisCategories
            ) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int) tuple.getScore());
                categories.add(category);
            }
        }
        return categories;
    }
}
