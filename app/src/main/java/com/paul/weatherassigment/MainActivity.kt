package com.paul.weatherassigment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.paul.weatherassigment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        when (UserManager.readData("greet")) {
            true -> {
                UserManager.saveData(false)
            }
            false -> {
                Toast.makeText(this, "歡迎回來", Toast.LENGTH_SHORT).show()
            }

        }
    }
}