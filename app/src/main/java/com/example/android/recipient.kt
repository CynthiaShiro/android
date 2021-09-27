package com.example.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast


class recipient : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var etSearch: EditText
    var arrayAdapter: ArrayAdapter<String>? = null
    var hospitals:ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipient)
        title = "Recipient page"
        listView = findViewById(R.id.listView)
        etSearch = findViewById(R.id.etSearch)
        hospitals.add("Nairobi Hospital")
        hospitals.add("Kenyatta Hospital")
        hospitals.add("Kijabe Hospital")
        hospitals.add("Nyeri PGH")
        hospitals.add("Getrudes Hospital, Muthaiga")
        hospitals.add("Karen Hospital")
        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,hospitals)
        listView.adapter = arrayAdapter
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                arrayAdapter!!.filter.filter(s)
            }
            override fun afterTextChanged(s: Editable) {}
        })
        listView.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position) as String
            Toast.makeText(this,"Please contact $selectedItem.", Toast.LENGTH_SHORT).show()
        }


    }
}