package com.example.ydword

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.ydword.Adapter.FragmentAdpter
import com.example.ydword.fragments.FragmentA
import com.example.ydword.fragments.FragmentB
import com.google.android.material.tabs.TabLayout


class ReciteWordsActivity : AppCompatActivity() {


    lateinit var button_input: Button
    lateinit var button_recite: Button
    lateinit var button_check: Button
    lateinit var button_self: Button
    lateinit var tableLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var fragments: ArrayList<Fragment>
    lateinit var strings: Array<String>
    lateinit var fragmentA: FragmentA
    lateinit var fragmentB: FragmentB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recite_words)
        val decorView=window.decorView
        decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor= Color.TRANSPARENT
        /**
         * 初始化
         */
        viewPager = findViewById(R.id.vp)
        tableLayout = findViewById(R.id.tl)
        /**
         * 把页面添加到页面组
         */
        fragmentA = FragmentA("单词写译")
        fragmentB = FragmentB("翻译写英")
        fragments = ArrayList<Fragment>()
        fragments.add(fragmentA)
        fragments.add(fragmentB)
        strings = arrayOf("单词写译", "翻译写英")
        val fragmentAdpter = FragmentAdpter(supportFragmentManager, fragments)
        viewPager.setAdapter(fragmentAdpter)
        tableLayout.setupWithViewPager(viewPager)
        buttons
        setListener()
    }

    val buttons: Unit
        get() {
            button_input = findViewById(R.id.btn_input)
            button_recite = findViewById(R.id.btn_recite)
            button_check = findViewById(R.id.btn_check)
            button_self = findViewById(R.id.btn_selfcenter)
        }

    fun setListener() {
        /**
         * 录词按钮设置监听
         * 实现跳转
         */
        button_input!!.setOnClickListener {
            val intent = Intent(application, InputWordsActivity::class.java)
            startActivity(intent)
        }
        /**
         * 背词按钮设置监听
         * 实现跳转
         */
        button_recite!!.setOnClickListener {
            val intent = Intent(application, ReciteWordsActivity::class.java)
            startActivity(intent)
        }
        /**
         * 查词按钮设置监听
         * 实现跳转
         */
        button_check!!.setOnClickListener {
            val intent = Intent(application, CheckWordsActivity::class.java)
            startActivity(intent)
        }
        /**
         * 个人中心按钮设置监听
         * 实现跳转
         */
        button_self!!.setOnClickListener {
            val intent = Intent(application, SelfCenterActivity::class.java)
            startActivity(intent)
        }
    }
}