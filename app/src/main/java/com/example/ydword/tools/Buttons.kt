package com.example.ydword.tools

import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.ydword.*


class Buttons : AppCompatActivity() {
    var button_input: Button? = null
    var button_recite: Button? = null
    var button_check: Button? = null
    var button_self: Button? = null
    val buttons: Unit
        get() {
            button_input = findViewById(R.id.btn_input) as Button?
            button_recite = findViewById(R.id.btn_recite) as Button?
            button_check = findViewById(R.id.btn_check) as Button?
            button_check = findViewById(R.id.btn_selfcenter) as Button?
        }

    /**
     * 录词按钮设置监听
     * 实现跳转
     */
    fun setButton_inputListener(view: View?) {
        button_input!!.setOnClickListener {
            val intent = Intent(getApplication(), InputWordsActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * 背词按钮设置监听
     * 实现跳转
     */
    fun setButton_reciteListener(view: View?) {
        button_recite!!.setOnClickListener {
            val intent = Intent(getApplication(), ReciteWordsActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * 查词按钮设置监听
     * 实现跳转
     */
    fun setButton_checkListener(view: View?) {
        button_check!!.setOnClickListener {
            val intent = Intent(getApplication(), CheckWordsActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * 个人中心按钮设置监听
     * 实现跳转
     */
    fun setButton_selfListener(view: View?) {
        button_self!!.setOnClickListener {
            val intent = Intent(getApplication(), SelfCenterActivity::class.java)
            startActivity(intent)
        }
    }

    init {
        buttons
    }
}