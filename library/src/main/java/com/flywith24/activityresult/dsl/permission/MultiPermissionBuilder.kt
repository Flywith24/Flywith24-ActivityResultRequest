package com.flywith24.activityresult.dsl.permission

/**
 * @author Flywith24
 * @date   2020/6/28
 * time   14:08
 * description
 */

class MultiPermissionBuilder {
    var allGranted: () -> Unit = {}
    var denied: (List<String>) -> Unit = {}
    var explained: (List<String>) -> Unit = {}
}