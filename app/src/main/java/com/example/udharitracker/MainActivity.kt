package com.example.udharitracker

import Adapter.ListPersonAdapter
import DBHelper.DBHelper
import android.app.Person
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_layout.*
import model.persondata

class MainActivity : AppCompatActivity() {
    internal  lateinit var db: DBHelper
    internal  var firstPersons:List<persondata> = ArrayList<persondata>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db=DBHelper(this)
        refreshData()

        add.setOnClickListener{
            val person=persondata(Integer.parseInt(id.text.toString()),name.text.toString(),Integer.parseInt(udhaar.text.toString()))
        db.addPerson(person)
            refreshData()


        }



        update.setOnClickListener{
            val person=persondata(Integer.parseInt(id.text.toString()),name.text.toString(),Integer.parseInt(udhaar.text.toString()))
        db.updatePerson(person)
            refreshData()
        }

        delete.setOnClickListener{
            val person=persondata(Integer.parseInt(id.text.toString()),name.text.toString(),Integer.parseInt(udhaar.text.toString()))
            db.deletePerson(person)
            refreshData()
        }
    }

    private fun refreshData() {
        firstPersons=db.allPerson
        val adapter=ListPersonAdapter(this,firstPersons,id,name,udhaar)
       list.adapter=adapter
    }
}