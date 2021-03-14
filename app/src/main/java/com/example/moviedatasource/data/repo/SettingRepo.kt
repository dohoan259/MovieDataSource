package com.example.moviedatasource.data.repo

import android.content.SharedPreferences
import com.example.moviedatasource.utils.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingRepo @Inject constructor(
    private val prefs: SharedPreferences
) {

    val isAuthenticated: Boolean = prefs.getBoolean(Constants.KEY_IS_AUTHENTICATED, false)
}