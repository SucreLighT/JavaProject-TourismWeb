package cn.sucre.service;

        import cn.sucre.domain.PageBean;
        import cn.sucre.domain.Route;

public interface RouteService {
    /**
     * @param cid currentPage pageSize rname
     * @return PageBean
     * @author sucre
     * @function 分页查询
     */
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    /**
     * @param rid
     * @return route
     * @author sucre
     * @function 根据id查询单个route对象
     */
    public Route findOne(String rid);
}
