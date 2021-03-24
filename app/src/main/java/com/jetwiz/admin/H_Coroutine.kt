package wazma.punjabi.helper

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * untuk menangkap exception dari coroutine
 * secara default, tidak ada
 */
object H_Coroutine {
    private val TAG = "CoroutineException"

    fun getErrorHandler ():CoroutineExceptionHandler {
        val handler = CoroutineExceptionHandler({ coroutineContext, throwable ->
            Log.println(Log.ERROR, TAG, Log.getStackTraceString(throwable))
            throw throwable
        })

        return handler
    }
}