package viked.testrxload

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.row.view.*


/**
 * Created by 1 on 01.08.2015.
 */
class ContactListAdapter(val list: MutableList<Data>) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    override fun getItemCount() = list.size

    override fun onBindViewHolder(p0: ViewHolder, position: Int) {
        p0.bindData(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, p1: Int) =
            ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.row, parent, false))


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(data: Data) {
            with(data) {
                itemView.textView.text = title
                itemView.textView2.text = body
            }
        }

    }
}
