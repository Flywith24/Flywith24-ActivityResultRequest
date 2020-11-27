package com.flywith24.request

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.flywith24.activityresult.*


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val launcher by lazy { PictureLauncher() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(launcher)
    }

    /**
     * 拍照，返回图片路径
     */
    fun takePictureClick(view: View) {
        launcher.lunch() { path ->
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
        takeVideo() { path ->
            Log.i(TAG, "take video success path = $path")
        }
    }

    /**
     * 选择联系人，返回 uri
     */
    fun pickContactClick(view: View) {
        pickContact { uri ->
            Log.i(TAG, "pick contact success uri = $uri")
        }
    }

    /**
     * startActivityForResult
     */
    fun startActivityForResultClick(view: View) {
        launchForResult<SecondActivity> {
            val location = it?.getStringExtra("Configs.LOCATION_RESULT")
            Log.i(TAG, "startActivityForResultClick: $location")
        }

        /*
         launchForResult<SecondActivity>(
            setIntent = {
                //配置请求 intent
            },
            onSuccess = {
                val location = it?.getStringExtra("Configs.LOCATION_RESULT")
                Log.i(TAG, "startActivityForResultClick: $location")
            }
        )*/
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}