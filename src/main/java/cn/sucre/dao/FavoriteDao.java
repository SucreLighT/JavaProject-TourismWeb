package cn.sucre.dao;

import cn.sucre.domain.Favorite;

public interface FavoriteDao {
    /**
     * 根据rid和uid查询是否存在该favorite对象
     *
     * @param rid
     * @param uid
     * @return Favorite
     */
    public Favorite findByRidAndUid(int rid, int uid);

    /**
     * 查询收藏次数
     *
     * @param rid
     * @return
     */
    public int findCountByRid(int rid);

    /**
     * 添加收藏记录
     *
     * @param rid
     * @param uid
     */
    public void add(int rid, int uid);
}
