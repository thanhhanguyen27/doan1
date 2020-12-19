package com.example.qunltisn.fragment.organization

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qunltisn.R
import com.example.qunltisn.viewmodel.OrganizationViewModel

class OrganizationFragment : Fragment() {

    companion object {
        fun newInstance() = OrganizationFragment()
    }

    private lateinit var viewModel: OrganizationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.organization_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OrganizationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}