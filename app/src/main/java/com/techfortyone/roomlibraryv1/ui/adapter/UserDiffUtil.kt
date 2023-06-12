package com.techfortyone.roomlibraryv1.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.techfortyone.roomlibraryv1.data.model.User

class UserDiffUtil(val oldItemList: List<User>, val newItemList: List<User>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldItemList.size
    }

    override fun getNewListSize(): Int {
        return newItemList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemList[oldItemPosition].id == newItemList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldItemList[oldItemPosition].id != newItemList[newItemPosition].id -> {
                false
            }

            oldItemList[oldItemPosition].age != newItemList[newItemPosition].age -> {
                false
            }

            oldItemList[oldItemPosition].firstName != newItemList[newItemPosition].firstName -> {
                false
            }

            oldItemList[oldItemPosition].lastName != newItemList[newItemPosition].lastName -> {
                false
            }

            else -> {
                true
            }
        }
    }
}