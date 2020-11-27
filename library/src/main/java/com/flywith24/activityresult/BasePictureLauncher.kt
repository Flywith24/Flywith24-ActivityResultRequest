package com.flywith24.activityresult

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContract
import androidx.lifecycle.LifecycleOwner
import com.flywith24.activityresult.permission.PermissionLauncher

/**
 * @author yyz (杨云召)
 * @date   2020/11/27
 * time   15:01
 * description
 */
abstract class BasePictureLauncher<I, O>(contract: ActivityResultContract<I, O>) :
    BaseLauncher<I, O>(contract) {
    val camera by lazy { PermissionLauncher() }
    lateinit var context: Context

    override fun onCreate(owner: LifecycleOwner) {
        if (owner is ComponentActivity) {
            context = owner
            owner.lifecycle.addObserver(camera)
        }
        super.onCreate(owner)
    }
}