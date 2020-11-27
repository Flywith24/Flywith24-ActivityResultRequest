package com.flywith24.activityresult

import android.Manifest
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import java.io.File

/**
 * @author yyz (杨云召)
 * @date   2020/11/27
 * time   15:57
 * description
 */
class TakeVideoLauncher : BasePictureLauncher<Uri, Bitmap>(ActivityResultContracts.TakeVideo()) {
    var onError: (message: String) -> Unit = {}
    var onSuccess: (path: String) -> Unit = {}
    var path: String = ""

    fun lunch(
        onError: (message: String) -> Unit = {},
        onSuccess: (path: String) -> Unit = {}
    ) {
        this.onError = onError
        this.onSuccess = onSuccess
        camera.lunch(Manifest.permission.CAMERA) {
            granted = {
                path =
                    "${context.getExternalFilesDir(Environment.DIRECTORY_MOVIES)?.absolutePath}/${System.currentTimeMillis()}.mp4"
                val fileUri =
                    FileProvider.getUriForFile(
                        context,
                        "${context.applicationContext.packageName}.chooseProvider",
                        File(path)
                    )
                launcher.launch(fileUri)
            }
            denied = {
                onError.invoke("camera permission denied")
            }
            explained = {
                onError.invoke("camera permission denied")
            }
        }
    }

    override fun onActivityResult(result: Bitmap?) {
        onSuccess.invoke(path)
    }
}