package com.example.qunltisn.fragment.device

import android.content.ContentValues.TAG
import android.media.MediaPlayer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.service.autofill.Validators.or
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qunltisn.adapter.DeviceAdapter
import com.example.qunltisn.databinding.FragmentListDeviceFragmentBinding
import com.example.qunltisn.viewmodel.FragmentListDeviceViewModel
import com.example.qunltisn.R
import com.example.qunltisn.model.TSDeviceSetInfo
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment__quen_mat_khau_fragment.*
import kotlinx.android.synthetic.main.fragment_list_device_fragment.*
import kotlinx.android.synthetic.main.item_device.*
import javax.security.auth.callback.Callback


class FragmentListDevice : Fragment(),  DeviceAdapter.OnItemButtonClick{

    companion object {
        fun newInstance() = FragmentListDevice()
    }

    private lateinit var viewModel: FragmentListDeviceViewModel
    private lateinit var binding: FragmentListDeviceFragmentBinding
    private lateinit var toolbar: Toolbar
    private var deviceFiltered: ArrayList<TSDeviceSetInfo> = arrayListOf()
    private var devices1: ArrayList<TSDeviceSetInfo> = ArrayList()
    private lateinit var deviceAdapter: DeviceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //setHasOptionsMenu(true)
        // (activity as AppCompatActivity).supportActionBar?.hide()
        deviceAdapter = DeviceAdapter(devices1, this)
        getDevice()
        binding=DataBindingUtil.inflate(inflater,
            R.layout.fragment_list_device_fragment, container, false)
        binding.recyclerView.apply{
            adapter = deviceAdapter
            layoutManager= LinearLayoutManager(context)
        }
        seachView()
        toolbar= binding.topAppBar
        val fab = binding.floatingAddDevice
        fab.setOnClickListener{
            openFragmentAddDevice()
        }
        toolbar.setOnMenuItemClickListener{
            when (it.itemId) {
                R.id.QRCode -> {

                    true
                }else ->false
            }
        }
        return  binding.root
    }
    private fun getDevice(){
        val db = FirebaseFirestore.getInstance()
        db.collection("devices").get()
            .addOnSuccessListener { result ->

                devices1 = ArrayList(result.map {
                    it.toObject<TSDeviceSetInfo>()
                })
                Log.d("_FragmentListDevice", devices1.toString())
                deviceAdapter.devices = devices1
                deviceAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.d("GETDEVICE", "Error getting documents: ", exception)
            }

    }
    private fun seachView(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null && newText.isNotEmpty()) {
                    deviceFiltered.clear()
                    devices1.forEach { device ->
                        if ((device.DeviceName.toLowerCase()
                                .contains(newText.toLowerCase())) || (device.DeviceId
                                .contains(newText))) {
                            deviceFiltered.add(device)
                        }
                    }
                    deviceAdapter.devices = deviceFiltered
                    deviceAdapter.notifyDataSetChanged()
                } else {
                    deviceAdapter.devices = devices1
                    deviceAdapter.notifyDataSetChanged()
                }
                return true
            }
        })
    }
    private fun  openFragmentAddDevice(){
        findNavController().navigate(R.id.action_fragmentListDevice_to_addDeviceFragment)
    }
    override fun onRetailButtonClick(device: TSDeviceSetInfo){
        requireView().findNavController().navigate(FragmentListDeviceDirections.actionFragmentListDeviceToDeviceInfoFragment(device))
    }
    override fun onUpdateButtonClick(device: TSDeviceSetInfo){
        requireView().findNavController().navigate(FragmentListDeviceDirections.actionFragmentListDeviceToUpdateDeviceInfoFragment(device))
    }

    override fun onDeleteButtonClick(device: TSDeviceSetInfo) {
        val db = FirebaseFirestore.getInstance()
        db.collection("devices").document(device.DeviceId).delete().addOnSuccessListener {
            Log.d("_DELETE", "Đã xóa thiết bị")
            Toast.makeText(context, "Đã xóa thiết bị", Toast.LENGTH_SHORT).show()}
            .addOnFailureListener{e->
                Log.d("_DELETE", "${e!!.message}", e)
            }
    }

    override fun onItemClick(device: TSDeviceSetInfo){
        requireView().findNavController().navigate(FragmentListDeviceDirections.actionFragmentListDeviceToDeviceInfoFragment(device))
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentListDeviceViewModel::class.java)
        // TODO: Use the ViewModel
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.menu_list_device, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when(item.itemId){
//            R.id.search -> {
//                //xu li
//                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//
//        }
//    }



}
//0353606795
//botruong