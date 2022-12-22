package com.example.ydword

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ydword.database.DBWords

class InputWordsActivity : AppCompatActivity() {

    lateinit var textView:TextView
    lateinit var input:Button
    lateinit var button_input:Button
    lateinit var button_recite:Button
    lateinit var button_check:Button
    lateinit var button_self:Button
    lateinit var editText_word:EditText
    lateinit var editText_translate:EditText
    lateinit var dbWords: DBWords
    //private lateinit var client:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_words)
        val decorView=window.decorView
        decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor= Color.TRANSPARENT
        textView=findViewById(R.id.name)
        editText_word=findViewById(R.id.et_words)
        editText_translate=findViewById(R.id.et_translate)
        input=findViewById(R.id.btn)
        dbWords= DBWords(application,"tb_words",null,1)
        button_input = findViewById(R.id.btn_input)
        button_recite = findViewById(R.id.btn_recite)
        button_check = findViewById(R.id.btn_check)
        button_self = findViewById(R.id.btn_selfcenter)

        setInputListener()
        setListener()

        val bundle: Bundle? =intent.extras
        try{
            textView.text=bundle?.getString("name")
        }catch (e:Exception){

        }
    }
    fun setListener(){

        button_input.setOnClickListener {
            val intent = Intent(application, InputWordsActivity::class.java)
            startActivity(intent)
        }

        button_recite.setOnClickListener {
            val intent = Intent(application, ReciteWordsActivity::class.java)
            startActivity(intent)
        }

        button_check.setOnClickListener {
            val intent = Intent(application, CheckWordsActivity::class.java)
            startActivity(intent)
        }

        button_self.setOnClickListener {
            val intent = Intent(application, SelfCenterActivity::class.java)
            startActivity(intent)
        }

    }
    fun setInputListener(){
        input.setOnClickListener {
            val word=editText_word.text.toString()
            val translate=editText_translate.text.toString()
            if(word.isEmpty()||translate.isEmpty()){
                Toast.makeText(this,"请输入数据",Toast.LENGTH_SHORT).show()

            }else{
                dbWords.writeWord(word,translate)
                Toast.makeText(this,"录入成功",Toast.LENGTH_SHORT).show()
                editText_translate.setText("")
                editText_word.setText("")
                editText_word.requestFocus()
            }
        }
    }
}











