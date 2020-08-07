package cn.sucre.dao;

import cn.sucre.domain.Category;

import java.util.List;

public interface CategoryDao {
    /**
     * @param
     * @return List
     * @author sucre
     * @function 获取所有的分类条目
     */
    public List<Category> findAll();

}
