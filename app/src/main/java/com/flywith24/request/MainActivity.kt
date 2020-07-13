package com.flywith24.request

import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.flywith24.activityresult.takePicture
import com.flywith24.activityresult.takePicturePreview

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    /**
     * 拍照
     */
    fun takePictureClick(view: View) {
        takePicture { path ->
            Log.i(TAG, "take picture success path = $path")
        }
    }


    fun takePicturePreviewClick(view: View) {
        takePicturePreview { bitmap ->
            findViewById<ImageView>(R.id.image).setImageBitmap(bitmap)
        }
    }


    companion object {
        private const val TAG = "MainActivity"
    }
}