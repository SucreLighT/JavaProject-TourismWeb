package cn.sucre.dao;

import cn.sucre.domain.Route;

import java.util.List;

public interface RouteDao {
    /**
     * @param cid
     * @return int
     * @author sucre
     * @function 根据cid查询总记录数
     */
    public int findTotalCount(int cid);

    /**
     * @param cid start pageSize
     * @return List
     * @author sucre
     * @function 根据cid，start以及pageSize查询当前页的数据集合
     */
    public List<Route> findByPage(int cid, int start, int pageSize);
}
