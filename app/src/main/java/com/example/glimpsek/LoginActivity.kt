package com.example.glimpsek

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.glimpsek.Adapters.ContactAdapter
import com.example.glimpsek.databinding.ActivityLoginBinding
import com.example.glimpsek.model.LoginActivityViewModel
import com.example.glimpsek.model.User
import com.example.glimpsek.model.Users
import kotlinx.android.synthetic.main.activity_login.*

private const val TAG = "MainActivity"
class LoginActivity : AppCompatActivity() {

    //data binding
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val contacts = mutableListOf<Users>()
        val contactAdapter = ContactAdapter(this, contacts)
        rvContacts.adapter = contactAdapter
        rvContacts.layoutManager = LinearLayoutManager(this)

       /* val person = User(1, "Person", "agg@gmail.com", "54545454")
        binding.user = person
*/
        val model = ViewModelProviders.of(this)[LoginActivityViewModel::class.java]
        model.getContacts().observe(this, Observer { contactsSnapshot ->
            // Update the UI
            Log.i(TAG, "Received contacts from view model")
            contacts.clear()
            contacts.addAll(contactsSnapshot)
            contactAdapter.notifyDataSetChanged()
        })
        model.getIsRefreshing().observe(this, Observer { isRefreshing ->
            Log.i(TAG, "Received isRefreshing from view model")
            swipeContainer.isRefreshing = isRefreshing
        })

        swipeContainer.setOnRefreshListener {
            // Show the refreshing UI and fetch new data
            model.fetchNewContact()
        }
    }
}