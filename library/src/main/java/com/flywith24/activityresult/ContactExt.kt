package com.flywith24.activityresult

import android.Manifest
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import com.flywith24.activityresult.dsl.permission.permission


/**
 * @author Flywith24
 * @date   2020/7/13
 * time   20:25
 * description
 */

inline fun ComponentActivity.pickContact(
    crossinline onError: (message: String) -> Unit = {},
    crossinline onSuccess: (uri: Uri?) -> Unit = {}
) {
    permission(Manifest.permission.READ_CONTACTS) {
        registerForActivityResult(ActivityResultContracts.PickContact()) {
            onSuccess.invoke(it)
        }.launch()
    }
}