package com.flywith24.request

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Flywith24
 * @date   2020/7/14
 * time   10:08
 * description
 */
class SecondActivity : AppCompatActivity(R.layout.activity_second) {
    fun back(view: View) {
        setResult(Activity.RESULT_OK, Intent().putExtra("Configs.LOCATION_RESULT", "result"))
        finish()
    }

}