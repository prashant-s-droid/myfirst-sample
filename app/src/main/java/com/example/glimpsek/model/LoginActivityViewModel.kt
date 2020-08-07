package com.example.glimpsek.model

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG= "LoginActivityViewModel"

class LoginActivityViewModel : ViewModel() {
    private val contactsLiveData: MutableLiveData<MutableList<Users>>
    private val isRefreshingLiveData: MutableLiveData<Boolean>

    init {
        Log.i(TAG, "init")
        contactsLiveData = MutableLiveData()
        contactsLiveData.value = createContacts()
        isRefreshingLiveData = MutableLiveData()
        isRefreshingLiveData.value = false
    }

    fun getContacts(): LiveData<MutableList<Users>> {
        return contactsLiveData
    }

    fun getIsRefreshing(): LiveData<Boolean> {
        return isRefreshingLiveData
    }

    fun fetchNewContact() {
        Log.i(TAG, "fetchNewContact")
        // indicate that we're in "refreshing" state
        isRefreshingLiveData.value = true
                    // add the new contact
                    val contacts = contactsLiveData.value
                    contacts?.add(0, Users("Julius Campbell", 52))
                    contactsLiveData.value = contacts
                    isRefreshingLiveData.value = false
    }

    private fun createContacts(): MutableList<Users> {
        Log.i(TAG, "createContacts")
        val contacts = mutableListOf<Users>()
        for (i in 1..150) contacts.add(Users("Person #$i", i))
        return contacts
    }

}
