package wind.kotlin.model

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table

/**
 * Created by pc on 2016/4/22.
 */
@Table(name = "shop")
class Shop() : Model() {

    @Column(name = "hotline")
    var hotline: String? = null

    @Column(name = "city")
    var city: String? = null

    @Column(name = "manager_portrait")
    var manager_portrait: String? = null

    @Column(name = "_id", unique = true, notNull = true)
    var id: Int? = null

    @Column(name = "area")
    var area: String? = null

    @Column(name = "address")
    var address: String? = null

    @Column(name = "manager_tel")
    var manager_tel: String? = null

    @Column(name = "name")
    var name: String? = null

    @Column(name = "province")
    var province: String? = null

    @Column(name = "manager_name")
    var manager_name: String? = null

    @Column(name = "longitude")
    var longitude: Double? = null

    @Column(name = "latitude")
    var latitude: Double? = null
}

data class Shops(var shops: List<Shop>)