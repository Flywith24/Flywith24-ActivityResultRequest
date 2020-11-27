package com.flywith24.activityresult

import android.Manifest
import android.graphics.Bitmap
import androidx.activity.result.contract.ActivityResultContracts

/**
 * @author yyz (杨云召)
 * @date   2020/11/27
 * time   15:01
 * description
 */
class TakePicturePreviewLauncher :
    BasePictureLauncher<Void, Bitmap>(ActivityResultContracts.TakePicturePreview()) {
    var onSuccess: (bitmap: Bitmap) -> Unit = {}
    var onError: (message: String) -> Unit = {}

    /**
     * 打开相机拍照，无需手动请求权限，内部已请求
     * [onSuccess] 成功回调，返回图片路径
     */
    fun lunch(
        onError: (message: String) -> Unit = {},
        onSuccess: (bitmap: Bitmap) -> Unit = {}
    ) {
        this.onError = onError
        this.onSuccess = onSuccess
        camera.lunch(Manifest.permission.CAMERA) {
            granted = {
                launcher.launch(null)
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
        if (result != null) {
            onSuccess.invoke(result)
        } else {
            onError.invoke("result is null")
        }
    }
}