package cn.sucre.util;

import java.util.UUID;

/**
 * @description: 生成唯一标志符的工具类
 * @author: sucre
 * @date: 2020/08/04
 * @time: 10:30
 */
public class UuidUtil {
    private UuidUtil(){}
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
    /**
     * 测试
     */
    public static void main(String[] args) {
        System.out.println(UuidUtil.getUuid());
        System.out.println(UuidUtil.getUuid());
        System.out.println(UuidUtil.getUuid());
        System.out.println(UuidUtil.getUuid());
    }
}
