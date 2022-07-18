package com.renatojobal.rocketEngine

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.renatojobal.rocketEngine.timber.DebugTree
import com.renatojobal.rocketEngine.timber.ReleaseTree
import timber.log.Timber

class RocketEngineApp : Application() {
    /**
     * Main method that we use to set up important things
     */
    override fun onCreate() {
        super.onCreate()

        // Set up timber
        setUpTimber()


    }


    /**
     * Timber is a library to log in a better way
     */
    private fun setUpTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
            // Set a key to an int.
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(false)
            Timber.i("Timber set up in DEBUG level")
        } else {
            Timber.plant(ReleaseTree())
            // Set a key to an int.
            FirebaseCrashlytics.getInstance().setCustomKey("Build config", "RELEASE")
        }
    }






}