package com.permissionx.lcdev

import androidx.fragment.app.FragmentActivity

/**
 * 对外接口
 */
object PermissionX {

    private const val TAG = "InvisibleFragment"

    // FragmentActivity是AppCompatActivity的父类
    fun request(
        activity: FragmentActivity, vararg permissions: String, callback:
        PermissionCallback
    ) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            // 添加到Activity中
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        // 申请运行时权限，*表示将一个数组转换成可变长度参数
        fragment.requestNow(callback, *permissions)
    }

}