package com.antiglukapps.monikaapp.ui.storage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.antiglukapps.monikaapp.AddStorageItemActivity
import com.antiglukapps.monikaapp.R
import com.antiglukapps.monikaapp.data.MonicaDatabase
import com.antiglukapps.monikaapp.data.StorageItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StorageFragment : Fragment(), StorageItemAdapter.OnStorageItemClickedListener {

    private lateinit var storageViewModel: StorageViewModel

    private lateinit var storageRv: RecyclerView

    private lateinit var monicaDatabase: MonicaDatabase
    private lateinit var storageItemAdapter: StorageItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        monicaDatabase = MonicaDatabase.getInstance(requireActivity())
        storageItemAdapter = StorageItemAdapter()
        storageItemAdapter.setStorageItemClickedListener(this)

        storageViewModel =
            ViewModelProvider(this).get(StorageViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_storage, container, false)

        storageRv = root.findViewById(R.id.storageRv)

        val add_button: FloatingActionButton = root.findViewById(R.id.floatingStorageAddButton)
        add_button.setOnClickListener {
            startActivity(
                Intent(
                    activity, AddStorageItemActivity::class.java
                )
            )
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        storageItemAdapter.storageItemsList = monicaDatabase.getStorageItemDao().getStorageItemList()
        storageRv.adapter = storageItemAdapter
        storageRv.layoutManager = LinearLayoutManager(activity)
        storageRv.hasFixedSize()
    }

    override fun onStorageItemClicked(storageItem: StorageItem) {
        TODO("Not yet implemented")
    }

    override fun onStorageItemLongClicked(storageItem: StorageItem) {
        TODO("Not yet implemented")
    }
}
