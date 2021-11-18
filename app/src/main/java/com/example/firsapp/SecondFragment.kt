package com.example.firsapp

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firsapp.databinding.ActivityMainBinding
import com.example.firsapp.databinding.FragmentSecondBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SecondFragment : Fragment() {

    lateinit var binding: FragmentSecondBinding
    private val adapter = ItemAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSecondBinding.inflate(layoutInflater)
        init()
    }

    private fun init() {
        binding.apply {
            rcItem.layoutManager = LinearLayoutManager(requireContext())
            rcItem.adapter=adapter
            btnAdd.setOnClickListener{
                showDialogCastom()
            }
        }
    }




    private fun showDialogCastom() {

            val builder = AlertDialog.Builder(requireContext())
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.dialog_layout , null)
            val title = dialogLayout.findViewById<EditText>(R.id.et_title)
            val discrip = dialogLayout.findViewById<EditText>(R.id.et_discrip)
            val position = dialogLayout.findViewById<EditText>(R.id.et_position)
            with(builder) {
                setTitle("Заполните новый элемент")
                setPositiveButton("OK") { dialog , wich ->
                    var index: Int
                    if(adapter.itemCount ==0)
                        index =0
                    else
                        index = adapter.itemCount-1
                    if(position==null)
                    {
                        val item = Item(
                            title.text.toString(),
                            discrip.text.toString(),
                            index
                            )
                        adapter.itemlist.add(item.position,item)
                        adapter.notifyItemInserted(item.position)

                    }
                    else
                        if(position.text.toString().toInt()>adapter.itemCount){
                            index = adapter.itemCount-1
                            val item = Item(
                                title.text.toString(),
                                discrip.text.toString(),
                                index
                            )
                            adapter.itemlist.add(item.position,item)
                            adapter.notifyItemInserted(item.position)
                        }
                    val item = Item(
                        title.text.toString(),
                    discrip.text.toString(),
                    position.text.toString().toInt())
                    adapter.itemlist.add(item.position,item)
                    adapter.notifyItemInserted(item.position)


                }
                setNegativeButton("Cancel") { dialog , wich ->
                    Log.d("Second" , "Negative button clicked")
                }
                setView(dialogLayout)
                show()
            }


    }


    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
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


