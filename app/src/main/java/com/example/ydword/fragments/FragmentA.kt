package com.example.ydword.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.ydword.R
import com.example.ydword.database.DBResult
import com.example.ydword.database.DBWords
import com.example.ydword.info.Word

class FragmentA: Fragment{
    var title:String=""
    lateinit var textView: TextView
    var editText: EditText? = null
    lateinit var button_ensure: Button
    lateinit var button_delete:Button
    var dbWords: DBWords? = null
    var dbResult: DBResult? = null
    var wordscount = 0
    var number = 0
    var word: Word? = null
    var count = 0
    var right = 0 //正确数量

    var wrong = 0 //错误数量

    var delete = 0 //删除数量
    constructor(title:String):super(){
        this.title=title
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_a,null)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.tv_word_1)
        editText = view.findViewById(R.id.et_translate)
        button_ensure = view.findViewById(R.id.btn_ensure)
        button_delete = view.findViewById(R.id.btn_delete)
        dbWords = DBWords(activity, "tb_words", null, 1)
        dbResult = DBResult(activity, "tb_result", null, 1)

        setButtonListener()
        Test()
    }

    fun Test(){
        if(count<10){
            wordscount= dbWords?.count ?: 0
            number=(wordscount*Math.random()).toInt()
            word= dbWords?.getWord(number)
            textView.setText(word!!.word)
            count++
        }else{
            ifBackToFirst()
        }
    }
    fun setButtonListener() {
        /**
         * 设置确定按钮的监听
         * 判断是否正确
         */
        button_ensure.setOnClickListener {
            if (word?.translate.equals(editText!!.text.toString())) {
                Toast.makeText(activity,"回答正确! ",Toast.LENGTH_SHORT).show()
                right++
                editText!!.setText("")
            } else {
                Toast.makeText(activity,"回答错误! ",Toast.LENGTH_SHORT).show()
                wrong++
                editText!!.setText("")
            }
            Test()
        }
        /**
         * 设置删除按钮的监听
         * 点击删除单词
         */
        button_delete.setOnClickListener {
            dbWords!!.deleteWord(number.toString() + "")
            textView.text = ""
            editText!!.setText("")
            delete++
            Test()
        }
    }
    fun ifBackToFirst() {
        dbResult!!.writeData(dbResult!!.readableDatabase, "正确：$right", "错误：$wrong", "删除：$delete")
        wrong = 0
        delete = 0
        right = 0
        val builder= activity?.let { AlertDialog.Builder(it) }
        builder?.setTitle("已经背到最后了")?.setMessage("是否再来一组？")
            ?.setIcon(R.drawable.icon_image)
            ?.setPositiveButton("是", DialogInterface.OnClickListener { dialogInterface, j ->
                count = 0
                Test()
            })?.setNegativeButton("否", DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(activity,"已背完最后一组啦!",Toast.LENGTH_SHORT).show()
                textView.text = ""
                editText!!.setText("")
            })?.show()
    }
}











