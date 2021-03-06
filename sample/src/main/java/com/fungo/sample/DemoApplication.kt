package com.fungo.sample

import android.app.Application
import com.fungo.socialgo.share.config.PlatformConfig

/**
 * @author Pinger
 * @since 18-7-20 下午4:30
 *
 */

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        PlatformConfig.setQQ(AppConstant.QQ_APP_ID)
        PlatformConfig.setSinaWB(AppConstant.SINA_APP_KEY)
        PlatformConfig.setWeixin(AppConstant.WX_APP_ID)
    }
}