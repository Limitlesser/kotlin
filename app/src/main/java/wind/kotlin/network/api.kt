package wind.kotlin.network

import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import rx.Observable
import wind.kotlin.model.Shops


/**
 * Created by pc on 2016/4/22.
 */

fun <T> getApi(api: Class<T>): T {
    val host = "http://app.skyallhere.net:9001";
    var retrofit = Retrofit.Builder().baseUrl(host)
            .addConverterFactory(GsonConverterFactory.create()).build();
    return retrofit.create(api);
}

interface Api {
    @GET("/CW_APPInterfaceServer/shoplist.do?param=eyJjaXR5Ijoi5rex5Zyz5biCIiwiaW5mb19pZCI6MCwidmVyIjowLCJrZXl2YWx1ZSI6IjMxNTE2YTAxNjYxNGNlNzY4MTA2YjJmMWUzNDRhZGNkIiwicmQiOiIxNzYwNTIifQ%3D%3D")
    fun getShopList(): Observable<Shops>;
}
