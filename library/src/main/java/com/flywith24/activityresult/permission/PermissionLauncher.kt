package com.flywith24.activityresult.permission

import androidx.activity.result.contract.ActivityResultContracts
import com.flywith24.activityresult.BaseLauncher
import com.flywith24.activityresult.dsl.permission.PermissionBuilder

/**
 * @author yyz (杨云召)
 * @date   2020/11/27
 * time   11:52
 * description
 */
class PermissionLauncher :
    BaseLauncher<String, Boolean>(ActivityResultContracts.RequestPermission()) {
    var granted: (permission: String) -> Unit = {}
    var denied: (permission: String) -> Unit = {}
    var explained: (permission: String) -> Unit = {}
    var permission: String = ""

    fun lunch(permission: String, builderPermission: PermissionBuilder.() -> Unit) {
        val builder = PermissionBuilder()
        builder.builderPermission()
        this.granted = builder.granted
        this.explained = builder.explained
        this.denied = builder.denied
        this.permission = permission
        launcher.launch(permission)
    }

    override fun onActivityResult(result: Boolean?) {
        when {
            result == true -> granted.invoke(permission)
            else -> explained.invoke(permission)
        }
    }
}