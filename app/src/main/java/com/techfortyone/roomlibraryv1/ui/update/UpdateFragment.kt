package com.techfortyone.roomlibraryv1.ui.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.techfortyone.roomlibraryv1.R
import com.techfortyone.roomlibraryv1.data.model.User
import com.techfortyone.roomlibraryv1.viewmodel.MainViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        view.findViewById<TextView>(R.id.updateFirstNameEt).text =
            args.currentUser.firstName.toString()
        view.findViewById<TextView>(R.id.updateLastNameEt).text =
            args.currentUser.lastName.toString()
        view.findViewById<TextView>(R.id.updateAgeEt).text = args.currentUser.age.toString()
        mViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        view.findViewById<Button>(R.id.updateAddBtn).setOnClickListener {
            updateUser()
        }
        return view
    }

    private fun updateUser() {
        val firstName = view?.findViewById<TextView>(R.id.updateFirstNameEt)?.text.toString()
        val lastName = view?.findViewById<TextView>(R.id.updateLastNameEt)?.text.toString()
        val age = view?.findViewById<TextView>(R.id.updateAgeEt)?.text.toString()
        if (inputCheck(firstName, lastName, age)) {
            val updateUser = User(args.currentUser.id, firstName, lastName, Integer.parseInt(age))
            mViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "User updated successfully", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_mainFragment)

        } else {
            Toast.makeText(requireContext(), "Input fields caanot be empty", Toast.LENGTH_LONG)
                .show()
        }
    }


    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(
            age
        ))
    }
}