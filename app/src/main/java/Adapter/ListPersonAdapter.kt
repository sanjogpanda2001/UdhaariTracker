package Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.udharitracker.R
import kotlinx.android.synthetic.main.row_layout.view.*
import model.persondata


class ListPersonAdapter(internal  var activity:Activity, internal  var firstPersons:List<persondata>,
internal var id:EditText,internal var name:EditText,internal var udhar:EditText):BaseAdapter() {

     var inflater:LayoutInflater
    init {
        inflater=activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       val rowView:View
        rowView=inflater.inflate(R.layout.row_layout,null)
        rowView.txt_row_id.text=firstPersons[position].id.toString()
        rowView.txt_name.text=firstPersons[position].name.toString()
        rowView.txt_udhaar.text=firstPersons[position].udhar.toString()


        rowView.setOnClickListener(){
            id.setText(rowView.txt_row_id.text.toString())
            name.setText(rowView.txt_name.text.toString())
            udhar.setText(rowView.txt_udhaar.text.toString())
        }
        return rowView
    }

    override fun getItem(position: Int): Any {
       return firstPersons[position]
    }

    override fun getItemId(position: Int): Long {
      return firstPersons[position].id.toLong()
    }

    override fun getCount(): Int {
       return firstPersons.size
    }


}