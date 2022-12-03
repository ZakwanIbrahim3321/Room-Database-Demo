package com.example.roomdbproject.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdbproject.R
import com.example.roomdbproject.model.User
import com.example.roomdbproject.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

  private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        view.bt_add_user.setOnClickListener {
          insertUserToDataBase()
        }
        return view
    }

  private fun insertUserToDataBase() {
    val firstName = et_first_name.text.toString()
    val lastName = et_last_name.text.toString()
    val age = et_age.text.toString()

    if (inputValidation(firstName,lastName,age)){
      val user = User(0,firstName,lastName,Integer.parseInt(age))
      userViewModel.addUser(user)
      Toast.makeText(requireContext(), "User Added!", Toast.LENGTH_SHORT).show()
      findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }else{
      Toast.makeText(requireContext(), "Please Fill All Fields!", Toast.LENGTH_SHORT).show()
    }

  }

  private fun inputValidation(firstName: String, lastName: String, age: String): Boolean{
    return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age))
  }

}
