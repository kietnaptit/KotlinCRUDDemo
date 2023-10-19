package nak.g7.mad.workmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import nak.g7.mad.workmanagement.db.DBHandler
import nak.g7.mad.workmanagement.model.Work
import java.text.SimpleDateFormat
import java.util.Date

class NewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val idField = findViewById<EditText>(R.id.inIDField)
        val nameField = findViewById<EditText>(R.id.inNameField)
        val desField = findViewById<EditText>(R.id.inDescriptionField)
        val genderField = findViewById<RadioGroup>(R.id.inGenderField)
        val dateField = findViewById<EditText>(R.id.inDateField)
        val saveBtn = findViewById<Button>(R.id.saveButton)

        saveBtn.setOnClickListener {
//            val formatter = SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy")
            val id = Integer.parseInt(idField.text.toString())
            val name = nameField.text.toString()
            val des = desField.text.toString()
            val genderchoice = genderField.checkedRadioButtonId
            var gender: String = ""
            when(genderchoice){
                R.id.malePick -> {
                    gender = "Male"
                }
                R.id.femalePick -> {
                    gender = "Female"
                }
            }
            val date = dateField.text.toString()
            val dbHandler = DBHandler(this)
            val res : Long? = dbHandler.addNewWork(Work(id, name, des, gender, date))
            if(res?.toInt() != -1){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                val rootView = findViewById<View>(android.R.id.content)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0)
                Toast.makeText(this, "Some error occured", Toast.LENGTH_SHORT).show()
            }
        }
    }
}