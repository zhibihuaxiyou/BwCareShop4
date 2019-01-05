package com.bwie.bwcareshop.netWork;

import com.bwie.bwcareshop.api.Apis;
import com.bwie.bwcareshop.bean.WalletBean;

import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author : 张腾
 * @date : 2018/12/27.
 * desc :
 */
public interface BaseApis<T> {
    @GET
    Observable<ResponseBody> get(@Url String url);

    @POST
    Observable<ResponseBody> post(@Url String url, @QueryMap Map<String, String> map);

    @Multipart
    @POST
    Observable<ResponseBody> postFormBody(@Url String url, @PartMap Map<String, RequestBody> requestBodyMap);

    //我的钱包
    @GET(Apis.GOODS_WALLET_URL)
    Call<WalletBean> showWallet(@Query("page") int page, @Query("count") int count);
}
