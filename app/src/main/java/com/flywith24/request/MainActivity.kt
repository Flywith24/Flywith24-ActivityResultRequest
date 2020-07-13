package com.flywith24.request

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.flywith24.activityresult.takePicture
import com.flywith24.activityresult.takePicturePreview
import com.flywith24.activityresult.takeVideo

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    /**
     * 拍照，返回图片路径
     */
    fun takePictureClick(view: View) {
        takePicture(BuildConfig.APPLICATION_ID) { path ->
            Log.i(TAG, "take picture success path = $path")
        }
    }

    /**
     * 拍照，返回图片 bitmap
     */
    fun takePicturePreviewClick(view: View) {
        takePicturePreview() { bitmap ->
            findViewById<ImageView>(R.id.image).setImageBitmap(bitmap)
        }
    }

    fun takeVideoClick(view: View) {
        takeVideo(BuildConfig.APPLICATION_ID) { path ->
            Log.i(TAG, "take video success path = $path")
        }
    }


    companion object {
        private const val TAG = "MainActivity"
    }

}