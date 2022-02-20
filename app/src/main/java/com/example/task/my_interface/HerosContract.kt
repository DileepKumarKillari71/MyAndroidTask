package com.example.task.my_interface

import com.example.task.pojo.Hero
import com.example.task.my_interface.HerosContract.Model.OnFinishListener

interface HerosContract {
    interface View {
        fun showProgressDialog()
        fun hideProgressDialog()
        fun setDataToRecyclerView(data: List<Hero>)
        fun onResponseFailiure(throwable: Throwable)
    }
    interface Presenter {
        fun onDestroy()
        fun loadDataFromApi()
    }
    interface Model {
        interface OnFinishListener {
            fun onFinished(data: List<Hero>?)
            fun onFail(throwable: Throwable)
        }
        fun getHerosList(onFinishListener: OnFinishListener)
    }
}