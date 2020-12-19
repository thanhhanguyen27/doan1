package com.example.qunltisn.adapter

import android.content.ContentValues
import android.icu.number.NumberFormatter.with
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import com.example.qunltisn.R
import com.example.qunltisn.databinding.ItemDeviceBinding
import com.example.qunltisn.fragment.device.FragmentListDevice
import com.example.qunltisn.model.TSDeviceSetInfo
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class DeviceAdapter(var devices: List<TSDeviceSetInfo>, private val onItemButtonClick: FragmentListDevice):RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {
    class ViewHolder(
        var binding: ItemDeviceBinding, private val onItemButtonClick: DeviceAdapter.OnItemButtonClick
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindView(device: TSDeviceSetInfo) {
            binding.device = device
            Log.d("_LOADIMAGE", device.Image)
            Picasso.get().load(device.Image).centerCrop().fit().into(binding.imageDevice, object : Callback{
                override fun onSuccess() {
                    Log.d("_LOADIMAGE", "success")
                }

                override fun onError(e: Exception?) {
                    Log.d("_LOADIMAGE", "error ${e!!.message} ")
                }
            })
            // binding.imageDevice.setImageResource(device.anh)
            binding.apply {
                root.setOnClickListener{
                    onItemButtonClick.onItemClick(device)
                }
                buttonChiTiet.setOnClickListener {
                    onItemButtonClick.onRetailButtonClick(device)
                }
                buttonChinhSua.setOnClickListener {
                    onItemButtonClick.onUpdateButtonClick(device)
                }
                buttonXoa.setOnClickListener {
                    onItemButtonClick.onDeleteButtonClick(device)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceAdapter.ViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<ItemDeviceBinding>(layoutInflater, R.layout.item_device,parent, false)
        return ViewHolder(binding, onItemButtonClick)
    }

    override fun onBindViewHolder(holder: DeviceAdapter.ViewHolder, position: Int) {
        val device= devices[position]
        holder.bindView(device)
    }

    override fun getItemCount(): Int {
        return devices.size
    }
    interface OnItemButtonClick{
        fun onRetailButtonClick(device: TSDeviceSetInfo)
        fun onUpdateButtonClick(device: TSDeviceSetInfo)
        fun onDeleteButtonClick(device: TSDeviceSetInfo)
        fun onItemClick(device: TSDeviceSetInfo)
    }

}