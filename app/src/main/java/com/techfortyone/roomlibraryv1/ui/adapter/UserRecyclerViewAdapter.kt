package com.techfortyone.roomlibraryv1.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.location.GnssStatusCompat.ConstellationType
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.techfortyone.roomlibraryv1.R
import com.techfortyone.roomlibraryv1.data.model.User
import com.techfortyone.roomlibraryv1.ui.add.AddFragmentDirections
import com.techfortyone.roomlibraryv1.ui.list.MainFragmentDirections
import com.techfortyone.roomlibraryv1.ui.list.MainFragmentDirections.Companion.actionMainFragmentToUpdateFragment
import org.w3c.dom.Text

class UserRecyclerViewAdapter(val userItemListener : UserItemListener) : RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder>(),UserItemListener {

    private var mUserList = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_card_view, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mUserList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = mUserList[position]
        with(holder) {
            this.firstname.text = currentItem.firstName.toString()
            this.lastName.text = currentItem.lastName.toString()
            this.age.text = currentItem.age.toString()
        }
        holder.itemView.setOnClickListener {
            userItemListener.onUserItemClick(currentItem)
            val action = MainFragmentDirections.actionMainFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    inner class UserViewHolder(itemView: View) : ViewHolder(itemView) {
        val age = itemView.findViewById<TextView>(R.id.ageTv)
        val firstname = itemView.findViewById<TextView>(R.id.firstNameTv)
        val lastName = itemView.findViewById<TextView>(R.id.lastNameTv)

    }

    fun setData(newUserList : List<User>) {
        val diffUtil = UserDiffUtil(mUserList,newUserList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        mUserList = newUserList
        diffResults.dispatchUpdatesTo(this)
    }

    override fun onUserItemClick(user: User) {
       // userItemListener.onUserItemClick(user)
    }


}

interface UserItemListener {
    fun onUserItemClick(user: User)
}