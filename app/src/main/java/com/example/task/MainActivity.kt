package com.example.task

import androidx.appcompat.app.AppCompatActivity
import com.example.task.my_interface.HerosContract
import com.example.task.pojo.Hero
import com.example.task.adapter.HerosAdapter
import com.example.task.presenter.HerosPresenter
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import com.example.task.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity(), HerosContract.View {
    private val herosList: MutableList<Hero> = ArrayList()
    private var herosAdapter: HerosAdapter? = null
    private val TAG = "MainActivity"
    private var herosPresenter: HerosPresenter? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

// init Recyclerview
        initRecyclerview()
//init presenter
        herosPresenter = HerosPresenter(this)
        herosPresenter?.loadDataFromApi()
    }

    private fun initRecyclerview() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        herosAdapter = HerosAdapter(this@MainActivity)
        binding.recyclerView.adapter = herosAdapter;
    }

    override fun showProgressDialog() {
        binding.progressbar.visibility = View.VISIBLE
    }

    override fun hideProgressDialog() {
        binding.progressbar.visibility = View.INVISIBLE
    }

    override fun setDataToRecyclerView(data: List<Hero>) {
        data?.let {
            herosList.clear()
            herosList.addAll(data)
            herosAdapter?.setData(herosList) } ?: run {
            Toast.makeText(this, "data not existed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResponseFailiure(throwable: Throwable) {
        Toast.makeText(this, "issue: " + throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        herosPresenter?.onDestroy()
    }
}