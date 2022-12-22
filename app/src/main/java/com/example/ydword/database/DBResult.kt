package com.example.ydword.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBResult(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {
        val sql = "CREATE TABLE " + "tb_result" + " (" +
                "id			INTEGER 		PRIMARY KEY     autoincrement ," +
                " correct		VARCHAR(50)		NOT NULL ," +
                " wrong   	varchar(50)		NOT NULL ," +
                " remove     varchar(50)        NOT NULL)"
        sqLiteDatabase!!.execSQL(sql)
    }
    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("delete from tb_result")
        sqLiteDatabase.execSQL("update sqlite_sequence SET seq = 0 where name ='tb_result'")
    }
    fun writeData(sqLiteDatabase: SQLiteDatabase, right: String?, wrong: String?, delete: String?) {
        val values = ContentValues()
        values.put("correct", right)
        values.put("wrong", wrong)
        values.put("remove", delete)
        sqLiteDatabase.insert("tb_result", null, values) //保存功能
    }
}