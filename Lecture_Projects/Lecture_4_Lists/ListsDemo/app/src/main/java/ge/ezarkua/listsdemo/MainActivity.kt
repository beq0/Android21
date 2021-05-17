package ge.ezarkua.listsdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ContactItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var list = getContactList()
        var rvContacts = findViewById<RecyclerView>(R.id.rv_contacts)
        var adapter = ContactsAdapter(list)
        var etName = findViewById<EditText>(R.id.et_new_contact_name)
        var etPhone = findViewById<EditText>(R.id.et_new_contact_phone)
        var btnAdd = findViewById<Button>(R.id.btn_add)

        adapter.contactClickListener = this

        rvContacts.adapter = adapter
        rvContacts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        btnAdd.setOnClickListener {
            list.add(ContactItem(etName.text.toString(), etPhone.text.toString()))
            adapter.notifyItemInserted(list.size - 1)
        }

    }

    fun getContactList(): ArrayList<ContactItem>{
        var list = arrayListOf<ContactItem>()
        for(i in 0..20){
            if(i == 3) {
                list.add(ContactItem("Ana asdsd ada dasd asdas  ad asd asd asdaddsa ", ""))
            }
                list.add(ContactItem("Ana asdsd ada dasd asdas  ad asd asd asdaddsa ", ""))

        }
        return list
    }

    override fun onContactClicked(item: ContactItem) {
        Toast.makeText(this, item.phoneNumber, Toast.LENGTH_SHORT).show()
        var detailsIntent = Intent(this, ContactDetailsActivity::class.java)
        startActivity(detailsIntent)
    }


}