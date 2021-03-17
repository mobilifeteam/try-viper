package com.raywenderlich.chuckyfacts.utils

import android.os.Build

object DeviceUtils {
    val manufacturerSerialNumber: String
        get() {
            var serial = ""
            try {
                val c = Class.forName("android.os.SystemProperties")
                val get = c.getMethod("get", String::class.java, String::class.java)
                serial = get.invoke(c, "ril.serialnumber", "unknown") as String
            } catch (ignored: Exception) {
            }
            return serial
        }
}