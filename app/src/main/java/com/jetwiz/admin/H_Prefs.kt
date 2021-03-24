package wazma.punjabi.helper

import android.content.Context
import android.content.SharedPreferences

class H_Prefs(val context: Context) {

    private val name = "tbPrefs"
    private val mode = Context.MODE_PRIVATE
    private var sharedPreferences:SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(name, mode)
    }

    fun getPrefs(): SharedPreferences {
        return sharedPreferences
    }

    /**
     * For update and create
     */
    fun setData(keyName:String, value:String): H_Prefs {
        sharedPreferences.edit().putString(keyName, value).apply()
        return this
    }

    /**
     * For update and create
     */
    fun setData(keyName:String, value:Int): H_Prefs {
        sharedPreferences.edit().putInt(keyName, value).apply()
        return this
    }

    /**
     * For update and create
     */
    fun setData(keyName:String, value:Boolean): H_Prefs {
        sharedPreferences.edit().putBoolean(keyName, value).apply()
        return this
    }

    /**
     * For update and create
     */
    fun setData(ctx:Context, keyName:String, value:Int): H_Prefs {
        sharedPreferences.edit().putInt(keyName, value).apply()
        return this
    }

    /**
     * For update and create
     */
    fun setData(keyName:String, value:Long): H_Prefs {
        sharedPreferences.edit().putLong(keyName, value).apply()
        return this
    }

    fun deleteData(keyName:String): H_Prefs {
        sharedPreferences.edit().remove(keyName).apply()
        return this
    }

    /**
     * Delete all cIcon in this SecurePrefences
     */
    fun destroy(){
        sharedPreferences.edit().clear().apply()
    }

    fun isNull(keyName:String):Boolean{
        if (sharedPreferences.getString(keyName, null) == null ){
            return true
        } else {
            return false
        }
    }

    fun isNotNull(keyName:String):Boolean{
        if (sharedPreferences.getString(keyName, null) == null ){
            return false
        } else {
            return true
        }
    }
}