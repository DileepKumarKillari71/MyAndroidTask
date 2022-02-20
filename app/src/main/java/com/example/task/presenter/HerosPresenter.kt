package com.example.task.presenter

import com.example.task.my_interface.HerosContract.Presenter
import com.example.task.my_interface.HerosContract.Model.OnFinishListener
import com.example.task.pojo.Hero
import com.example.task.model.HerosListModel
import com.example.task.my_interface.HerosContract

class HerosPresenter(private var herosView: HerosContract.View?) : Presenter, OnFinishListener {
    private val herosModel: HerosContract.Model
    override fun onDestroy() {
        herosView = null;
    }
    override fun loadDataFromApi() {
        if (herosView != null) {
            herosView!!.showProgressDialog()
        }
        herosModel.getHerosList(this)
    }
    override fun onFinished(data: List<Hero>?) {
        if (data != null && !data.isEmpty()) {
            herosView!!.setDataToRecyclerView(data)
        }
        if (herosView != null) {
            herosView!!.hideProgressDialog()
        }
    }
    override fun onFail(throwable: Throwable) {
        herosView!!.onResponseFailiure(throwable)
        if (herosView != null) {
            herosView!!.hideProgressDialog()
        }
    }
    init {
        herosModel = HerosListModel()
    }
}