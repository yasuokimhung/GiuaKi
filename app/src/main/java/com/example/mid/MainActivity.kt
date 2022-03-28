package com.example.mid

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.mid.Model.Person
import com.example.mid.Adapter.ListPersonAdapter
import com.example.mid.DBHelper.DBHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    internal lateinit var db: DBHelper
    internal var lstPersons:List<Person> = ArrayList<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)

        refreshData()

        //event
        btn_add.setOnClickListener {
            val a = edt_email.text.toString();
            val b = edt_reemail.text.toString();
            if (a != b){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Androidly Alert")
                builder.setMessage("Email không trùng khớp")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    Toast.makeText(applicationContext,
                        android.R.string.yes, Toast.LENGTH_SHORT).show()
                }

                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(applicationContext,
                        android.R.string.no, Toast.LENGTH_SHORT).show()
                }

//                builder.setNeutralButton("Maybe") { dialog, which ->
//                    Toast.makeText(applicationContext,
//                        "Maybe", Toast.LENGTH_SHORT).show()
//                }
                builder.show()
            }else {
                val person= Person(
                    Integer.parseInt(edt_id.text.toString()),
                    edt_name.text.toString(),
                    edt_email.text.toString()
                )
                db.addPerson(person)
                refreshData()
            }
        }
        btn_update.setOnClickListener {
            val a = edt_email.text.toString();
            val b = edt_reemail.text.toString();
            if (a != b){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Androidly Alert")
                builder.setMessage("Email không trùng khớp")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    Toast.makeText(applicationContext,
                        android.R.string.yes, Toast.LENGTH_SHORT).show()
                }

                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(applicationContext,
                        android.R.string.no, Toast.LENGTH_SHORT).show()
                }

//                builder.setNeutralButton("Maybe") { dialog, which ->
//                    Toast.makeText(applicationContext,
//                        "Maybe", Toast.LENGTH_SHORT).show()
//                }
                builder.show()
            }else {
            val person= Person(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_email.text.toString()
            )
            db.updatePerson(person)
            refreshData()
            }
        }
        btn_delete.setOnClickListener {
            val person= Person(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_email.text.toString()
            )
            db.deletePerson(person)
            refreshData()
        }
    }
    private fun refreshData(){
        lstPersons = db.allPerson
        val adapter = ListPersonAdapter(this@MainActivity, lstPersons, edt_id, edt_name, edt_email)
        list_persons.adapter = adapter
    }
}