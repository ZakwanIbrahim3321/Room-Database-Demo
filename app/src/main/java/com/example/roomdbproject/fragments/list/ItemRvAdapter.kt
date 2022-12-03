package com.example.roomdbproject.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbproject.R
import com.example.roomdbproject.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class ItemRvAdapter: RecyclerView.Adapter<ItemRvAdapter.MyViewHolder>() {

  private var userList = emptyList<User>()

  class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val currentItem = userList[position]
    holder.itemView.tv_id.text = currentItem.id.toString()
    holder.itemView.tv_first_name.text = currentItem.firstName
    holder.itemView.tv_last_name.text = currentItem.lastName
    "(${currentItem.age})".also { holder.itemView.tv_age.text = it }

    holder.itemView.item_layout.setOnClickListener {
      val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
      holder.itemView.findNavController().navigate(action)
    }

  }

  fun setData(user: List<User>){
    this.userList = user
    notifyDataSetChanged()
  }

  override fun getItemCount(): Int {
    return userList.size
  }


}
