package com.example.task.model

import com.example.task.my_interface.HerosContract
import com.example.task.my_interface.HerosContract.Model.OnFinishListener
import com.example.task.network.Api
import com.example.task.network.ResponseData
import com.example.task.pojo.Hero
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HerosListModel : HerosContract.Model {
    /*below method will provide the all heros list*/
    override fun getHerosList(onFinishListener: OnFinishListener) {
        Api.getInstance().retrofit.doGetListResources()
                .enqueue(object : Callback<ResponseData<List<Hero>>> {
                    override fun onResponse(call: Call<ResponseData<List<Hero>>>, response: Response<ResponseData<List<Hero>>>) {
                        val data = response.body()!!.data
                        onFinishListener.onFinished(data)
                    }
                    override fun onFailure(call: Call<ResponseData<List<Hero>>>, t: Throwable) {
                        onFinishListener.onFail(t)
                        call.cancel()
                    }
                })
    }
}


