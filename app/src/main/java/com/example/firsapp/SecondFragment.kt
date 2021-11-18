package com.example.firsapp

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firsapp.databinding.ActivityMainBinding
import com.example.firsapp.databinding.FragmentSecondBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SecondFragment : Fragment() {

    lateinit var binding: FragmentSecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSecondBinding.inflate(layoutInflater)

        showDialogCastom()
    }

    private fun showDialogCastom() {
        binding.btnAdd.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.dialog_layout , null)
            with(builder) {
                setTitle("Заполните новый элемент")
                setPositiveButton("OK") { dialog , wich ->


                }
                setNegativeButton("Cancel") { dialog , wich ->
                    Log.d("Second" , "Negative button clicked")
                }
                setView(dialogLayout)
                show()
            } 
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second , container , false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String , param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1 , param1)
                    putString(ARG_PARAM2 , param2)
                }
            }
    }
}


