package com.cya.frame.base.vm

import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.cya.frame.exception.CyaException
import com.cya.frame.retrofit.BaseResult

abstract class BaseViewModel<V : ViewBinding, R : BaseRepository>(
    val repository: R
) :
    ViewModel() {

    /**
     * 检测结果
     * @param result 结果
     * @param success 成功
     * @param failed 失败 CyaException:异常实体 string1:自定义信息
     */
    open fun <T : Any> checkResult(
        result: BaseResult<T>,
        success: (T?) -> Unit,
        failed: (CyaException?, String?) -> Unit
    ) {
        when (result) {
            is BaseResult.Success -> {
                success(result.data)
            }
            is BaseResult.Failed -> {
                failed(result.exception, result.errorMsg)
            }
        }
    }

}