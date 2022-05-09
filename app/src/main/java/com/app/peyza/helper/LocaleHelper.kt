package com.app.peyza.helper

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import com.app.peyza.data.local.UserManager
import java.util.*

/**
 * This class is used to change your application locale and persist this change for the next time
 * that your app is going to be used.
 *
 *
 * You can also change the locale of your application on the fly by using the setLocale method.
 *
 */
object LocaleHelper {


    fun onAttach(context: Context?): Context? {
        return setLocale(context, UserManager.getDefaultLocale())
    }


    fun setLocale(context: Context?, locale: String?): Context? {
        persist(locale)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, locale)
        } else updateResourcesLegacy(context, locale)

    }

    private fun persist(locale: String?) {
        UserManager.saveDefaultLocale(locale ?: "")
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context?, locale: String?): Context? {
        val localeSelected = Locale(locale ?: "")

        Locale.setDefault(localeSelected)

        val configuration = context?.resources?.configuration


        configuration?.setLayoutDirection(localeSelected)
        configuration?.setLocale(localeSelected)

        return context?.createConfigurationContext(configuration ?: Configuration())
    }

    private fun updateResourcesLegacy(context: Context?, locale: String?): Context? {
        val localeSelected = Locale(locale ?: "")

        Locale.setDefault(localeSelected)

        val resources = context?.resources

        val configuration = resources?.configuration
        configuration?.setLayoutDirection(localeSelected)
        configuration?.locale = localeSelected

        resources?.updateConfiguration(configuration, resources.displayMetrics)

        return context
    }

}