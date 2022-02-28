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
        herosView?.showProgressDialog()
        herosModel.getHerosList(this)
    }
    override fun onFinished(data: List<Hero>?) {
        data?.let {
            herosView?.setDataToRecyclerView(it)
        }
        herosView?.hideProgressDialog()
    }
    override fun onFail(throwable: Throwable) {
        herosView?.onResponseFailiure(throwable)
        herosView?.hideProgressDialog()
    }
    init {
        herosModel = HerosListModel()
    }
}