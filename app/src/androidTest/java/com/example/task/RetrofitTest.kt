package com.example.task

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.task.model.HerosListModel
import com.example.task.my_interface.HerosContract
import com.example.task.pojo.Hero
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.util.concurrent.CountDownLatch

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RetrofitTest {
    @Before // testcase to check response of the api
    @Test
    fun testApiResponse() {
        val herosListModel = HerosListModel()
        val latch = CountDownLatch(1)
        herosListModel.getHerosList(object : HerosContract.Model.OnFinishListener {
            override fun onFinished(data: List<Hero>?) {
                // checking received proper response or not
                Assert.assertFalse(data.isNullOrEmpty())
                // checking received length is 12 or not
                Assert.assertEquals(data?.size,12)
                latch.countDown()
            }

            override fun onFail(throwable: Throwable) {
                latch.countDown()
            }
        })
        try {
            latch.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    companion object {
        const val TAG = "RetrofitTest"
    }
}