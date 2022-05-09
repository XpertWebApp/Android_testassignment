package com.app.peyza.data.local

import com.app.peyza.utils.Constants.DEVICE_TOKEN
import com.app.peyza.utils.Constants.GUEST_USER
import com.app.peyza.utils.Constants.LOCAL
import com.c2c.data.local.PrefsManager


object UserManager {
    fun saveToken(accessToken: String) {
        PrefsManager.get().save(PrefsManager.PREF_ACCESS_TOKEN, "Bearer $accessToken")
    }

    fun getAccessToken(): String {
        return PrefsManager.get().getString(PrefsManager.PREF_ACCESS_TOKEN, "") ?: ""
    }

    fun getDeviceToken(): String? {
        return PrefsManager.get().getString(DEVICE_TOKEN, "")
    }

    fun saveDefaultLocale(lang: String) {
        PrefsManager.get().save(LOCAL, lang)
    }

    fun saveDeviceToken(token: String) {
        PrefsManager.get().save(DEVICE_TOKEN, token)
    }

    fun saveIsGuestUser(isGuest: Boolean) {
        PrefsManager.get().save(GUEST_USER, isGuest)
    }

    fun getDefaultLocale(): String? {
        return PrefsManager.get().getString(LOCAL, null)
    }

    fun getIsGuestUser(): Boolean? {
        return PrefsManager.get().getBoolean(GUEST_USER, false)
    }

}