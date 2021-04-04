package me.ranieripieper.android.github

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import me.ranieripieper.android.github.features.navigation.FeaturesNavigation

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            FeaturesNavigation.startRepositoryModule(this)
            finish()
        }, resources.getInteger(R.integer.splash_duration).toLong())

    }
}