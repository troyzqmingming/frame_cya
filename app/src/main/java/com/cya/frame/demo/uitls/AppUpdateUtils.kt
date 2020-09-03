package com.cya.frame.demo.uitls

import com.allenliu.versionchecklib.callback.APKDownloadListener
import com.allenliu.versionchecklib.v2.AllenVersionChecker
import com.allenliu.versionchecklib.v2.builder.UIData
import com.cya.frame.CyaSDK
import com.cya.frame.ext.yes

object AppUpdateUtils {

    data class UpdateInfo(
        var savePath: String,
        var saveFileName: String,
        var downloadUrl: String,
        /**
         * 是否强制更新
         */
        var force: Boolean = false
    )


    fun checkUpdate(info: UpdateInfo, callback: APKDownloadListener) {
        AllenVersionChecker.getInstance()
            .downloadOnly(UIData.create().setDownloadUrl(info.downloadUrl))
            .apply {
                info.run {
                    force.yes {
                        setForceUpdateListener {
                            //退出APP
                        }
                    }
                    //自定义下载路径
                    setDownloadAPKPath(savePath)
                    //自定义下载文件名
                    setApkName(saveFileName)
                    //下载监听
                    setApkDownloadListener(callback)
                }
            }
            .executeMission(CyaSDK.getContext())
    }


    fun release() {
        AllenVersionChecker.getInstance().cancelAllMission()
    }
}