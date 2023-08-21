package uz.mbr.quiz

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    private val mySharedPref: SharedPreferences

    init {
        mySharedPref = context.getSharedPreferences("AppConfig", Context.MODE_PRIVATE)
    }

    // Best Result
    fun loadBestResultScore(): Int {
        return mySharedPref.getInt("bestResult", 0)
    }

    fun setBestResultScore(number: Int) {
        mySharedPref.edit().putInt("bestResult", number).apply()
    }

    // Night Mode
    fun setNightModeState(state: Boolean?) {
        val editor = mySharedPref.edit()
        editor.putBoolean("night_mode", state!!)
        editor.apply()
    }

    fun loadNightModeState(): Boolean {
        return mySharedPref.getBoolean("night_mode", false)
    }
}