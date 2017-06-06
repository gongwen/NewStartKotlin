package com.gw.newstart.kotlin.utils

import com.gw.newstart.kotlin.MainApplication
import java.io.File

/**
 * Created by GongWen on 17/6/5.
 */
class FileUtils {
    companion object {
         fun getCacheDir(uniqueName: String): File {
            val cacheDir = StorageUtils.getCacheDirectory(MainApplication.instance)
            return File(cacheDir, uniqueName)
        }
    }
}