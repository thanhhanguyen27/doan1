package com.example.qunltisn.fragment.user

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.qunltisn.*
import com.example.qunltisn.viewmodel.FragmentQuenMatKhauViewModel
import com.example.qunltisn.databinding.FragmentQuenMatKhauFragmentBinding
import com.example.qunltisn.retrofit.WebInterface
import com.example.qunltisn.model.response.user.QLTSLotPasswordRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Fragment_QuenMatKhau : Fragment() {

    companion object {
        fun newInstance() = Fragment_QuenMatKhau()
    }

    private lateinit var viewModel: FragmentQuenMatKhauViewModel
    private lateinit var binding: FragmentQuenMatKhauFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding= DataBindingUtil.inflate(inflater,
            R.layout.fragment__quen_mat_khau_fragment, container, false)
        val showdialog = binding.buttonGuiEmail
        binding.apply{
            textViewMatKhau.setOnClickListener {
               requireActivity().onBackPressed()
           }
            buttonGuiEmail.setOnClickListener {
                lotPass(edtNhapEmail.text.toString())
                findNavController().navigate(Fragment_QuenMatKhauDirections.actionFragmentQuenMatKhauToCheckOTPFragment())
                //showdialog()
            }

       }
        return binding.root

    }
    private fun lotPass(email: String){
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(WebInterface::class.java)
        val request = service.getQLTSLotPassword(email)
        request.enqueue(object : Callback<QLTSLotPasswordRes>{
            override fun onResponse(
                call: Call<QLTSLotPasswordRes>,
                response: Response<QLTSLotPasswordRes>
            ) {
                Log.d("Lot"," ${response.body()!!.toMapQLTSLotPasswordRes()}")
                val lotRes= response.body()
                if (lotRes!!.respcode ==0) {
                  Toast.makeText(requireContext(), "Check mail để lấy mã OTP", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<QLTSLotPasswordRes>, t: Throwable) {
                Log.d( "Lot", "ERROR ${t.message}")
            }

        })
    }

    private fun showdialog(){
        val mAlertDialog= AlertDialog.Builder(requireContext())
        mAlertDialog.setMessage(" Mật khẩu đã được gửi đến email của bạn, vui lòng kiểm tra trong email.")
        mAlertDialog.setPositiveButton("Ok"){ dialog, Id->
            Toast.makeText(requireContext(),"Ok", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            requireActivity().onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentQuenMatKhauViewModel::class.java)

    }

}
