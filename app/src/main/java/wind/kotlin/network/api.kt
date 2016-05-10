package wind.kotlin.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import retrofit2.Call
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.RxJavaCallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import wind.kotlin.model.Shops
import java.lang.reflect.Modifier
import java.util.ArrayList


/**
 * Created by pc on 2016/4/22.
 */

val host = "http://music.163.com/"

val header = mapOf(
        "Accept"           to   "*/*",
        "Accept-Encoding"  to   "gzip,deflate,sdch",
        "Accept-Language"  to   "zh-CN,zh;q=0.8,gl;q=0.6,zh-TW;q=0.4",
        "Connection"       to   "keep-alive",
        "Content-Type"     to   "application/x-www-form-urlencoded",
        "Host"             to   "music.163.com",
        "Referer"          to   "http://music.163.com/search/",
        "User-Agent"       to   "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36"
)

val COOKIES = hashMapOf(
        "appver"           to   "2.0.2",
        "__csrf"           to   "",
        "_ntes_nuid"       to   "",
        "_ntes_nnid"       to   "",
        "NETEASE_WDA_UID"  to   "",
        "NTES_PASSPORT"    to   ""
)

val client = OkHttpClient.Builder()
        .cookieJar(object : CookieJar {
            override fun saveFromResponse(url: HttpUrl?, cookies: MutableList<Cookie>?) {
                cookies?.forEach { cookie -> COOKIES[cookie.name()] = cookie.value() }
            }

            override fun loadForRequest(url: HttpUrl?): MutableList<Cookie>? {
                return ArrayList(COOKIES.toList().map { pair -> Cookie.Builder().domain("163.com").name(pair.first).value(pair.second).build() })
            }

        })
        .addInterceptor { chain ->
            chain?.proceed(chain.request()?.newBuilder()
                    ?.apply { header.forEach { entry -> addHeader(entry.key, entry.value) } }?.build())
        }.build()

val api = Retrofit.Builder()
        .baseUrl(host)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(client)
        .build()
        .create(Api::class.java)

interface Api {

    @GET("/api/search/get/web")
    fun search(@Query("s") str: String,
               @Query("type") type: Int = 1,
               @Query("total") total: Boolean = true,
               @Query("offset") offset: Int = 0,
               @Query("limit") limit: Int = 20,
               @Query("__csrf") __csrf: String? = COOKIES["__csrf"]): Call<ResponseBody>
}

object ShopApi {

    val host = "http://app.skyallhere.net:9001"

    val api = Retrofit.Builder()
            .baseUrl(host)
            .addConverterFactory(GsonConverterFactory.create(
                    GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC, Modifier.FINAL).create()))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(Api::class.java)


    interface Api {
        @GET("/CW_APPInterfaceServer/shoplist.do?param=eyJjaXR5Ijoi5rex5Zyz5biCIiwiaW5mb19pZCI6MCwidmVyIjowLCJrZXl2YWx1ZSI6IjMxNTE2YTAxNjYxNGNlNzY4MTA2YjJmMWUzNDRhZGNkIiwicmQiOiIxNzYwNTIifQ%3D%3D")
        fun getShops(): Observable<Shops>
    }
}