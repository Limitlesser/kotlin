package wind.kotlin

import android.app.Application
import com.activeandroid.ActiveAndroid
import com.activeandroid.Configuration
import kotlin.properties.Delegates

/**
 * Created by wind on 2016/5/7.
 */
class App : Application() {

    companion object {
        var instance: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ActiveAndroid.initialize(Configuration.Builder(this).create())
    }
}