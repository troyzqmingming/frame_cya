#概述

view:ViewBinding

网络:协程+retrofit2+OkHttp

框架:MVVM+ViewBinding+LiveData


##

##命名规范

1.  retrofit接口返回数据

如:

Json response

``
{
	"error_code": 0,
	"message": "登录成功",
	"data": {
		"token": "123123"
	}
}
``

创建返回实体命名`**Response`

`
data class HttpBaseResponse<T>(
    @SerializedName("error_code")
    var errorCode: Int,
    @SerializedName("message")
    var errorMsg: String = "",
    @SerializedName("data")
    var data: T? = null
)
`

创建Data实体命名为`**Result`,如:

`
    data class LoginResult(@SerializedName("token") var token: String)
`

接口例子:

`
    @FormUrlEncoded
    @POST("api/login/mobile/login")
    suspend fun login(@FieldMap map: MutableMap<String, Any?>): HttpBaseResponse<LoginResult>
`

数据转换均转换为`BaseResult<T>`,再返回至相应逻辑

例:

`
open fun <T : Any> convertResponse(response: HttpBaseResponse<T>): BaseResult<T> {
        return when (response.errorCode) {
            0 -> {//登陆成功
                BaseResult.Success(response.data)
            }
            else -> {//登陆失败
                BaseResult.Failed(null, response.errorMsg)
            }
        }
    }
`
