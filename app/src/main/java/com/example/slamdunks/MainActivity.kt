package com.example.slamdunks
import android.content.Intent
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.slamdunks.GetUsernameActivity
import com.example.slamdunks.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val gifDrawable = ContextCompat.getDrawable(this, R.drawable.gif_kenlogo) as AnimatedImageDrawable
            gifDrawable.repeatCount = 1
            binding.logoImageView.setImageDrawable(gifDrawable)
            gifDrawable.start()
        }
        binding.getStartedButton.setOnClickListener {
            val intent = Intent(this, GetUsernameActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Clear the activity stack
            startActivity(intent)
        }

    }
}
