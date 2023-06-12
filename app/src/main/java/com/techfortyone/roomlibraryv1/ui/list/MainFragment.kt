package com.techfortyone.roomlibraryv1.ui.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.techfortyone.roomlibraryv1.R
import com.techfortyone.roomlibraryv1.data.model.User
import com.techfortyone.roomlibraryv1.ui.adapter.UserItemListener
import com.techfortyone.roomlibraryv1.ui.adapter.UserRecyclerViewAdapter
import com.techfortyone.roomlibraryv1.viewmodel.MainViewModel


class MainFragment : Fragment(), UserItemListener {

    private lateinit var mAdapter : UserRecyclerViewAdapter
    private lateinit var mRecyclerView : RecyclerView
    private lateinit var mUserViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }
        //viewmodel
        mUserViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        //recyclerview
        mRecyclerView = view.findViewById(R.id.rv)
        //recyclerview adapter
        mAdapter = UserRecyclerViewAdapter(this)
        initRecyclerView()
        return view
    }

    private fun initRecyclerView() {
//        val user1 = User(1,"sai","r",32)
//        val user2 = User(2,"Ramalingam","M",32)
//        val user3 = User(3,"Swathi","r",34)
//        mUserViewModel.userData.observe(viewLifecycleOwner, Observer { userList ->
//            if (userList != null) {
//                mAdapter.setData(userList)
//            }
//            else {
//                Toast.makeText(requireContext(),"null list",Toast.LENGTH_LONG).show()
//            }
//        })
        mUserViewModel.readData().observe(requireActivity(), Observer { userList ->
            if (userList != null) {
                mAdapter.setData(userList)
            }
            else {
                Toast.makeText(requireContext(),"null list",Toast.LENGTH_LONG).show()
            }

        })
      //  mAdapter.setData(listOf(user1,user2,user3))
        mRecyclerView.adapter = mAdapter

    }
    override fun onUserItemClick(user: User) {
        Log.d("TAGS", "user ${user.firstName} is clicked")
    }
}