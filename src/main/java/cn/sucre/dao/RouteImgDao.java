package cn.sucre.dao;

import cn.sucre.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {
    /**
     * 根据rid查询route对象中包含的图片对象
     *
     * @param rid
     * @return List
     */
    public List<RouteImg> findByRid(int rid);
}
