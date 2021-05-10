package com.repose.practical.mvvm

import android.content.Context
import android.preference.PreferenceManager
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.repose.practical.mvvm.data.model.Article
import com.repose.practical.mvvm.data.model.NewsResponse

class AppPreference(context: Context) {

    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

    private val pref = EncryptedSharedPreferences
        .create(
            context.getString(R.string.app_name),
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun saveData(data: NewsResponse?) {
        val editor = pref.edit()
        val gson = Gson()
        val json = gson.toJson(data)
        editor.putString("data", json)
        editor.apply() // This line is IMPORTANT !!!
    }

    fun getData(): NewsResponse? {
        val gson = Gson()
        val json = pref.getString("data", null)
        val type = object : TypeToken<NewsResponse?>() {}.type
        return gson.fromJson(json, type)
    }
}