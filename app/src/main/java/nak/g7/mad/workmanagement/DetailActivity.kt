package nak.g7.mad.workmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import nak.g7.mad.workmanagement.db.DBHandler
import nak.g7.mad.workmanagement.model.Work

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = findViewById<EditText>(R.id.inIDField)
        val name = findViewById<EditText>(R.id.inNameField)
        val des = findViewById<EditText>(R.id.inDescriptionField)
        val gender = findViewById<RadioGroup>(R.id.inGenderField)
        val date = findViewById<EditText>(R.id.inDateField)
        val saveBtn = findViewById<Button>(R.id.saveButton)
        val delBtn = findViewById<Button>(R.id.deleteButton)


        id.setText(intent.getStringExtra("workID"))
        name.setText(intent.getStringExtra("workName"))
        des.setText(intent.getStringExtra("workDes"))
        date.setText(intent.getStringExtra("workDate"))
        val genderValue = intent.getStringExtra("workGender")
        val maleBtn = findViewById<RadioButton>(R.id.malePick)
        val femaleBtn = findViewById<RadioButton>(R.id.femalePick)
        if(genderValue.equals("Male")){
            maleBtn.isChecked = true
        }else if(genderValue.equals("Female")){
            femaleBtn.isChecked = true
        }

        saveBtn.setOnClickListener {
            val idEdited = Integer.parseInt(id.text.toString())
            val nameEdited = name.text.toString()
            val desEditted = des.text.toString()
            var genderEdiited = ""
            if(maleBtn.isChecked){
                genderEdiited = "Male"
            }else if(femaleBtn.isChecked){
                genderEdiited = "Female"
            }
            val dateEditted = date.text.toString()
            val dbHandler = DBHandler(this)
            val result = dbHandler.editAWork( Work(idEdited, nameEdited, desEditted, genderEdiited, dateEditted))
            if(result != -1){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                val rootView = findViewById<View>(android.R.id.content)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0)
                Toast.makeText(this, "Some error occured", Toast.LENGTH_SHORT).show()

            }

        }

        delBtn.setOnClickListener {
            val dbHandler = DBHandler(this)
            val result = dbHandler.deleteAWork(Integer.parseInt(id.text.toString()))
            if(result != -1){
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