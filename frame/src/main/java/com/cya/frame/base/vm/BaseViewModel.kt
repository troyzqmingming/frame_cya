package com.cya.frame.base.vm

import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.cya.frame.retrofit.BaseResult

abstract class BaseViewModel<V : ViewBinding, R : BaseRepository>(
    val binding: V,
    val repository: R
) :
    ViewModel() {

    val mContext = binding.root.context

    /**
     * 检测结果
     * @param result 结果
     * @param success 成功
     * @param failed 失败 string0:异常信息 string1:自定义信息
     */
    open fun <T : Any> checkResult(
        result: BaseResult<T>,
        success: (T?) -> Unit,
        failed: (String?, String?) -> Unit
    ) {
        when (result) {
            is BaseResult.Success -> {
                success(result.data)
            }
            is BaseResult.Failed -> {
                failed(result.exception?.message, result.errorMsg)
            }
        }
    }
}