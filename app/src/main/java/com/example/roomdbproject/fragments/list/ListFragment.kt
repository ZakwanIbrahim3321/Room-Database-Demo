package com.example.roomdbproject.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdbproject.R
import com.example.roomdbproject.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

  private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)


        //adapter and rv
        val adapter = ItemRvAdapter()
        val recyclerView = view.recycler_view
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //view model
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.readAllData.observe(viewLifecycleOwner, Observer{ user ->
          adapter.setData(user)
        })

        view.floating_action_button.setOnClickListener {
          findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.delete_menu, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.menu_delete){
      deleteAllUsers()
    }
    return super.onOptionsItemSelected(item)
  }

  private fun deleteAllUsers(){
    val builder = AlertDialog.Builder(requireContext())
    builder.setTitle("Delete All Users ?")
    builder.setMessage("Are you sure you want to delete all users?")
    builder.setPositiveButton("Yes"){ _, _ ->
      userViewModel.deleteAllUsers()
      Toast.makeText(requireContext(),
        "Users Deleted!",
        Toast.LENGTH_SHORT).show()
    }
    builder.setNegativeButton("No"){ _, _ -> }
    builder.create().show()
  }

}
