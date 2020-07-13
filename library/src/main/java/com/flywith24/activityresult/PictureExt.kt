package com.flywith24.activityresult

import android.graphics.Bitmap
import android.os.Environment.DIRECTORY_PICTURES
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.core.content.FileProvider
import java.io.File

/**
 * @author Flywith24
 * @date   2020/7/11
 * time   17:20
 * description
 */
inline fun ComponentActivity.takePicture(
    crossinline onError: () -> Unit = {},
    crossinline onSuccess: (path: String) -> Unit = {}
) {
    val path =
        "${getExternalFilesDir(DIRECTORY_PICTURES)?.absolutePath}/${System.currentTimeMillis()}.jpg"
    val fileUri = FileProvider.getUriForFile(this, "com.flywith24.picture.fileprovider", File(path))
    registerForActivityResult(ActivityResultContracts.TakePicture()) {
        if (it) onSuccess.invoke(path) else onError.invoke()
    }.launch(fileUri)
}

inline fun ComponentActivity.takePicturePreview(
    crossinline onSuccess: (bitmap: Bitmap) -> Unit = {}
) {
    registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        onSuccess.invoke(it)
    }.launch()
}