package cn.sucre.dao;

import cn.sucre.domain.Seller;

public interface SellerDao {
    /**
     * @param sid
     * @return seller
     * @author sucre
     * @function 根据sid查询该route对象对应的商家对象seller
     */
    public Seller findById(int sid);
}
