package cn.sucre.service;

import cn.sucre.domain.Category;

import java.util.List;

public interface CategoryService {
    /**
     * @param
     * @return List
     * @author sucre
     * @function 查询所有分类条目
     */
    public List<Category> findAll();
}
