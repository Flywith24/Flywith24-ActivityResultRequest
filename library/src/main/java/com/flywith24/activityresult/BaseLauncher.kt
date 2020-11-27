package com.flywith24.activityresult

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.CallSuper
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * @author yyz (杨云召)
 * @date   2020/11/27
 * time   15:21
 * description
 */
abstract class BaseLauncher<I, O>(private val contract: ActivityResultContract<I, O>) :
    DefaultLifecycleObserver,
    ActivityResultCallback<O> {
    protected lateinit var launcher: ActivityResultLauncher<I>

    @CallSuper
    override fun onCreate(owner: LifecycleOwner) {
        if (owner is ComponentActivity) {
            launcher = owner.registerForActivityResult(contract, this)
        }
    }

    override fun onActivityResult(result: O) {

    }
}