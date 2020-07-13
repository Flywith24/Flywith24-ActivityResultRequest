package com.flywith24.activityresult

import android.Manifest
import android.graphics.Bitmap
import android.os.Environment.DIRECTORY_PICTURES
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.core.content.FileProvider
import com.flywith24.activityresult.dsl.permission.permission
import com.flywith24.activityresult.dsl.permission.permissions
import java.io.File

/**
 * @author Flywith24
 * @date   2020/7/11
 * time   17:20
 * description
 */

/**
 * 打开相机拍照，无需手动请求权限，内部已请求
 * [onSuccess] 成功回调，返回图片路径
 */
inline fun ComponentActivity.takePicture(
    applicationId: String,
    crossinline onError: (message: String) -> Unit = {},
    crossinline onSuccess: (path: String) -> Unit = {}
) {
    //请求拍照权限
    permissions(Manifest.permission.CAMERA) {
        allGranted = {
            val path =
                "${getExternalFilesDir(DIRECTORY_PICTURES)?.absolutePath}/${System.currentTimeMillis()}.jpg"
            val fileUri =
                FileProvider.getUriForFile(
                    this@takePicture,
                    "${applicationId}.fileprovider",
                    File(path)
                )
            registerForActivityResult(ActivityResultContracts.TakePicture()) {
                if (it) onSuccess.invoke(path) else onError.invoke("")
            }.launch(fileUri)
        }
        denied = {
            onError.invoke("camera permission denied")
        }
        explained = {
            onError.invoke("camera permission denied")
        }
    }
}

inline fun ComponentActivity.takePicturePreview(
    crossinline onError: (message: String) -> Unit = {},
    crossinline onSuccess: (bitmap: Bitmap) -> Unit = {}
) {
    //请求拍照权限
    permission(Manifest.permission.CAMERA) {
        granted = {
            registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
                onSuccess.invoke(it)
            }.launch()
        }
        denied = {
            onError.invoke("camera permission denied")
        }
        explained = {
            onError.invoke("camera permission denied")
        }
    }
}

inline fun ComponentActivity.takeVideo(
    applicationId: String,
    crossinline onError: (message: String) -> Unit = {},
    crossinline onSuccess: (path: String) -> Unit = {}
) {
    //请求拍照权限
    permission(Manifest.permission.CAMERA) {
        granted = {
            val path =
                "${getExternalFilesDir(DIRECTORY_PICTURES)?.absolutePath}/${System.currentTimeMillis()}.mp4"
            val fileUri =
                FileProvider.getUriForFile(
                    this@takeVideo,
                    "${applicationId}.fileprovider",
                    File(path)
                )
            registerForActivityResult(ActivityResultContracts.TakeVideo()) {
                onSuccess(path)
            }.launch(fileUri)
        }
        denied = {
            onError.invoke("camera permission denied")
        }
        explained = {
            onError.invoke("camera permission denied")
        }
    }
}