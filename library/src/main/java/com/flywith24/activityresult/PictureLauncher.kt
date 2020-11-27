package com.flywith24.activityresult

import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.lifecycle.LifecycleOwner
import java.io.File

/**
 * @author yyz (杨云召)
 * @date   2020/11/27
 * time   15:01
 * description
 */
class PictureLauncher : BaseLauncher<Uri, Boolean>(ActivityResultContracts.TakePicture()) {
    var onSuccess: (permission: String) -> Unit = {}
    var onError: (permission: String) -> Unit = {}
    var path: String = ""
    private val camera by lazy { PermissionLauncher() }
    lateinit var context: Context

    override fun onCreate(owner: LifecycleOwner) {
        if (owner is ComponentActivity) {
            context = owner
            owner.lifecycle.addObserver(camera)
        }
        super.onCreate(owner)
    }

    /**
     * 打开相机拍照，无需手动请求权限，内部已请求
     * [onSuccess] 成功回调，返回图片路径
     */
    fun lunch(
        onError: (message: String) -> Unit = {},
        onSuccess: (path: String) -> Unit = {}
    ) {
        this.onError = onError
        this.onSuccess = onSuccess
        camera.lunch(Manifest.permission.CAMERA) {
            granted = {
                path =
                    "${context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absolutePath}/${System.currentTimeMillis()}.jpg"
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

    override fun onActivityResult(result: Boolean) {
        if (result) onSuccess.invoke(path) else onError.invoke("")
    }
}