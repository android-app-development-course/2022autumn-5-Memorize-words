package com.example.ydword

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SelfCenterActivity : AppCompatActivity() {
    lateinit var textView_explain: TextView
    lateinit var textView_resetname:TextView
    lateinit var textView_allword:TextView
    lateinit var textView_result:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_self_center)
        val decorView=window.decorView
        decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor= Color.TRANSPARENT
        textView_explain = findViewById<TextView>(R.id.self_tv_explain)
        textView_resetname = findViewById<TextView>(R.id.self_tv_resetname)

        textView_allword = findViewById<TextView>(R.id.self_tv_allwords)
        textView_result = findViewById<TextView>(R.id.self_tv_result)

        //点击软件说明

        //点击软件说明
        textView_explain.setOnClickListener {
            val intent = Intent(application, Explain::class.java)
            startActivity(intent)
        }

/*        //点击修改用户名
        textView_resetname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ResetUsername.class);
                startActivity(intent);
            }
        });*/

        //点击单词库

/*        //点击修改用户名
        textView_resetname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ResetUsername.class);
                startActivity(intent);
            }
        });*/

        //点击单词库
        textView_allword.setOnClickListener {
            val intent = Intent(application, allWordsBase::class.java)
            startActivity(intent)
        }

        //点击单词战况

        //点击单词战况
        textView_result.setOnClickListener {
            val intent = Intent(application, Result::class.java)
            startActivity(intent)
        }
    }
}