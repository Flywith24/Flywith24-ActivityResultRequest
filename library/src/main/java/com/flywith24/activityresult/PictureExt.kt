package com.flywith24.activityresult

import android.Manifest
import android.os.Environment.DIRECTORY_PICTURES
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.flywith24.activityresult.dsl.permission.permission
import java.io.File

/**
 * @author Flywith24
 * @date   2020/7/11
 * time   17:20
 * description
 */

inline fun ComponentActivity.takeVideo(
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
                    "${this@takeVideo.applicationContext}.chooseProvider",
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