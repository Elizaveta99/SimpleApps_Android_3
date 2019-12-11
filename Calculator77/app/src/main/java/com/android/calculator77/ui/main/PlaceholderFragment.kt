package com.android.calculator77.ui.main

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.ArrayList
import com.android.calculator77.MainActivity
import com.android.calculator77.R
import java.util.logging.Logger


/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var pageViewModel: PageViewModel

    var listOfItemsFrom = arrayListOf("", "")
    var listOfItemsTo = arrayListOf("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(com.android.calculator77.R.layout.fragment_main, container, false)
        val button = v.findViewById(com.android.calculator77.R.id.button) as Button
        button.setOnClickListener(this)

        listOfItemsFrom = resources.getStringArray(com.android.calculator77.R.array.listOfItemsFrom).toCollection(
            ArrayList<String>())
        listOfItemsTo = resources.getStringArray(com.android.calculator77.R.array.listOfItemsTo).toCollection(ArrayList<String>())
        Logger.getLogger(PlaceholderFragment::class.java.name).warning(listOfItemsFrom.toString())

        val spinner_from = v.findViewById<Spinner>(R.id.spinner_from)
        spinner_from?.adapter = ArrayAdapter(activity?.applicationContext, android.R.layout.simple_spinner_item, listOfItemsFrom)

        val spinner_to = v.findViewById<Spinner>(R.id.spinner_to)
        spinner_to?.adapter = ArrayAdapter(activity?.applicationContext, android.R.layout.simple_spinner_item, listOfItemsTo)

        return v
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        Logger.getLogger(PlaceholderFragment::class.java.name).warning("SELECTED")

        if(parent.id == com.android.calculator77.R.id.spinner_from)
        {
            //from.text = listOfItemsFrom[position]
            from.text = parent?.getItemAtPosition(position).toString()

            Logger.getLogger(PlaceholderFragment::class.java.name).warning("from selected")

            editText_from.isEnabled = true
        }
        else if(parent.id == com.android.calculator77.R.id.spinner_to)
        {
            //to.text = listOfItemsTo[position]
            to.text = parent?.getItemAtPosition(position).toString()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        if(parent.id == com.android.calculator77.R.id.spinner_from)
        {
            from.setText(com.android.calculator77.R.string.from)
            editText_from.isEnabled = false
        }
        else if(parent.id == com.android.calculator77.R.id.spinner_to)
        {
            to.setText(com.android.calculator77.R.string.to)
        }
    }


    private val positiveButtonClick = { dialog: DialogInterface, which: Int ->
    }

    private fun basicAlert(view: View){

        val builder = AlertDialog.Builder(view.context)

        with(builder)
        {
            setTitle(com.android.calculator77.R.string.error)
            setMessage(com.android.calculator77.R.string.wrong_input)
            setPositiveButton("OK", DialogInterface.OnClickListener(function = positiveButtonClick))
            show()
        }


    }

    override fun onClick(view: View) {
        if (view.getId() == com.android.calculator77.R.id.button) {

            try {
                var fromVal: Double = editText_from.text.toString().toDouble()
                if (fromVal >= 0) {
                    if (spinner_from!!.selectedItem.equals(listOfItemsFrom[1]))
                        fromVal /= 1000000.0

                    if (spinner_to!!.selectedItem.equals(listOfItemsTo[0])) {
                        editText_to.text = (fromVal * 10763910.0).toString()
                    } else {
                        editText_to.text = (fromVal * 247.1).toString()
                    }
                } else {
                    basicAlert(view)
                }
            } catch (e: NumberFormatException) {
                basicAlert(view)
            }
        }

    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}