package viked.testrxload

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

import java.util.ArrayList


/**
 * Created by 1 on 01.08.2015.
 */
class ContactListAdapter(context: Activity, list: List<Data>) : ArrayAdapter<Data>(context, R.layout.row, list) {

    internal class ViewHolder(itemLayoutView: View) {
        var contactNameView: TextView
        var contactPhoneView: TextView

        init {
            contactNameView = itemLayoutView.findViewById(R.id.textView) as TextView
            contactPhoneView = itemLayoutView.findViewById(R.id.textView2) as TextView
        }
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        var holder: ViewHolder? = null
        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.row, parent, false)
            holder = ViewHolder(convertView)
            convertView!!.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        configureViewHolder(holder, position)
        return convertView
    }


    private fun configureViewHolder(viewHolder: ViewHolder, position: Int) {
        val contact = getItem(position)
        viewHolder.contactNameView.text = contact.title
        viewHolder.contactPhoneView.text = contact.toString()
    }
}
