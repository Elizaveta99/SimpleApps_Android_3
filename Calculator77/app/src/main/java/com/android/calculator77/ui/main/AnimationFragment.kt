package com.android.calculator77.ui.main

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.calculator77.R
import kotlinx.android.synthetic.main.animation_main.*
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A placeholder fragment containing a simple view.
 */
class AnimationFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.animation_main, container, false)

        val radio1: RadioButton = root.findViewById(R.id.radioButton1)
        val radio2: RadioButton = root.findViewById(R.id.radioButton3)
        val image: ImageView = root.findViewById(R.id.hit_image)

        radio1.setOnClickListener {
            val animation: Animation =
                AnimationUtils.loadAnimation(activity?.getApplicationContext(), R.anim.first_animation)
            image.startAnimation(animation)
            radio1.isChecked = false
        }


        radio2.setOnClickListener {
            val animation: Animation =
                AnimationUtils.loadAnimation(activity?.getApplicationContext(), R.anim.second_animation)
            image.startAnimation(animation)
            radio2.isChecked = false
        }


            return root
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
        fun newInstance(sectionNumber: Int): AnimationFragment {
            return AnimationFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}