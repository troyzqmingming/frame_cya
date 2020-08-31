package com.cya.frame.media

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.cya.frame.CyaSDK
import com.cya.frame.ext.createFile
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes
import java.io.File
import java.io.FileOutputStream

/**
 * 存储图片
 */
object ImageMedia {

    private val context = CyaSDK.getContext()

    /**
     * 默认缓存路径
     */
    private val cacheDefaultPath = CyaSDK.getContext().getExternalFilesDir("image")?.absolutePath

    /**
     * 本地图片路径 Q
     */
    private val localPathQ = Environment.DIRECTORY_DCIM

    /**
     * 本地图片路径
     */
    private val localPath =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            .toString()

    /**
     * 保存图片到设备，卸载后不删除
     * @param bitmap
     * @param fileName 文件名
     */
    fun saveImageToPhone(
        bitmap: Bitmap,
        fileName: String,
        quality: Int = 90
    ): Boolean {
        val fos = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val resolver: ContentResolver = CyaSDK.getContext().contentResolver
            val imageUri = resolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(MediaStore.MediaColumns.RELATIVE_PATH, localPathQ)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/JPEG")

                })
            imageUri?.let {
                resolver.openOutputStream(it)
            }
        } else {
            val image = File(localPath, fileName)
            FileOutputStream(image)
        }
        val isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fos)
        fos?.flush()
        fos?.close()
        return isSuccess
    }

    /**
     * 保存图片，
     * 默认保存到外部私有文件，android/data/包名/image
     *
     */
    fun saveImageToCache(
        bitmap: Bitmap,
        filePath: String? = cacheDefaultPath,
        fileName: String,
        quality: Int = 90
    ): Boolean {
        filePath?.let {
            //保存到本地
            createFile(it, fileName).apply {
                FileOutputStream(this).let { ops ->
                    val isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, quality, ops)
                    ops.flush()
                    ops.close()
                    return isSuccess
                }
            }
        }
        return false
    }

    /**
     * 获取本地图片
     */
    fun getImageFromLocal(
        fileName: String,
        callback: (Bitmap?) -> Unit
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val external = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val cursor = context.contentResolver?.query(
                external,
                arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME),
                MediaStore.Images.Media.DISPLAY_NAME + "=?",
                arrayOf(fileName), null
            )
            cursor?.let {
                if (it.moveToFirst()) {
                    ContentUris.withAppendedId(external, it.getLong(0)).run {
                        Glide.with(context).asBitmap().load(this)
                            .into(object : CustomTarget<Bitmap>() {
                                override fun onResourceReady(
                                    resource: Bitmap,
                                    transition: Transition<in Bitmap>?
                                ) {
                                    callback(resource)
                                }

                                override fun onLoadCleared(placeholder: Drawable?) {
                                }

                            })
                    }
                }
            }
            cursor?.close()
        } else {
            getImageFromCache(
                localPath, fileName, callback
            )
        }
    }

    /**
     * 获取混存图片
     */
    fun getImageFromCache(
        filePath: String? = cacheDefaultPath,
        fileName: String,
        callback: (Bitmap?) -> Unit
    ) {
        val file = File(filePath, fileName)
        file.exists().yes {
            callback(BitmapFactory.decodeFile(file.absolutePath))
        }.otherwise {
            callback(null)
        }
    }


}