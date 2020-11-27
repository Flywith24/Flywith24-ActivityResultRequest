package com.flywith24.activityresult.permission

import androidx.activity.result.contract.ActivityResultContracts
import com.flywith24.activityresult.BaseLauncher

/**
 * @author yyz (杨云召)
 * @date   2020/11/27
 * time   16:52
 * description
 */
class MultiPermissionLauncher :
    BaseLauncher<Array<String>, Map<String, Boolean>>(ActivityResultContracts.RequestMultiplePermissions()) {

    var denied: (List<String>) -> Unit = {}
    var explained: (List<String>) -> Unit = {}
    var allGranted: () -> Unit = {}


    fun lunch(
        permissions: Array<String>,
        denied: (List<String>) -> Unit = {},
        explained: (List<String>) -> Unit = {},
        allGranted: () -> Unit = {}
    ) {
        this.denied = denied
        this.explained = explained
        this.allGranted = allGranted

        launcher.launch(permissions)
    }

    override fun onActivityResult(result: Map<String, Boolean>?) {
        //过滤 value 为 false 的元素并转换为 list
        val deniedList = result!!.filter { !it.value }.map { it.key }
        when {
            deniedList.isNotEmpty() -> {
                //对被拒绝全选列表进行分组，分组条件为是否勾选不再询问
                val map = deniedList.groupBy { permission ->
                    if (activity.shouldShowRequestPermissionRationale(permission)) DENIED else EXPLAINED
                }
                //被拒接且没勾选不再询问
                map[DENIED]?.let { denied.invoke(it) }
                //被拒接且勾选不再询问
                map[EXPLAINED]?.let { explained.invoke(it) }
            }
            else -> allGranted.invoke()
        }
    }

    companion object {
        const val DENIED = "DENIED"
        const val EXPLAINED = "EXPLAINED"
    }
}
