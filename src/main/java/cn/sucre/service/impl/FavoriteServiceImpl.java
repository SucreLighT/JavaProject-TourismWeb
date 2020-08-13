package cn.sucre.service.impl;

import cn.sucre.dao.FavoriteDao;
import cn.sucre.dao.impl.FavoriteDaoImpl;
import cn.sucre.domain.Favorite;
import cn.sucre.service.FavoriteService;

/**
 * @description:
 * @author: sucre
 * @date: 2020/08/13
 * @time: 09:59
 */
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);

        //如果对象有值，则为true，反之，则为false
        return favorite != null;
    }

    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid), uid);
    }
}
