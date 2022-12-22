package com.example.ydword

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText

class Mdialog : Dialog {
    var editText_word: EditText? = null
    var editText_translate:EditText? = null
    var cancel: Button? = null
    var ensure: Button? = null

    constructor(context: Context):super(context){
        //通过LayouInflater来获取布局
        val view: View = LayoutInflater.from(getContext()).inflate(R.layout.activity_mdialog,null)
        editText_word = view.findViewById(R.id.editText)
        editText_translate = view.findViewById(R.id.editText2)
        cancel = view.findViewById(R.id.btn_cancel)
        ensure = view.findViewById(R.id.btn_ensure_1)
        setContentView(view)
    }
}














