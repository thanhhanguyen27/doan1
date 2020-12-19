package com.example.qunltisn.fragment.user

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.qunltisn.BASE_URL
import com.example.qunltisn.HomeActivity
import com.example.qunltisn.MainActivity
import com.example.qunltisn.R
import com.example.qunltisn.databinding.CheckOTFragmentBinding
import com.example.qunltisn.retrofit.WebInterface
import com.example.qunltisn.model.response.user.QLTSCheckOTPLostPasswordRes
import com.example.qunltisn.model.response.user.QLTSLotPasswordRes
import com.example.qunltisn.viewmodel.CheckOTViewModel
import kotlinx.android.synthetic.main.check_o_t_fragment.*
import kotlinx.android.synthetic.main.fragment__quen_mat_khau_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CheckOTPFragment : Fragment() {

    companion object {
        fun newInstance() = CheckOTPFragment()
    }

    private lateinit var viewModel: CheckOTViewModel
    private lateinit var binding: CheckOTFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding= DataBindingUtil.inflate(inflater, R.layout.check_o_t_fragment, container, false)
        binding.btncheckOTP.setOnClickListener {
            checkLotPass(edtcheckEmail.text.toString(), edtcheckOTP.text.toString())
        }
        binding.tvOTP.setOnClickListener {
            lotPass(edtNhapEmail.text.toString())
        }
        return binding.root
    }
    private fun checkLotPass(email: String, code: String){
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(WebInterface::class.java)
        val request = service.getQLTSCheckOTPLostPassword(email, code)
        request.enqueue(object : Callback<QLTSCheckOTPLostPasswordRes>{
            override fun onResponse(
                call: Call<QLTSCheckOTPLostPasswordRes>,
                response: Response<QLTSCheckOTPLostPasswordRes>
            ) {
               Log.d("CheckLot", " ${response.body()!!.toMapQLTSCheckOTPLostPasswordRes()}")
                val checkLotRes = response.body()
                if (checkLotRes!!.respcode ==0) {
                    Toast.makeText(requireContext(), "Mật khẩu đã gửi về email!", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<QLTSCheckOTPLostPasswordRes>, t: Throwable) {
                Log.d("CheckLot", "ERROR ${t.message}")
            }

        })
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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            requireActivity().onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CheckOTViewModel::class.java)
        // TODO: Use the ViewModel
    }

}