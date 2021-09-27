package com.example.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class donor : AppCompatActivity() {
    private lateinit var dataBase: SqliteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donor)
        title = "Donation Page"
        val contactView: RecyclerView = findViewById(R.id.myContactList)
        val linearLayoutManager = LinearLayoutManager(this)
        contactView.layoutManager = linearLayoutManager
        contactView.setHasFixedSize(true)
        dataBase = SqliteDatabase(this)
        var allContacts= dataBase.listContacts()
        if (allContacts.size > 0) {
            contactView.visibility = View.VISIBLE
            val mAdapter = ContactAdapter(this, allContacts)
            contactView.adapter = mAdapter
        }
        else {
            contactView.visibility = View.GONE
            Toast.makeText(
                this,
                "There is no hospital in the database. Start adding now",
                Toast.LENGTH_LONG
            ).show()
        }
        val btnAdd: Button = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener { addTaskDialog() }

    }
    private fun addTaskDialog() {
        val inflater = LayoutInflater.from(this)
        val subView = inflater.inflate(R.layout.add_contacts, null)
        val nameField: EditText = subView.findViewById(R.id.enterName)
        val noField: EditText = subView.findViewById(R.id.enterPhoneNum)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add new DONOR")
        builder.setView(subView)
        builder.create()
        builder.setPositiveButton("ADD DONOR") { _, _ ->
            val name = nameField.text.toString()
            val phoneNum = noField.text.toString()
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(
                    this,
                    "Something went wrong. Check your input values",
                    Toast.LENGTH_LONG
                ).show()
            }
            else {
                val newContact = person(name, phoneNum)
                dataBase.addContacts(newContact)
                finish()
                startActivity(intent)
            }
        }
        builder.setNegativeButton("CANCEL") { _, _ -> Toast.makeText(this, "Task cancelled",
            Toast.LENGTH_LONG).show()}
        builder.show()
    }
    override fun onDestroy() {
        super.onDestroy()
        dataBase.close()

    }
}