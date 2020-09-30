<!---   [下载](#下载)-->
<!---   [概述](#概述)-->
<!---   [基础使用](#基础使用)-->
<!--    -   [初始化SDK](#初始化SDK)-->
<!--    -   [获取Context](#获取Context)-->
<!--    -   [页面继承](#页面继承)-->
<!--    -   [RecyclerView适配器](#RecyclerView适配器)-->
<!--    -   [简单使用MVVM](#简单使用MVVM)-->
<!--    -   [Retrofit使用](#Retrofit使用)-->
<!--    -   [命名规范](#命名规范)-->
<!--    -   [扩展方法](#扩展方法)-->
<!--        -   [Activity](#Activity)-->
<!--        -   [Fragment](#Fragment)-->
<!--        -   [Boolean](#Boolean)-->
<!--        -   [Context](#Context)-->
<!--        -   [File](#File)-->
<!--        -   [View](#View)-->


#   下载
在build.gradle中添加

`implementation 'com.lib:cya_frame:1.0.2'`

#	概述
view:ViewBinding

网络:协程+retrofit2+OkHttp

框架:MVVM+ViewBinding+LiveData

#   基础使用

##  初始化SDK

在Application中调用
```kotlin
CyaSDK.initApplication()
```

##  获取Context
```kotlin
CyaSDK.getContext()
```

##  页面继承

提供MVVM模式与普通模式Activity与Fragment

```kotlin
abstract class BaseActivity<V : ViewBinding>

abstract class BaseFragment<V : ViewBinding>

abstract class BaseMVVMActivity<V : ViewBinding, VM : BaseViewModel<V, *>>

abstract class BaseMVVMFragment<V : ViewBinding, VM : BaseViewModel<V, *>>
```

##  RecyclerView适配器

事例:
```kotlin
class HomeListAdapter : BaseRecyclerAdapter<Article>(R.layout.adapter_home_list) {
    override fun getLayoutManager(): LinearLayoutManager {
        return LinearLayoutManager(mContext)
    }

    override fun convert(helper: BaseViewHolder, item: Article) {
        helper.setText(R.id.tv_msg, item.title)
    }

}
```

##  简单使用MVVM

使用携程+LiveData处理业务，类库已封装`UIState<T>`用于发射数据

```kotlin
fun <T> emit(type: Class<T>, value: UIState<T>)

fun <T> getObservable(type: Class<T>)
```

```kotlin
open class UIState<T>(
    var state: State = State.SUCCESS,
    // 数据
    var data: T? = null,
    //成功或者错误的 code 以及 message
    var code: Int? = null,
    var msg: String? = null,
    //预留字段
    var ext: Any? = null
)
```

以下以登陆模块为事例:

### LoginActivity

用于界面操作修改等不涉及业务逻辑内容

```kotlin
class LoginActivity : DemoMVVMActivity<ActivityLoginBinding, LoginViewModel>() {


    override fun initViewModel(): LoginViewModel {
        return getViewModel()
    }

    override fun getViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun showLoading() {
        super.showLoading()
        showCommonProgress()
    }

    override fun hideLoading() {
        super.hideLoading()
        dismissCommonProgress()
    }

    override fun startObserve() {
        super.startObserve()
        vm.apply {
            getObservable(UserResult::class.java).observe(
                this@LoginActivity,
                Observer<UIState<UserResult>> {
                    when (it.state) {
                        State.SUCCESS -> {
                            binding.tvMsg.text = it.data?.nickname
                            finish()
                        }
                        State.FAILED -> {
                            it.msg?.let { it1 -> toast(it1) }
                        }
                    }
                })
            getObservable(Int::class.java).observe(
                this@LoginActivity,
                Observer {
                    binding.btnTest.text = it.data.toString()
                }
            )
        }
    }

    override fun initView() {
        binding.btnLogin.setOnClickListener {
            vm.loginWanAndroid(
                binding.etPhone.text.toString().trim(),
                binding.etCode.text.toString().trim()
            )
        }
        binding.btnRegister.setOnClickListener {
            vm.registerWanAndroid(
                binding.etPhone.text.toString().trim(),
                binding.etCode.text.toString().trim()
            )
        }
        binding.btnTest.setOnClickListener {
            vm.testButton()
        }
    }

    override fun initData() {
    }

}
```

### LoginRepository

用于处理数据，包括网络获取和本地数据

```kotlin
class LoginRepository(private val loginApi: LoginAPI) : DemoBaseRepository() {

    fun logoutWanAndroid(action: (() -> Unit)? = null) {
        Config.Account.clearUserCache()
        action?.invoke()
    }

    suspend fun requestRegisterWanAndroid(
        username: String,
        password: String
    ): BaseResult<UserResult> {
        val mutableMap = mutableMapOf<String, Any?>()
        mutableMap["username"] = username
        mutableMap["password"] = password
        mutableMap["repassword"] = password
        val response = loginApi.registerWanAndroid(mutableMap)
        return executeResponse(response, {
            Config.Account.saveUserCache(it)
        })
    }

    suspend fun requestLoginWanAndroid(
        username: String,
        password: String
    ): BaseResult<UserResult> {
        val mutableMap = mutableMapOf<String, Any?>()
        mutableMap["username"] = username
        mutableMap["password"] = password
        val response = loginApi.loginWanAndroid(mutableMap)
        return executeResponse(response, {
            Config.Account.saveUserCache(it)
        })
    }
}
```

### LoginViewModel

用于处理业务逻辑

```kotlin
class LoginViewModel(repository: LoginRepository) :
    DemoBaseViewModel<ActivityLoginBinding, LoginRepository>(repository) {

    var testCount = 0
    fun testButton() {
        emit(Int::class.java, UIState(data = ++testCount))
    }

    fun loginWanAndroid(username: String, password: String) {
        launchResult({
            repository.requestLoginWanAndroid(username, password)
        }, {
            emitUIState(userInfo = it)
            //通知登陆成功
            LiveEventBus.get(Contract.EventKey.User.UPDATE_INFO, UserResult::class.java)
                .post(it)
        }, {
            emitUIState(state = State.FAILED, errorMsg = it)
        })
    }

    fun registerWanAndroid(username: String, password: String) {
        launchResult({
            repository.requestRegisterWanAndroid(username, password)

        }, {
            emitUIState(userInfo = it)
            //通知登陆成功
            LiveEventBus.get(Contract.EventKey.User.UPDATE_INFO, UserResult::class.java)
                .post(it)
        }, {
            emitUIState(state = State.FAILED, errorMsg = it)
        })
    }


    private fun emitUIState(
        state: State = State.SUCCESS,
        userInfo: UserResult? = null,
        errorMsg: String? = null
    ) {
        emit(UserResult::class.java, UIState(state = state, data = userInfo, msg = errorMsg))
    }

}
```
##  Retrofit使用

使用Retrofit+协程，Retrofit内部已对线程进行处理，接口需要加上`suspend`

接口数据返回

```json
{
    "data": {
        "id": 123456,
        "nickname": "test_account"
    },
    "errorCode": 0,
    "errorMsg": ""
}
```

此处拆分为通用返回与实体，即data才是需要的实体

```kotlin
data class HttpBaseResponse<T>(
    @SerializedName("error_code", alternate = ["errorCode"])
    var errorCode: Int,
    @SerializedName("message", alternate = ["errorMsg"])
    var errorMsg: String = "",
    @SerializedName("data")
    var data: T? = null
)
```

```kotlin
data class UserResult(
    @SerializedName("nickname") var nickname: String
)
```

创建接口
```kotlin
@FormUrlEncoded
    @POST("user/login")
    suspend fun loginWanAndroid(@FieldMap map: MutableMap<String, Any?>): HttpBaseResponse<UserResult>
```

接口封装
```kotlin
 suspend fun requestLoginWanAndroid(
        username: String,
        password: String
    ): BaseResult<UserResult> {
        val mutableMap = mutableMapOf<String, Any?>()
        mutableMap["username"] = username
        mutableMap["password"] = password
        val response = loginApi.loginWanAndroid(mutableMap)
        return executeResponse(response, {
            Config.Account.saveUserCache(it)
        })
    }
```

接口使用
```kotlin
 fun loginWanAndroid(username: String, password: String) {
        viewModelScope.launch {
            try {
                repository.requestLoginWanAndroid(username, password).run {
                    when (this) {
                        is BaseResult.Success -> {
                            emitUIState(userInfo = this.data)
                        }
                        is BaseResult.Failed -> {
                            emitUIState(state = State.FAILED, errorMsg = this.errorMsg)
                        }
                    }
                }


            }catch (e:Exception){
                //根据需求处理异常
                ExceptionEngine.handleException(e)
            }

        }
    }
```


##  命名规范


描述 |  类型 | 命名规范 |  事例
-|-|-
页面  |   Activity | **Activity | LoginActivity |
页面  |   Fragment | **Fragment | HomeFragment |
数据管理  |   Repository | **Repository | LoginRepository |
viewModel  |   ViewModel | **ViewModel | LoginViewModel |
实体模型(网络返回)  |  data  | **Result | LoginResult |
实体模型  |   data | **Info | ArticleInfo |
扩展类  |  ext  | **Ext | FileExt |
Retrofit接口API  |  api  | **API | LoginAPI |



##  扩展方法

### [Activity](https://github.com/troyzqmingming/frame_cya/blob/master/frame/src/main/java/com/cya/frame/ext/ActivityExt.kt)

####    跳转页面

```kotlin
fun startActivity(mClass: Class<A>,action: String? = null,bundle: Bundle? = null) 
```

####    软键盘控制

```kotlin
fun Activity.hideKeyboard()

fun Activity.showKeyboard(et: EditText)

fun Activity.hideKeyboard(view: View) 
```

### [Fragment](https://github.com/troyzqmingming/frame_cya/blob/master/frame/src/main/java/com/cya/frame/ext/FragmentExt.kt)

####    跳转页面

```kotlin
fun startActivity(mClass: Class<A>,action: String? = null,bundle: Bundle? = null) 
```

### [Boolean](https://github.com/troyzqmingming/frame_cya/blob/master/frame/src/main/java/com/cya/frame/ext/BooleanExt.kt)

使用`().yes` ,`().no`代替if()else

事例
```kotlin
(vm.curPageId == 0).yes {
    binding.refreshLayout.finishRefresh()
}.otherwise {
     binding.refreshLayout.finishLoadMore()
}
```

### [Context](https://github.com/troyzqmingming/frame_cya/blob/master/frame/src/main/java/com/cya/frame/ext/ContextExt.kt)

####    APP版本获取

```kotlin
val Context.versionName: String

val Context.versionCode: Long
```

####    设备
```kotlin
设备宽度
val Context.screenWidth

设备高度
val Context.screenHeight

fun Context.dp2Px(dp: Int): Int

fun Context.px2Dp(px: Int): Int
```

####    功能使用
```kotlin
打电话
fun Context.call(phone: String)

发短信
fun Context.sendSMS(phone: String, msg: String) 
```

### [File](https://github.com/troyzqmingming/frame_cya/blob/master/frame/src/main/java/com/cya/frame/ext/FileExt.kt)

```kotlin
获取文件夹或文件总大小
val File.totalSize: Long

格式化大小 如MB GB
val File.formatSize: String
```

### [View](https://github.com/troyzqmingming/frame_cya/blob/master/frame/src/main/java/com/cya/frame/ext/ViewExt.kt)

```kotlin
fun View.visible()

fun View.gone()

fun View.inVisible()
```



