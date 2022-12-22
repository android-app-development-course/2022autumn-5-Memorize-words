package com.example.ydword

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.ydword.database.DBResult

class Result : AppCompatActivity() {
    var dbResult: DBResult? = null
    var listView: ListView? = null
    var list: List<Map<String, Any>>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val decorView=window.decorView
        decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor= Color.TRANSPARENT
        dbResult = DBResult(this@Result, "tb_result", null, 1)

        val result_items: ArrayList<Result_item>? = getResult()
        listView = findViewById<ListView>(R.id.list)
        list = ArrayList()

        for (item in result_items!!) {
            val map: MutableMap<String, Any> = HashMap()
            map["id"]=item.id.toString()
            map["right"]=item.right.toString()
            map["wrong"]=item.wrong.toString()
            map["delete"]=item.delete.toString()
            (list as ArrayList<Map<String, Any>>).add(map)
        }
        val simpleAdapter = SimpleAdapter(
            this@Result,
            list,
            R.layout.list_item,
            arrayOf("id", "right", "wrong", "delete"),
            intArrayOf(R.id.id, R.id.word, R.id.translate, R.id.delete)
        )

        listView!!.adapter = simpleAdapter
    }
    @SuppressLint("Range")
    private fun getResult(): ArrayList<Result_item>? {
        val result_items = ArrayList<Result_item>()
        val cursor =
            dbResult!!.readableDatabase.query("tb_result", null, null, null, null, null, null)
        var i = 1
        while (cursor.moveToNext()) {
            val result_item = Result_item()
            //利用getColumnIndex：String 来获取列的下标，再根据下标获取cursor的值
            result_item.id = i
            result_item.right = cursor.getString(cursor.getColumnIndex("right"))
            result_item.wrong = cursor.getString(cursor.getColumnIndex("wrong"))
            result_item.delete = cursor.getString(cursor.getColumnIndex("remove"))
            result_items.add(result_item)
            i++
        }
        return result_items
    }
}