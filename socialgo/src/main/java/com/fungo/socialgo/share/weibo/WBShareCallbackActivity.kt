package com.fungo.socialgo.share.weibo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fungo.socialgo.share.SocialApi
import com.fungo.socialgo.share.config.PlatformConfig
import com.fungo.socialgo.share.config.PlatformType
import com.sina.weibo.sdk.constant.WBConstants
import com.sina.weibo.sdk.share.WbShareCallback

/**
 * @author Pinger
 * @since 18-7-20 下午3:53
 * 微博分享后的回调
 */

class WBShareCallbackActivity : AppCompatActivity(), WbShareCallback {

    private var mSinaWBHandler: SinaWBHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.mSinaWBHandler = SocialApi.getSSOHandler(PlatformType.SINA_WB) as SinaWBHandler
        this.mSinaWBHandler!!.onCreate(this, PlatformConfig.getPlatformConfig(PlatformType.SINA_WB))

        if (this.intent != null) {
            this.handleIntent(this.intent)
        }
    }


    override fun onNewIntent(paramIntent: Intent) {
        super.onNewIntent(paramIntent)
        this.mSinaWBHandler = SocialApi.getSSOHandler(PlatformType.SINA_WB) as SinaWBHandler
        this.mSinaWBHandler!!.onCreate(this, PlatformConfig.getPlatformConfig(PlatformType.SINA_WB))

        this.handleIntent(this.intent)
    }

    private fun handleIntent(intent: Intent) {
        this.mSinaWBHandler?.onNewIntent(intent, this)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        this.mSinaWBHandler?.onActivityResult(requestCode, resultCode, data)
    }


    override fun onWbShareFail() {
        this.mSinaWBHandler?.onResponse(WBConstants.ErrorCode.ERR_FAIL, "onWbShareFail")
        finish()
    }

    override fun onWbShareCancel() {
        this.mSinaWBHandler?.onResponse(WBConstants.ErrorCode.ERR_CANCEL, "")
        finish()
    }

    override fun onWbShareSuccess() {
        this.mSinaWBHandler?.onResponse(WBConstants.ErrorCode.ERR_OK, "")
        finish()
    }
}