package cn.sucre.service;

public interface FavoriteService {
    /**
     * @param rid uid
     * @return boolean
     * @author sucre
     * @function 判断是否收藏
     */
    public boolean isFavorite(String rid, int uid);

    /**
     * 添加一条收藏记录
     *
     * @param rid
     * @param uid
     */
    void add(String rid, int uid);
}
