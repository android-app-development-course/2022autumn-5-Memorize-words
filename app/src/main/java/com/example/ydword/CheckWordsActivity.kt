package com.example.ydword

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ydword.database.DBWords
import com.example.ydword.info.Word

class CheckWordsActivity : AppCompatActivity() {
    var dbWords: DBWords?=null
    lateinit var tv_word: EditText
    lateinit var tv_translate:EditText
    lateinit var query_btn_translate: Button
    lateinit var query_btn_word:Button
    var words: ArrayList<Word>? = null
    var i = 0 //背诵数量

    var a = 1 //删除数量

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_words)
        val decorView=window.decorView
        decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor= Color.TRANSPARENT
        dbWords = DBWords(application, "tb_words", null, 1)
        tv_word = findViewById(R.id.query_et_word)
        tv_translate = findViewById(R.id.query_et_translate)
        query_btn_translate = findViewById(R.id.query_btn_translate)
        query_btn_word = findViewById(R.id.query_btn_word)
        setListener()
    }

    fun setListener(){
        query_btn_translate.setOnClickListener {
            tv_word.setText(getWords())
        }
        query_btn_word.setOnClickListener {
            tv_translate.setText(getTranslate())
        }
    }
    @SuppressLint("Range")
    private fun getWords(): String? {
        val translate = tv_translate.text.toString()
        var word: String? = null
        val cursor = dbWords!!.readableDatabase.query(
            "tb_words",
            null,
            "translate = ? ",
            arrayOf(translate),
            null,
            null,
            null
        )
        if (cursor.count == 0) Toast.makeText(this,"没有该单词！",Toast.LENGTH_SHORT).show()
        else {
            while (cursor.moveToNext()) {
                val word1 = Word()
                //利用getColumnIndex：String 来获取列的下标，再根据下标获取cursor的值
                word1.word=cursor.getString(cursor.getColumnIndex("word"))
                word = word1.word
            }
        }
        return word
    }

    @SuppressLint("Range")
    private fun getTranslate(): String? {
        val word = tv_word!!.text.toString()
        var translate: String? = null
        Log.d("1111", "onClick: ")
        val cursor = dbWords!!.readableDatabase.query(
            "tb_words",
            null,
            "word = ?",
            arrayOf(word),
            null,
            null,
            null
        )
        Log.d("1111", "onClick: ")
        if (cursor.count == 0)Toast.makeText(this,"没有该单词！",Toast.LENGTH_SHORT).show()
        else {
            while (cursor.moveToNext()) {
                val word1 = Word()
                //利用getColumnIndex：String 来获取列的下标，再根据下标获取cursor的值
                word1.translate=cursor.getString(cursor.getColumnIndex("translate"))
                translate = word1.translate
            }
        }
        return translate
    }
}





















