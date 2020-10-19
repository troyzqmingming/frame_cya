package com.cya.plugin

object BuildAndroid {

    const val CompileSdkVersion = 29
    const val BuildToolsVersion = "29.0.2"
    const val MinSdkVersion = 21
    const val TargetSdkVersion = 29
    const val VersionCode = 1
    const val VersionName = "1.0.0"
}

object Versions {
    const val Kotlin = "1.4.10"
    const val Appcompat = "1.2.0"
    const val Annotation = "1.1.0"
    const val CoreKtx = "1.3.1"
    const val ConstraintLayout = "2.0.1"
    const val ViewPager2 = "1.0.0"
    const val Recyclerview = "1.1.0"
    const val Coroutines = "1.3.7"
    const val Koin = "2.1.5"
    const val JetPackNavigation = "2.3.0"
    const val JetPackLifecycleViewModel = "2.2.0"
    const val JetPackLifecycleLiveData = "2.2.0"
    const val JetPackPaging = "3.0.0-alpha05"
    const val Glide = "4.11.0"
    const val Arouter = "1.5.0"
    const val ArouterCompiler = "1.2.2"
    const val Coil = "1.0.0-rc3"
    const val Lottie = "3.4.4"
}

object Dependencies {
    const val KotlinLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin}"
    const val Appcompat = "androidx.appcompat:appcompat:${Versions.Appcompat}"
    const val Annotation = "androidx.annotation:annotation:${Versions.Annotation}"
    const val CoreKtx = "androidx.core:core-ktx:${Versions.CoreKtx}"
    const val ConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.ConstraintLayout}"
    const val ViewPager2 = "androidx.viewpager2:viewpager2:${Versions.ViewPager2}"
    const val Recyclerview = "androidx.recyclerview:recyclerview:${Versions.Recyclerview}"

    //协程
    const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Coroutines}"
    const val CoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutines}"

    //koin
    const val KoinViewModel = "org.koin:koin-android-viewmodel:${Versions.Koin}"

    //jetpack
    const val LifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.JetPackLifecycleViewModel}"
    const val LifecycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.JetPackLifecycleLiveData}"
    const val LifeExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.JetPackLifecycleLiveData}"
    const val Paging = "androidx.paging:paging-runtime:${Versions.JetPackPaging}"
    const val NavigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.JetPackNavigation}"
    const val NavigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.JetPackNavigation}"
    const val NavigationDynamic = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.JetPackNavigation}"

    const val LiveBus = "com.jeremyliao:live-event-bus-x:1.5.7"
    const val Retrofit = "com.squareup.retrofit2:retrofit:2.7.1"
    const val RetrofitGson = "com.squareup.retrofit2:converter-gson:2.6.2"
    const val OkHttpLogging = "com.squareup.okhttp3:logging-interceptor:4.0.0"
    const val LoggingInterceptor = "com.github.ihsanbal:LoggingInterceptor:3.0.0"
    const val Logger = "com.orhanobut:logger:2.2.0"
    const val CheckUpdate = "com.github.AlexLiuSheng:CheckVersionLib:2.4_androidx"
    const val BaseRecyclerViewAdapterHelper =
        "com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.46"
    const val Glide = "com.github.bumptech.glide:glide:${Versions.Glide}"
    const val GlideCompiler =
        "com.github.bumptech.glide:compiler:${Versions.Glide}"
    const val smartRefreshLayout = "com.scwang.smartrefresh:SmartRefreshLayout:1.1.2"
    const val Arouter ="com.alibaba:arouter-api:${Versions.Arouter}"
    const val ArouterCompiler ="com.alibaba:arouter-compiler:${Versions.ArouterCompiler}"
    const val Coil = "io.coil-kt:coil:${Versions.Coil}"
    const val CoilGif = "io.coil-kt:coil-gif:${Versions.Coil}"
    const val Lottie = "com.airbnb.android:lottie:${Versions.Lottie}"
}