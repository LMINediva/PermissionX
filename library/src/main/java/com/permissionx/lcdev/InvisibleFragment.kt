package com.permissionx.lcdev

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

// 使用typealias关键字给任意类型指定别名
typealias PermissionCallback = ((Boolean, List<String>) -> Unit)

/**
 * 不可见的Fragment
 */
class InvisibleFragment : Fragment() {

    // 运行时权限申请结果的回调通知方式
    private var callback: PermissionCallback? = null

    // 由外部调用方自主指定申请权限的功能
    fun requestNow(cb: PermissionCallback, vararg permissions: String) {
        callback = cb
        requestPermissions(permissions, 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            // 所有被用户拒绝的权限
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            // 所有申请的权限均已被授权标识
            val allGranted = deniedList.isEmpty()
            // 使用callback变量对运行时权限的申请结果进行回调
            callback?.let { it(allGranted, deniedList) }
        }
    }

}