package com.example.ydword.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import com.example.ydword.info.Word

class DBWords(context: Context?, name: String?, factory: CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
    var number = 0
    val Create_Table_SQL =
        "create table tb_words (_id integer primary key autoincrement,numbers,word,translate)"

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(Create_Table_SQL)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("delete from tb_words")
        sqLiteDatabase.execSQL("update sqlite_sequence SET seq = 0 where name ='tb_words'")
    }

    /**
     * 单词的添加方法
     */
    fun writeWord(word: String?, translate: String?) {
        number++
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("numbers", number.toString() + "")
        values.put("word", word)
        values.put("translate", translate)
        db.insert("tb_words", null, values)
    }

    /**
     * 删除单词方法
     *
     * @param id
     */
    fun deleteWord(id: String) {
        val db = this.writableDatabase
        val whereValue = arrayOf(id + "")
        db.delete("tb_words", "numbers = ?", whereValue)
    }

    /**
     * 修该单词方法
     *
     * @param id
     * @param word
     * @param translate
     */
    fun updateWord(id: String, word: String?, translate: String?) {
        val db = this.writableDatabase
        val where = "numbers = ?"
        val whereValue = arrayOf(id)
        val values = ContentValues()
        values.put("word", word)
        values.put("translate", translate)
        db.update("tb_words", values, where, whereValue)
    }

    /**
     * 获取单词
     *
     * @param id
     * @return
     */
    @SuppressLint("Range")
    fun getWord(id: Int): Word {
        val db = this.writableDatabase
        val word = Word()
        val cursor = db.query("tb_words", null, null, null, null, null, null)
        cursor.moveToPosition(id)
        try {
            word.word=cursor.getString(cursor.getColumnIndex("word"))
            word.translate=cursor.getString(cursor.getColumnIndex("translate"))
        } catch (e: Exception) {
        }
        return word
    }

    val count: Int
        get() {
            val db = this.writableDatabase
            val cursor = db.query("tb_words", null, null, null, null, null, null)
            return cursor.count
        }
}
