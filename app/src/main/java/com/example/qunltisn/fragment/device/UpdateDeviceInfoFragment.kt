package com.example.qunltisn.fragment.device

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.qunltisn.HomeActivity
import com.example.qunltisn.R
import com.example.qunltisn.databinding.UpdateDeviceInfoFragmentBinding
import com.example.qunltisn.databinding.UpdateUserInfoFragmentBinding
import com.example.qunltisn.model.TSDeviceSetInfo
import com.example.qunltisn.viewmodel.UpdateDeviceInfoViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.add_device_fragment.*
import kotlinx.android.synthetic.main.device_info_fragment.view.*
import kotlinx.android.synthetic.main.update_device_info_fragment.*
import kotlinx.android.synthetic.main.update_device_info_fragment.edtDeviceCode
import kotlinx.android.synthetic.main.update_device_info_fragment.edtDeviceId
import kotlinx.android.synthetic.main.update_device_info_fragment.edtDeviceName
import kotlinx.android.synthetic.main.update_device_info_fragment.edtLocation
import kotlinx.android.synthetic.main.update_device_info_fragment.edtManufacturer
import kotlinx.android.synthetic.main.update_device_info_fragment.edtOrigin
import kotlinx.android.synthetic.main.update_device_info_fragment.edtStatus
import kotlinx.android.synthetic.main.update_device_info_fragment.edtTimeEnd
import kotlinx.android.synthetic.main.update_device_info_fragment.edtTimeStart
import kotlinx.android.synthetic.main.update_device_info_fragment.edtType

class UpdateDeviceInfoFragment : Fragment() {

    companion object {
        fun newInstance() = UpdateDeviceInfoFragment()
    }

    private lateinit var viewModel: UpdateDeviceInfoViewModel
    private lateinit var binding: UpdateDeviceInfoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding= DataBindingUtil.inflate(inflater, R.layout.update_device_info_fragment, container, false)
        val device = UpdateDeviceInfoFragmentArgs.fromBundle(requireArguments()).device
        binding.apply {
            Picasso.get().load(device.Image).into(imageDevice, object : Callback {
                override fun onSuccess() {
                    Log.d("_LOADIMAGEs", "success")
                }

                override fun onError(e: Exception?) {
                    Log.d("_LOADIMAGEs", "error ${e!!.message} ")
                }
            })
            edtDeviceId.setText(device.DeviceId.toString())
            edtDeviceName.setText(device.DeviceName)
            edtDeviceCode.setText(device.DeviceCode)
            edtType.setText(device.Type)
            edtLocation.setText(device.Location)
            edtTimeStart.setText(device.TimeStart)
            edtManufacturer.setText(device.Manufacturer)
            edtTimeEnd.setText(device.TimeEnd)
            edtOrigin.setText(device.Origin)
            edtStatus.setText(device.Status.toString())
            buttonUpdate.setOnClickListener {
                var db = FirebaseFirestore.getInstance()
                val device1 = TSDeviceSetInfo(device.Image,
                    edtDeviceId.text.toString(),
                    edtDeviceName.text.toString(),
                    edtDeviceCode.text.toString(),
                    0,
                    "",
                    edtType.text.toString(),
                    edtLocation.text.toString(),
                    edtTimeStart.text.toString(),
                    edtManufacturer.text.toString(),
                    edtTimeEnd.text.toString(),
                    "",
                    "",
                    " ",
                    edtOrigin.text.toString(),
                    "",
                    "",
                    0,
                    edtStatus.text.toString().toInt()
                )
                db.collection("devices").document(device.DeviceId).set(device1).addOnSuccessListener { documentReference ->
                    Log.d("_UPDATE", "Cập nhật thành công thiết bị")
                    Toast.makeText(context, "Cập nhật thiết bị thành công", Toast.LENGTH_SHORT)
                        .show()
                    requireActivity().onBackPressed()
                }
                    .addOnFailureListener { e ->
                        Log.w("_UPDATE", "có lỗi xảy ra ${e!!.message}", e)
                    }
            }
        }

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            requireActivity().onBackPressed()
        }
            return super.onOptionsItemSelected(item)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UpdateDeviceInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}