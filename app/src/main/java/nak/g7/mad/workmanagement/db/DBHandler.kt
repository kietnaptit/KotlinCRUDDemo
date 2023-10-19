package nak.g7.mad.workmanagement.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.util.Log
import nak.g7.mad.workmanagement.model.Work
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Locale

class DBHandler(context: Context) {
    private val dbHelper : DBHelper = DBHelper(context)

    fun getAllWorks() : List<Work>?{
        val db = dbHelper.readableDatabase
        var works = ArrayList<Work>()
        var cursor : Cursor? = null
//        val formatter = SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy")
//        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.US)
        try{
            val query = "SELECT * FROM ${DBHelper.TABLE_NAME}"
            cursor = db.rawQuery(query, null)
            if(cursor.moveToFirst()){
                do{
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME))
                    val des = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_DES))
                    val gender = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_GENDER))
                    val date = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_DATE))
                    works.add(Work(id, name, des, gender, date))
                }while (cursor.moveToNext())
            }
        }catch (e : SQLException){
            e.printStackTrace()
        }finally {
            cursor?.close()
        }
        return works
    }

    fun addNewWork(work : Work) : Long?{
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put(DBHelper.COLUMN_NAME, work.name)
        values.put(DBHelper.COLUMN_DATE, work.date)
        values.put(DBHelper.COLUMN_GENDER, work.gender)
        values.put(DBHelper.COLUMN_DES, work.des)
        val i : Long? = db?.insert(DBHelper.TABLE_NAME,null,values)
        return i
    }

//    fun editAWork(work: Work) : Int?{
//        val db = dbHelper.writableDatabase
//        val values = ContentValues()
//        values.put(DBHelper.COLUMN_NAME, work.name)
//        values.put(DBHelper.COLUMN_DATE, work.date)
//        values.put(DBHelper.COLUMN_GENDER, work.gender)
//        values.put(DBHelper.COLUMN_DES, work.des)
//        val success = db?.update(DBHelper.TABLE_NAME, values, DBHelper.COLUMN_ID + "=?", arrayOf(work.id.toString()))
//        return success
//    }

    fun editAWork(work: Work) : Int?{
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put(DBHelper.COLUMN_NAME, work.name)
        values.put(DBHelper.COLUMN_DATE, work.date)
        values.put(DBHelper.COLUMN_GENDER, work.gender)
        values.put(DBHelper.COLUMN_DES, work.des)
        val success = db?.update(DBHelper.TABLE_NAME, values, DBHelper.COLUMN_ID + "=?", arrayOf(work.id.toString()))
        return success
    }

    fun deleteAWork(workID: Int) : Int?{
        val db = dbHelper.writableDatabase
        val i : Int? = db?.delete(DBHelper.TABLE_NAME, DBHelper.COLUMN_ID + "=?", arrayOf(workID.toString()))
        return i
    }



}