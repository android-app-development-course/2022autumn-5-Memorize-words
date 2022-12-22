package com.example.ydword

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val decorView=window.decorView
        decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor= Color.TRANSPARENT

        val button:Button=findViewById(R.id.main_btn)
        val editText:EditText=findViewById(R.id.et_name)

        button.setOnClickListener {
            val intent= Intent(this,InputWordsActivity::class.java)
            val bundle=Bundle()
            bundle.putString("name",editText.text.toString())
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}