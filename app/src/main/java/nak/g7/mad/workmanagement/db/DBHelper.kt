package nak.g7.mad.workmanagement.db

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import nak.g7.mad.workmanagement.model.Work
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.Locale

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        const val DATABASE_NAME = "Works.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "works"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_DES = "des"
        const val COLUMN_GENDER = "gender"
        const val COLUMN_DATE = "date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME(
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_DES TEXT,
                $COLUMN_GENDER TEXT,
                $COLUMN_DATE TEXT 
            )
        """.trimIndent()
        db?.execSQL(createTableQuery)
        insertSampleData(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    private fun insertSampleData(db : SQLiteDatabase?){
//        val formatter = SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy")
//        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.US)
        val sampleWork = ArrayList<Work>()

        sampleWork.add(Work(1, "Work 1", "Work1", "Male", "01/01/2023"))
        sampleWork.add(Work(2, "Work 2", "Work2", "Female", "02/01/2023"))
        sampleWork.add(Work(3, "Work 3", "Work3", "Male", "03/01/2023"))
        sampleWork.add(Work(4, "Work 4", "Work4", "Female", "03/01/2023"))
        sampleWork.add(Work(5, "Work 5", "Work5", "Male", "03/01/2023"))
        try{
            for(work in sampleWork){
                val values = ContentValues()
                values.put(DBHelper.COLUMN_NAME, work.name)
                values.put(DBHelper.COLUMN_DATE, work.date)
                values.put(DBHelper.COLUMN_GENDER, work.gender)
                values.put(DBHelper.COLUMN_DES, work.des)
                db?.insert(TABLE_NAME, null, values)
            }
        }catch (e : SQLException){
            e.printStackTrace()
        }finally {
            
        }

    }
}