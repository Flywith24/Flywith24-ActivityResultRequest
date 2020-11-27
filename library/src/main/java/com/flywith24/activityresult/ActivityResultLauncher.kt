package com.flywith24.activityresult

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LifecycleOwner

/**
 * @author yyz (杨云召)
 * @date   2020/11/27
 * time   16:25
 * description
 */
class ActivityResultLauncher :
    BaseLauncher<Intent, ActivityResult>(ActivityResultContracts.StartActivityForResult()) {
    var onError: (resultCode: Int) -> Unit = {}
    var onSuccess: (intent: Intent?) -> Unit = {}

    lateinit var activity: ComponentActivity
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        if (owner is ComponentActivity) {
            this.activity = owner
        }
    }

    inline fun <reified T : Activity> lunch(
        crossinline setIntent: (intent: Intent) -> Unit = {},
        noinline onError: (resultCode: Int) -> Unit = {},
        noinline onSuccess: (intent: Intent?) -> Unit = {}
    ) {
        this.onError = onError
        this.onSuccess = onSuccess
        val intent = Intent(activity, T::class.java)
        //根据配置设置 intent
        setIntent.invoke(intent)
        launcher.launch(intent)
    }

    override fun onActivityResult(result: ActivityResult?) {
        if (Activity.RESULT_OK == result?.resultCode) onSuccess.invoke(result.data)
        else onError.invoke(result?.resultCode ?: Activity.RESULT_CANCELED)
    }
}