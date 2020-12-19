package com.example.qunltisn.fragment.user

import android.content.Context.MODE_PRIVATE
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
import com.example.qunltisn.*
import com.example.qunltisn.databinding.FragmentDangKyFragmentBinding
import com.example.qunltisn.model.response.user.QLTSUserRegisterRes
import com.example.qunltisn.retrofit.WebInterface
import com.example.qunltisn.viewmodel.FragmentDangKyViewModel
import kotlinx.android.synthetic.main.fragment__dang_ky_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Fragment_DangKy : Fragment() {

    companion object {
        fun newInstance() = Fragment_DangKy()
    }

    private val TAG = "DangKi"
    private lateinit var viewModel: FragmentDangKyViewModel
    private lateinit var binding: FragmentDangKyFragmentBinding
    private var gioiTinh: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment__dang_ky_fragment, container, false)
        binding.apply{
            buttonSex.setOnCheckedChangeListener{ group, checkedId->
                gioiTinh = if (checkedId == R.id.buttonNam) "Nam" else "Nu"
//                Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
            }
            buttonDangKy2.setOnClickListener {
                getQLTSUserRegister(edittextusername.text.toString(), edittextEmail.text.toString(), edittextphonenumber.text.toString(),edittextAddress.text.toString(),gioiTinh)
            }
            buttonThoat.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentDangKyViewModel::class.java)
        Log.d(TAG, "Hello world")
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            requireActivity().onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun getQLTSUserRegister( fullname:String, email:String, phone:String, address:String, sex:String){
        val pref1= requireContext().getSharedPreferences(SHARED_NAME, MODE_PRIVATE)
        val retrofit= Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val service= retrofit.create(WebInterface::class.java)
        val request= service.getQLTSUserRegister(fullname, email, phone,address, sex)
        request.enqueue(object :Callback<QLTSUserRegisterRes>{
            override fun onFailure(call: Call<QLTSUserRegisterRes>, t: Throwable) {
                Log.d("Main", "ERROR ${t.message}")
            }

            override fun onResponse(
                call: Call<QLTSUserRegisterRes>,
                response: Response<QLTSUserRegisterRes>
            ) {
                Log.d("GetDataFromAPI", "${response.body()!!.toMapRegisterRes()}")
                val registerRes= response.body()
                if (registerRes!!.respcode==0){
                    Toast.makeText(requireContext(),"Đăng ký thành công" , Toast.LENGTH_SHORT).show()
                    pref1.edit().putString(KEY_USERNAME, edittextusername.text.toString())
                    requireActivity().onBackPressed()
                } else {
                    Toast.makeText(requireContext(),registerRes.resptext, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}