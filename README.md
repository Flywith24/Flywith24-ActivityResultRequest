# 最新的 Activity Result API

## startActivityForResult
```kotlin
registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
    // 返回 intent
	val intent = it.data
}.launch(Intent(this, SecondActivity::class.java))
```


## 请求单一权限

``` kotlin
val permission = Manifest.permission.READ_CONTACTS
registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
    when {
        result -> // 成功
        shouldShowRequestPermissionRationale(permission) -> // 失败 未勾选不再提醒
        else -> // 失败 勾选不再提醒
    }
}.launch(permission)
```



## 请求多个权限

``` kotlin
val permissions = arrayof(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result: MutableMap<String, Boolean> ->
    //过滤 value 为 false 的元素并转换为 list
    val deniedList = result.filter { !it.value }.map { it.key }
    when {
        deniedList.isNotEmpty() -> {
            //对被拒绝全选列表进行分组，分组条件为是否勾选不再询问
            val map = deniedList.groupBy { permission ->
                if (shouldShowRequestPermissionRationale(permission)) DENIED else EXPLAINED
            }
            //被拒接且没勾选不再询问
            map[DENIED]?.let { //拒绝列表 }
            //被拒接且勾选不再询问
            map[EXPLAINED]?.let { //不再询问列表 }
        }
        else -> //全部通过
    }
}.launch(permissions)
```



## 打开相机拍照

``` kotlin
val path = "${getExternalFilesDir(DIRECTORY_PICTURES)?.absolutePath}/${System.currentTimeMillis()}.jpg"
val fileUri = FileProvider.getUriForFile(this,"${applicationId}.fileprovider",File(path))
registerForActivityResult(ActivityResultContracts.TakePicture()) {
    if(it){
        //成功
    }
}.launch(fileUri)
```



## 打开相机录像

``` kotlin
val path ="${getExternalFilesDir(DIRECTORY_PICTURES)?.absolutePath}/${System.currentTimeMillis()}.mp4"
val fileUri = FileProvider.getUriForFile(this,"${applicationId}.fileprovider",File(path))
registerForActivityResult(ActivityResultContracts.TakeVideo()) {
    //返回 bitmap
}.launch(fileUri)
```



## 选择联系人

``` kotlin
registerForActivityResult(ActivityResultContracts.PickContact()) {
	// 返回 uri
}.launch()
```

