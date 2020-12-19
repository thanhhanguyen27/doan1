package com.example.qunltisn.fragment.device

import android.app.Activity
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.qunltisn.HomeActivity
import com.example.qunltisn.MainActivity
import com.example.qunltisn.viewmodel.DeviceInfoViewModel
import com.example.qunltisn.R
import com.example.qunltisn.databinding.DeviceInfoFragmentBinding
import com.example.qunltisn.model.TSDeviceSetInfo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.device_info_fragment.*

class DeviceInfoFragment : Fragment() {

    companion object {
        fun newInstance() = DeviceInfoFragment()
    }

    private lateinit var viewModel: DeviceInfoViewModel
    private lateinit var binding : DeviceInfoFragmentBinding
    //private var device: TSDeviceSetInfo? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      // setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.device_info_fragment, container, false)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val device = DeviceInfoFragmentArgs.fromBundle(requireArguments()).device
        binding.apply {
            binding.device= device
            Picasso.get().load(device.Image).centerCrop().fit().into(imageDevice, object : Callback {
                override fun onSuccess() {
                    Log.d("_LOADIMAGEs", "success")
                }

                override fun onError(e: Exception?) {
                    Log.d("_LOADIMAGEs", "error ${e!!.message} ")
                }
            })
//                        tenthietbi.text= device.DeviceName
//                        MaThietBi.text= device.DeviceId.toString()
//                        loaiThietBi.text= device.Type
//                        HangSanXuat.text= device.Origin
                        tvStatus.text= device.Status.toString()
                        tvDeviceCode.text= device.DeviceId
                        tvDeviceValue.text= device.DeviceValue.toString()
                        tvLocation.text= device.Location
                        tvTimeEnd.text= device.TimeEnd
                        tvTimeStart.text= device.TimeStart
                        tvManufacture.text= device.Manufacturer
                        tvOrganizationId.text= device.OrganizationID
                        tvSchedule.text= device.Schedule.toString()
                        tvNote.text= device.Note
                    }

        return binding.root
    }
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu_deviceinfo, menu)
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home -> {
            requireActivity().onBackPressed()
                true
        }
        else -> super.onOptionsItemSelected(item)
    }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DeviceInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}