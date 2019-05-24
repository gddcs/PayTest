package com.example.paytest

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class MyAccessibilityService : AccessibilityService() {
    var clicked = false;

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.e("tag","onServiceConnected")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
//        Log.e("onAccessibilityEvent",event.toString())
        //获取当前界面的布局信息
        Log.e("tag",event.toString())
        if (clicked)
            return
        val noteInfo: AccessibilityNodeInfo = when {
            rootInActiveWindow != null -> rootInActiveWindow
            event.source != null -> event.source
            else -> return
        }
        val text = arrayOf("好的","重新","刷新")

        for (str in text) {
            val nodeInfoList = noteInfo.findAccessibilityNodeInfosByText(str)
            if (nodeInfoList != null && !nodeInfoList!!.isEmpty()) {
                Log.e("tag", "111111111111")
                for (nodeInfo in nodeInfoList!!) {
                    Log.e("tag", "222222222222222")
                    if (nodeInfo != null && (str.equals(nodeInfo!!.getText()) || str.equals(nodeInfo!!.getContentDescription()))) {
//                    performClick(nodeInfo)
                        Log.e("tag", "3333333333333333")
                        Log.e("tag", "点击info：" + nodeInfo.toString())
                        nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                        break
                    }
                }
            } else {
                Log.e("tag", "000000000000")
            }
        }

    }

    override fun onInterrupt() {

        Log.e("tag","onInterrupt")


    }
}