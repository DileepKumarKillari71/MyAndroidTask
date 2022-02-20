package com.example.task

import androidx.appcompat.app.AppCompatActivity
import com.example.task.my_interface.HerosContract
import androidx.recyclerview.widget.RecyclerView
import com.example.task.pojo.Hero
import com.example.task.adapter.HerosAdapter
import android.widget.ProgressBar
import com.example.task.presenter.HerosPresenter
import android.os.Bundle
import android.view.View
import com.example.task.R
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import java.util.ArrayList

class MainActivity : AppCompatActivity(), HerosContract.View {
    private var recyclerView: RecyclerView? = null
    private val herosList: MutableList<Hero> = ArrayList()
    private var herosAdapter: HerosAdapter? = null
    private val TAG = "MainActivity"
    private var progressbar: ProgressBar? = null
    private var herosPresenter: HerosPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // init UI
        initUi()
        //init presenter
        herosPresenter = HerosPresenter(this)
        herosPresenter!!.loadDataFromApi()
    }

    private fun initUi() {
        progressbar = findViewById<View>(R.id.progressbar) as ProgressBar
        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        herosAdapter = HerosAdapter(this@MainActivity)
        recyclerView!!.adapter = herosAdapter
    }

    override fun showProgressDialog() {
        progressbar!!.visibility = View.VISIBLE
    }

    override fun hideProgressDialog() {
        progressbar!!.visibility = View.INVISIBLE
    }

    override fun setDataToRecyclerView(data: List<Hero>) {
        herosList.clear()
        herosList.addAll(data)
        herosAdapter!!.setData(herosList)
    }

    override fun onResponseFailiure(throwable: Throwable) {
        Toast.makeText(this, "issue: " + throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        herosPresenter!!.onDestroy()
    }
}