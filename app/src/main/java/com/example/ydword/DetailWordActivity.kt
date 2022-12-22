package com.example.ydword

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ydword.database.DBWords

class DetailWordActivity : AppCompatActivity() {
    var dbWords: DBWords? = null
    lateinit var textView_word: TextView
    lateinit var textView_translate:TextView
    lateinit var button_modify: Button
    lateinit var button_delete:Button
    var bundle: Bundle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_word)
        val decorView=window.decorView
        decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor= Color.TRANSPARENT
        /**
         * 初始化
         */
        dbWords = DBWords(application, "tb_words", null, 1)
        textView_word = findViewById<TextView>(R.id.detail_tv_word)
        textView_translate = findViewById<TextView>(R.id.detail_tv_translate)
        button_modify = findViewById<Button>(R.id.detail_btn_modify)
        button_delete = findViewById<Button>(R.id.detail_btn_delete)

        /**
         * 获取bundle，显示传来的单词和翻译
         */
        /**
         * 获取bundle，显示传来的单词和翻译
         */
        bundle = intent.extras
        textView_word.setText(bundle?.getString("word") ?: "")
        textView_translate.setText(bundle?.getString("translate") ?: "")

        /**
         * 给修改按钮设置监听
         */
        /**
         * 给修改按钮设置监听
         */
        button_modify.setOnClickListener(View.OnClickListener { OnExit() })

        /**
         * 给删除按钮设置监听
         */
        /**
         * 给删除按钮设置监听
         */
        button_delete.setOnClickListener(View.OnClickListener {
            bundle?.getString("number")?.let { it1 -> dbWords!!.deleteWord(it1) }
            Toast.makeText(applicationContext,"删除成功！",Toast.LENGTH_SHORT).show()
        })
    }
    fun OnExit() {
        /**
         * 设置没有标题栏
         */
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val mdialog = Mdialog(this) //实例化自定义对话框

        //给取消按钮设置监听事件
        mdialog.cancel!!.setOnClickListener {
            if (mdialog.isShowing) {
                mdialog.dismiss() //关闭对话框
            }
        }
        //给确定按钮设置监听事件
        mdialog.ensure!!.setOnClickListener {
            if (mdialog.isShowing) {
                val word = mdialog.editText_word!!.text.toString()
                val translate = mdialog.editText_translate!!.text.toString()
                bundle?.getString("number")?.let { it1 -> dbWords?.updateWord(it1, word, translate) }
                print(bundle?.getString("number"))
                mdialog.dismiss() //关闭对话框
                textView_word.setText(word)
                textView_translate.setText(translate)
            }
        }
        mdialog.show()
    }
}