package com.flywith24.request

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Flywith24
 * @date   2020/7/14
 * time   10:08
 * description
 */
class SecondActivity : AppCompatActivity(R.layout.activity_second) {
    private var request: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        request = intent.getStringExtra("Configs.LOCATION_RESULT")
    }

    fun back(view: View) {
        setResult(
            Activity.RESULT_OK,
            Intent().putExtra("Configs.LOCATION_RESULT", "$request ============ add result")
        )
        finish()
    }

}