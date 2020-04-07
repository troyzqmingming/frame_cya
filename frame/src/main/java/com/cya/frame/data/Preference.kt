package com.cya.frame.data

import android.content.Context
import android.content.SharedPreferences
import com.cya.frame.CyaSDK
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * sharedpreference代理类
 */
class Preference<T>(private val key: String, private val default: T) :
    ReadWriteProperty<Any?, T> {

    private val sharedPreferences: SharedPreferences by lazy {
        CyaSDK.getContext().run {
            getSharedPreferences(packageName, Context.MODE_PRIVATE)
        }
    }

    private fun putValue(key: String, default: T) {
        sharedPreferences.edit().apply {
            when (default) {
                is Boolean -> {
                    putBoolean(key, default)
                }
                is String -> {
                    putString(key, default)
                }
                is Long -> {
                    putLong(key, default)
                }
                is Int -> {
                    putInt(key, default)
                }
                is Float -> {
                    putFloat(key, default)
                }
                else -> {
                    throw ClassNotFoundException("无法识别类型")
                }

            }
        }.apply()
    }

    private fun getValue(key: String, default: T): T {
        sharedPreferences.apply {
            val res: Any? = when (default) {
                is Boolean -> {
                    getBoolean(key, default)
                }
                is String -> {
                    getString(key, default)
                }
                is Long -> {
                    getLong(key, default)
                }
                is Int -> {
                    getInt(key, default)
                }
                is Float -> {
                    getFloat(key, default)
                }
                else -> {
                    throw ClassNotFoundException("无法识别类型")
                }
            }
            return res as T
        }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = getValue(key, default)

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putValue(key, value)
    }

}