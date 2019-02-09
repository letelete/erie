package io.erie.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.erie.R
import io.erie.ui.feed.FeedActivity

class SplashScreenActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.ErieTheme)
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, FeedActivity::class.java))

        finish()
    }
}