package com.cya.module_home.ui.mine

import com.cya.lib_base.vm.CYABaseViewModel
import com.cya.module_user.data.UserContract
import com.cya.module_user.login.LoginRepository
import com.jeremyliao.liveeventbus.LiveEventBus

class MineViewModel(repository: LoginRepository) :
    CYABaseViewModel<LoginRepository>(repository) {

    fun logoutUser() {
        repository.logoutWanAndroid {
            LiveEventBus.get(UserContract.EventKey.User.LOGOUT).post(true)
        }
    }

//    fun downloadFile(
//        url: String,
//        saveName: String = getFileNameFormUrl(url),
//        savePath: String = FILE_PRIVATE_PATH_ROOT
//    ): Disposable? {
//        Logger.i(savePath)
//        return Task(url = url, saveName = saveName, savePath = savePath)
//            .download()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe {
//                Logger.i("文件名:$saveName")
//                Logger.i("文件地址:$savePath")
//                emit(Loading::class.java, UIState(State.LOADING_SHOW))
//            }
//            .doFinally {
//                emit(Loading::class.java, UIState(State.LOADING_HIDE))
//            }
//            .subscribeBy(
//                onNext = {
//                    Logger.i("下载进度:${(it.downloadSize.toDouble() / it.totalSize.toDouble() * 100).toInt()}\t/\t100%")
//                    emit(
//                        Loading::class.java,
//                        UIState(
//                            State.LOADING_SHOW,
//                            msg = "下载进度:${(it.downloadSize.toDouble() / it.totalSize.toDouble() * 100).toInt()}\t/\t100%"
//                        )
//                    )
//                },
//                onComplete = {
//                    Logger.i("下载完成")
//                },
//                onError = {
//                    Logger.i("下载异常${it.message}")
//                }
//            )
//    }
}