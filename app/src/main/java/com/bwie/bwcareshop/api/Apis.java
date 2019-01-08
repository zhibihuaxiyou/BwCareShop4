package com.bwie.bwcareshop.api;

/**
 * @author : 张腾
 * @date : 2018/12/27.
 * desc :
 */
public class Apis {
    //登录
    public static final String LOGIN_URL = "user/v1/login";
    //注册
    public static final String REGIST_URL = "user/v1/register";
    //首页列表
    public static final String HOME_LIST_URL = "http://mobile.bwstudent.com/small/commodity/v1/commodityList";
    //根据商品列表归属标签查询商品信息
    public static final String GOODS_LIST_URL = "findCommodityListByLabel";
    //查询用户钱包
    public static final String GOODS_WALLET_URL = "user/verify/v1/findUserWallet";
    //根据用户ID查询用户信息
    public static final String GOODS_USER_URL = "user/verify/v1/getUserById";
    //商品详情
    public static final String DETAILS_URL = "commodity/v1/findCommodityDetailsById";
    //加入购物车
    public static final String ADDSHOP_URL = "order/verify/v1/syncShoppingCart";
    //查询购物车
    public static final String SELECT_SHOP_URL = "order/verify/v1/findShoppingCart";
}
