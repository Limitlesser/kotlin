package wind.kotlin.model

/**
 * Created by pc on 2016/4/22.
 */

data class Shop(var hotline: String,
                var shop_image: String,
                var city: String,
                var manager_portrait: String,
                var id: Int,
                var area: String,
                var address: String,
                var manager_tel: String,
                var name: String,
                var province: String,
                var manager_name: String,
                var longitude: Double,
                var latitude: Double)

data class Shops(var shops: List<Shop>)