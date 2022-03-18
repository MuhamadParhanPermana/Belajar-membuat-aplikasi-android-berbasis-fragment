package com.example.myflexiblefragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class DetailCategoryFragment : Fragment(), View.OnClickListener {
    lateinit var tvCategoryName: TextView
    lateinit var tvCategoryDesc: TextView
    lateinit var btnProfile: Button
    lateinit var btnShowDialog: Button

    var description: String? = null

    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_DESC = "extra_desc"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvCategoryName = view.findViewById(R.id.tv_category_name)
        tvCategoryDesc = view.findViewById(R.id.tv_category_desc)
        btnProfile = view.findViewById(R.id.btn_profile)
        btnShowDialog = view.findViewById(R.id.btn_show_dialog)

        btnProfile.setOnClickListener(this)
        btnShowDialog.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState !=null) {
            val descFromBundle = savedInstanceState.getString(EXTRA_DESC)
            description = descFromBundle
        }

        if (arguments !=null) {
            val categoryName = arguments?.getString(EXTRA_NAME)
            tvCategoryName.text = categoryName
            tvCategoryDesc.text = description
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_profile -> {
                val mIntent = Intent(activity, ProfileActivity::class.java)
                startActivity(mIntent)
            }
            R.id.btn_show_dialog -> {
                val moptionDialogFragment = OptionDialogFragment()

                val mFragmentManager = childFragmentManager
                moptionDialogFragment.show(mFragmentManager, OptionDialogFragment::class.java.simpleName)
            }
        }
    }

    internal var optionDialogListener: OptionDialogFragment.OnOptionDialogListener = object : OptionDialogFragment.OnOptionDialogListener {
        override fun onOptionChosen(text: String?) {
            Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
        }
    }
}