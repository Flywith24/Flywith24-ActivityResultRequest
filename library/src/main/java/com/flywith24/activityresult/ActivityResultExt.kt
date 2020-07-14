package com.flywith24.activityresult

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

/**
 * @author Flywith24
 * @date   2020/7/14
 * time   10:47
 * description
 */
inline fun ComponentActivity.launchForResult(
    intent: Intent,
    crossinline onError: (resultCode: Int) -> Unit = {},
    crossinline onSuccess: (intent: Intent?) -> Unit = {}
) {
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (Activity.RESULT_OK == it.resultCode) onSuccess.invoke(it.data)
        else onError.invoke(it.resultCode)
    }.launch(intent)
}