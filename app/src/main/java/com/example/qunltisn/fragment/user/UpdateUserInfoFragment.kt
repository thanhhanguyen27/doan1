package com.example.qunltisn.fragment.user

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.qunltisn.*
import com.example.qunltisn.databinding.UpdateUserInfoFragmentBinding
import com.example.qunltisn.model.QLTSUserInfo
import com.example.qunltisn.model.request.UpdateUserInfoReq
import com.example.qunltisn.retrofit.WebInterface
import com.example.qunltisn.model.response.user.QLTSUserInfoRes
import com.example.qunltisn.model.response.user.UpdateUserInfoRes
import com.example.qunltisn.viewmodel.UpdateUserInfoViewModel
import kotlinx.android.synthetic.main.update_user_info_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpdateUserInfoFragment : Fragment() {

    companion object {
        fun newInstance() = UpdateUserInfoFragment()
    }

    private lateinit var viewModel: UpdateUserInfoViewModel
    private lateinit var binding: UpdateUserInfoFragmentBinding
    //private var userinfo: QLTSUserInfo? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding= DataBindingUtil.inflate(inflater,R.layout.update_user_info_fragment, container, false)
        val pref = requireContext().getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
        val username = pref.getString(KEY_USERNAME, "")
        val token = pref.getString(KEY_TOKEN, "")
        getQLTSUserInfo(username.toString(), token.toString())
        return binding.root
    }
    private fun getQLTSUserInfo(username: String, token: String) {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(WebInterface::class.java)
        val request = service.getQLTSUserInfo(username, token)
        request.enqueue(object : Callback<QLTSUserInfoRes> {
            override fun onFailure(call: Call<QLTSUserInfoRes>, t: Throwable) {
                Log.d("Main", "ERROR ${t.message}")
            }

            override fun onResponse(
                call: Call<QLTSUserInfoRes>,
                response: Response<QLTSUserInfoRes>
            ) {
                Log.d("UPDATE_USER_INFO", "${response.body()}")
                val userInfoRes = response.body()
                if (userInfoRes!!.respcode == 0) {
                    binding.apply {
                        edtname.setText(userInfoRes.userinfo.fullname)
                        edtsex.setText(userInfoRes.userinfo.sex)
                        edtAddress.setText(userInfoRes.userinfo.address)
                        edtPhone.setText(userInfoRes.userinfo.phonenumber)
                        edtECode.setText(userInfoRes.userinfo.employeecode)
                        edtEType.setText(userInfoRes.userinfo.employeetype)
                        edtMoreInfo.setText(userInfoRes.userinfo.moreinfo)
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_updateuserinfo, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.buttonLuu -> {
                val pref = requireContext().getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
                val username = pref.getString(KEY_USERNAME, "")
                val token = pref.getString(KEY_TOKEN, "")
                val userinfo = QLTSUserInfo("", edtname.text.toString(), edtAddress.text.toString(),edtEmail.text.toString(), edtPhone.text.toString(), edtsex.text.toString(), edtECode.text.toString(), edtEType.text.toString(), edtMoreInfo.text.toString())
                val updateUserInfoReq = UpdateUserInfoReq(username.toString(), token.toString(), userinfo)
                updateInfo(updateUserInfoReq)
                 requireActivity().onBackPressed()
                true
            }
            android.R.id.home -> {
                requireActivity().onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun updateInfo( updateUserInfoReq: UpdateUserInfoReq){
        val retrofit= Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(WebInterface::class.java)
        val request = service.GetUpdateUserInfo( updateUserInfoReq)
        request.enqueue(object : Callback<UpdateUserInfoRes>{
            override fun onResponse(
                call: Call<UpdateUserInfoRes>,
                response: Response<UpdateUserInfoRes>
            ) {
                Log.d("UPDATE1", " ${response.body()!!.toMApUpdateUserInfoRes()}")
                val updateRes = response.body()
                if (updateRes!!.respcode ==0)
                {
                  // Toast.makeText(context, " Đã lưu thông tin!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UpdateUserInfoRes>, t: Throwable) {
                Log.d("UPDATE1", "ERROR ${t.message}")
            }

        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UpdateUserInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}