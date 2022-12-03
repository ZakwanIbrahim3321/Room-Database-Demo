package com.example.roomdbproject.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdbproject.R
import com.example.roomdbproject.model.User
import com.example.roomdbproject.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

  private val arguments by navArgs<UpdateFragmentArgs>()

  private lateinit var userViewModel:UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      val view = inflater.inflate(R.layout.fragment_update, container, false)

      userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

      view.et_update_first_name.setText(arguments.currentUser.firstName)
      view.et_update_last_name.setText(arguments.currentUser.lastName)
      view.et_update_age.setText(arguments.currentUser.age.toString())

      view.bt_update_user.setOnClickListener {
        updateUser()
      }

      setHasOptionsMenu(true)

      return view
    }

  private fun updateUser(){
    val firstName = et_update_first_name.text.toString()
    val lastName = et_update_last_name.text.toString()
    val age = et_update_age.text.toString()

    if (inputValidation(firstName,lastName, age)){
      val updatedUser = User(arguments.currentUser.id, firstName, lastName, Integer.parseInt(age))
      userViewModel.updateUser(updatedUser)
      findNavController().navigate(R.id.action_updateFragment_to_listFragment)
      Toast.makeText(requireContext(), "User Updated!", Toast.LENGTH_SHORT).show()
    }else{
      Toast.makeText(requireContext(), "Please Fill All Fields!", Toast.LENGTH_SHORT).show()
    }

  }

  private fun inputValidation(firstName: String, lastName: String, age: String): Boolean{
    return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age))
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.delete_menu, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.menu_delete){
      deleteUser()
    }
    return super.onOptionsItemSelected(item)
  }

  private fun deleteUser() {
    val builder = AlertDialog.Builder(requireContext())
    builder.setTitle("Delete ${arguments.currentUser.firstName} ?")
    builder.setMessage("Are you sure you want to delete ${arguments.currentUser.firstName}")
    builder.setPositiveButton("Yes"){ _, _ ->
      userViewModel.deleteUser(arguments.currentUser)
      Toast.makeText(requireContext(),
        "${arguments.currentUser.firstName} Deleted!",
        Toast.LENGTH_SHORT).show()
      findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }
    builder.setNegativeButton("No"){ _, _ -> }
    builder.create().show()
  }

}
