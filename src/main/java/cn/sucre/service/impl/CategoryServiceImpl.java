package cn.sucre.service.impl;

import cn.sucre.dao.CategoryDao;
import cn.sucre.dao.impl.CategoryDaoImpl;
import cn.sucre.domain.Category;
import cn.sucre.service.CategoryService;

import java.util.List;

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
        return categoryDao.findAll();
    }
}
