package com.muhammetcakir.turizmacentasi.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muhammetcakir.turizmacentasi.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sett)
        supportActionBar!!.hide()
    }
}