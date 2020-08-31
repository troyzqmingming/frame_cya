package com.cya.frame.media

import android.content.ContentValues
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import com.cya.frame.CyaSDK
import com.cya.frame.ext.otherwise
import com.cya.frame.ext.yes
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream


/**
 * 存储下载
 */
object DownloadMedia {

    enum class Type {
        DOWNLOAD
    }

    private val resolver = CyaSDK.getContext().contentResolver

    /**
     * 保存至download
     */
    fun saveToLocal(fileName: String, file: File, callback: (Boolean) -> Unit) {
        val ops = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            getInsertUri(Type.DOWNLOAD, fileName)?.let {
                resolver.openOutputStream(it)
            }
        } else {
            val image = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    .toString(), fileName
            )
            FileOutputStream(image)
        }
        ops?.let {
            val input = file.inputStream()
            var total: Long = 0
            writeIO(input, it) { len ->
                total += len
            }.yes {
                callback.invoke(file.length() == total)
            }.otherwise {
                callback.invoke(false)
            }
            input.close()
            it.close()
        }

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun getInsertUri(type: Type, fileName: String): Uri? {
        return when (type) {
            Type.DOWNLOAD -> {
                resolver.insert(
                    MediaStore.Downloads.EXTERNAL_CONTENT_URI,
                    typeDownload(fileName)
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun typeDownload(fileName: String): ContentValues {
        return ContentValues().apply {
            //文件名
            put(MediaStore.Downloads.DISPLAY_NAME, fileName)
            //设置文件类型
            put(MediaStore.Downloads.MIME_TYPE, "application/vnd.android.package-archive")
            put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
        }
    }

    /**
     * write
     */
    private fun writeIO(
        bufferedInputStream: InputStream,
        outputStream: OutputStream, listener: ((Long) -> Unit)? = null
    ): Boolean {
        try {
            var len: Int
            bufferedInputStream.use { input ->
                outputStream.use { output ->
                    val buffer = ByteArray(1024)
                    while (input.read(buffer).also { len = it } != -1) {
                        output.write(buffer, 0, len)
                        output.flush()
                        listener?.invoke(len.toLong())
                    }
                }
            }
        } catch (t: Throwable) {
            return false
        }
        return true
    }

}