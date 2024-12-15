package com.example.slamdunks

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.example.slamdunks.R

class AvatarSelectionActivity : AppCompatActivity() {

    private val avatarList = listOf(
        R.drawable.boy1, R.drawable.boy2, R.drawable.boy3,
        R.drawable.girl1, R.drawable.girl2, R.drawable.girl3,

    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avatar_selection)

        val gridView = findViewById<GridView>(R.id.avatarGridView)
        val adapter = AvatarAdapter2(this, avatarList)
        gridView.adapter = adapter

        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedAvatar = avatarList[position]
            val resultIntent = Intent().apply {
                putExtra("selectedAvatar", selectedAvatar)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
