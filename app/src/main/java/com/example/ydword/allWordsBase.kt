package com.example.ydword

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.ydword.database.DBWords
import com.example.ydword.info.Word


class allWordsBase : AppCompatActivity() {
    var dbWords: DBWords? = null
    var listView: ListView? = null
    var list: ArrayList<Map<String, Any?>>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_words_base)
        val decorView=window.decorView
        decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor= Color.TRANSPARENT
        dbWords = DBWords(this@allWordsBase, "tb_words", null, 1)
        val words: ArrayList<Word> = getWords()
        listView = findViewById(R.id.list2)
        list = ArrayList()
        /**
         * 把单词添加到列表组里
         */
        for (i in words.indices) {
            val map: MutableMap<String, Any?> = HashMap()
            map["id"] = words[i].number.toString() + "."
            map["word"] = words[i].word
            map["translate"] = words[i].translate
            list!!.add(map)
        }
        val simpleAdapter = SimpleAdapter(
            this@allWordsBase,
            list,
            R.layout.list_item,
            arrayOf("id", "word", "translate"),
            intArrayOf(R.id.id, R.id.word, R.id.translate)
        )
        listView!!.adapter = simpleAdapter
        listView!!.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val bundle = Bundle()
                val word = words[position].word.toString()
                val translate= words[position].translate.toString()
                val number: String = words[position].number.toString()
                val intent = Intent(application, DetailWordActivity::class.java)
                bundle.putString("word", word)
                bundle.putString("translate", translate)
                bundle.putString("number", number)
                intent.putExtras(bundle)
                startActivity(intent)
            }
    }//利用getColumnIndex：String 来获取列的下标，再根据下标获取cursor的值

    /**
     * 获取单词组
     * @return
     */

    @SuppressLint("Range")
    private fun getWords():ArrayList<Word>{
        val words=ArrayList<Word>()
        val cursor:Cursor=
            dbWords?.readableDatabase?.query("tb_words", null, null, null, null, null, null)!!
        while(cursor.moveToNext()){
            val word=Word()
            //利用getColumnIndex：String 来获取列的下标，再根据下标获取cursor的值
            word.number=cursor.getString(cursor.getColumnIndex("numbers"))
            word.word=cursor.getString(cursor.getColumnIndex("word"))
            word.translate=cursor.getString(cursor.getColumnIndex("translate"))
            words.add(word)
        }
        return words
    }

    override fun onRestart() {
        super.onRestart()
        val words: ArrayList<Word> =getWords()
        list = ArrayList()
        /**
         * 把单词添加到列表组里
         */
        for (i in words.indices) {
            val map: MutableMap<String, Any?> = HashMap()
            map["id"] = words[i].number.toString() + "."
            map["word"] = words[i].word
            map["translate"] = words[i].translate
            list!!.add(map)
        }
        val simpleAdapter = SimpleAdapter(
            this@allWordsBase,
            list,
            R.layout.list_item,
            arrayOf("id", "word", "translate"),
            intArrayOf(R.id.id, R.id.word, R.id.translate)
        )
        listView!!.adapter = simpleAdapter
    }
}
