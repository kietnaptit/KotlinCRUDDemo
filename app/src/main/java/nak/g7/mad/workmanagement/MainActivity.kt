package nak.g7.mad.workmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nak.g7.mad.workmanagement.adapter.WorkRecyclerAdapter
import nak.g7.mad.workmanagement.db.DBHandler
import nak.g7.mad.workmanagement.model.Work

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<Button>(R.id.addButton)
        val dbHandler = DBHandler(this)
        var workList: List<Work>? = dbHandler.getAllWorks()
        var workListView = findViewById<RecyclerView>(R.id.workList)
        var wAdapter = WorkRecyclerAdapter(workList!!){ work ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("workID", work.id.toString())
            intent.putExtra("workName", work.name)
            intent.putExtra("workDes", work.des)
            intent.putExtra("workDate", work.date)
            intent.putExtra("workGender", work.gender)

            startActivity(intent)

        }
        val wLayoutManager = LinearLayoutManager(applicationContext)
        workListView!!.layoutManager = wLayoutManager
        workListView!!.itemAnimator = DefaultItemAnimator()
        workListView!!.adapter = wAdapter


        addButton.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)
        }

    }
}

private fun Intent.putExtra(s: String, work: Work) {

}
