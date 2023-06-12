package com.techfortyone.roomlibraryv1.ui.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.techfortyone.roomlibraryv1.R
import com.techfortyone.roomlibraryv1.data.model.User
import com.techfortyone.roomlibraryv1.viewmodel.MainViewModel


class AddFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        view.findViewById<Button>(R.id.addBtn).setOnClickListener {
            insertDataToDatabase(view)
        }

        return view
    }

    private fun insertDataToDatabase(view: View) {
        val firstName = view.findViewById<EditText>(R.id.firstNameEt).text.toString()
        val lastName = view.findViewById<EditText>(R.id.lastNameEt).text.toString()
        val age = view.findViewById<EditText>(R.id.ageEt).text.toString()
        if (inputCheck(firstName, lastName, age)) {
            val user = User(0, firstName, lastName, Integer.parseInt(age))
            mViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added user", Toast.LENGTH_LONG).show()
        } else
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG)
                .show()
    }


    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(
            age
        ))
    }

}
