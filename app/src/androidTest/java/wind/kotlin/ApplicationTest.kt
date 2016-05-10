package wind.kotlin

import android.app.Application
import android.test.ApplicationTestCase
import android.util.Log
import com.activeandroid.ActiveAndroid
import com.activeandroid.query.Delete
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import wind.kotlin.model.Shop
import wind.kotlin.network.ShopApi

/**
 * [Testing Fundamentals](http://d.android.com/tools/testing/testing_android.html)
 */
class ApplicationTest : ApplicationTestCase<Application>(Application::class.java) {

    val TAG = "TEST"

    fun testApi() {
        ShopApi.api.getShops().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { shops -> Log.i(TAG, shops.toString()) }
    }

    fun testModel() {
        val shop = Shop()
        shop.name = "sda"
        shop.save()
    }

}