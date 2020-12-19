package com.example.qunltisn.fragment.device

import android.Manifest
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.example.qunltisn.*
import com.example.qunltisn.viewmodel.AddDeviceViewModel
import com.example.qunltisn.databinding.AddDeviceFragmentBinding
import com.example.qunltisn.model.TSDeviceSetInfo
import com.example.qunltisn.retrofit.WebInterface
import com.example.qunltisn.model.response.device.CreateDeviceInfoRes
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.add_device_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.UUID.randomUUID

class AddDeviceFragment : Fragment() {

    companion object {
        fun newInstance() = AddDeviceFragment()
    }

    private lateinit var viewModel: AddDeviceViewModel
    private lateinit var binding: AddDeviceFragmentBinding
   // private lateinit var takePhotoHelper:
    private var img1Path: String? = null
    private val REQUEST_IMAGE1_CAPTURE = 1001
    private var isUploadImage1Success = false
    private val PERMISSION_CODE = 1001
    private val IMAGE_PICK_CODE = 1000
    private var image_uri: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.add_device_fragment, container, false)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //(activity as AppCompatActivity).supportActionBar?.show()
        //var db = Firebase.firestore
        binding.apply {
            buttonAddImage.setOnClickListener{
                taianh()
            }
            buttonViewImage.setOnClickListener{
                taianh()
            }

            buttonAdd.setOnClickListener {

                var db = FirebaseFirestore.getInstance()
                var device = TSDeviceSetInfo(image_uri.toString(), randomUUID().toString(), edtDeviceName.text.toString(), "",0, "" ,edtType.text.toString(), edtLocation.text.toString(),edtTimeStart.text.toString(), edtManufacturer.text.toString(), edtTimeEnd.text.toString(),"", edtTimeCreate.text.toString(), edtCreaterId.text.toString(), edtOrigin.text.toString(), "", "", edtSchedule.text.toString().toInt(), edtStatus.text.toString().toInt())
                //device.DeviceId = randomUUID().toString()
//                device.Image=image_uri.toString()
                db.collection("devices").document(device.DeviceId).set(device).addOnSuccessListener {  documentReference ->
                    Log.d("ADD", "Tạo thành công thiết bị với ID")
                    Toast.makeText(context,"Thêm thiết bị thành công", Toast.LENGTH_SHORT).show()
                    requireActivity().onBackPressed() }
                    .addOnFailureListener { e ->
                Log.w("ADD", "có lỗi xảy ra", e)
            }
            }
        }
        return binding.root

    }
//    private fun addImage(requestCode: Int){
//
//    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        if (item.itemId == android.R.id.home){
            requireActivity().onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun taianh(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (PermissionChecker.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PermissionChecker.PERMISSION_DENIED){
                //permission denied
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                //show popup to request runtime permission
                requestPermissions(permissions, PERMISSION_CODE);
            }
            else{
                //permission already granted
                pickImageFromGallery();
            }
        }
        else{
            //system OS is < Marshmallow
            pickImageFromGallery();
        }
    }
    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //called when user presses ALLOW or DENY from Permission Request Popup
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup was granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup was denied
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //called when image was captured from camera intent
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            //set image captured to image view
            binding.buttonViewImage.setImageURI(image_uri)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddDeviceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}


