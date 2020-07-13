package com.flywith24.request

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.flywith24.activityresult.pickContact
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

    /**
     * 拍视频，返回视频 path
     */
    fun takeVideoClick(view: View) {
        takeVideo(BuildConfig.APPLICATION_ID) { path ->
            Log.i(TAG, "take video success path = $path")
        }
    }

    /**
     * 选择联系人，返回 uri
     */
    fun pckContactClick(view: View) {
        pickContact { uri ->
            Log.i(TAG, "pick contact success uri = $uri")

        }
    }


    companion object {
        private const val TAG = "MainActivity"
    }


}