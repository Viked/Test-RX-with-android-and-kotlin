package viked.testrxload

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.widget.LinearLayoutManager
import rx.functions.Action1

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val projection = arrayOf(ContactsContract.Data.CONTACT_ID, ContactsContract.Data.DISPLAY_NAME)

    val contactsList: MutableList<Data> = mutableListOf();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView.layoutManager = LinearLayoutManager(this)
        val adapter = ContactListAdapter(contactsList)
        listView.adapter = adapter
        val loader = Loader(contentResolver.query(ContactsContract.Data.CONTENT_URI,projection, null, null, null))
        loader.startLoading(
                Action1 { list ->
                    contactsList.addAll(list)
                    adapter.notifyDataSetChanged() },
                Action1{ i-> progressBar.progress = i}
        )
        //loader.showProgressSubject().subscribe({i->progressBar.progress = i},{error -> print(error.message)})//, {progressBar.visibility = View.GONE})
    }

}

