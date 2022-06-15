package com.kazim.cryptoapp

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.recyclerview.widget.LinearLayoutManager
import com.kazim.cryptoapp.adapter.RecyclerAdapter
import com.kazim.cryptoapp.databinding.ActivityMainBinding
import com.kazim.cryptoapp.model.CryptoModelItem
import com.kazim.cryptoapp.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private val BASE_URL ="https://api.nomics.com/v1/currencies/"
    private var cryptoModels:ArrayList<CryptoModelItem> ?=null
    private lateinit var recyclerAdapter: RecyclerAdapter
    private var mDisposable:CompositeDisposable ?=null
    var handler:Handler= Handler()
    var runnable:Runnable?=null
    var delay =10000

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        mDisposable = CompositeDisposable()






    }

    override fun onResume() {
        handler.postDelayed(Runnable {
            handler.postDelayed(runnable!!, delay.toLong())
            loadData()
        }.also { runnable = it }, delay.toLong())
        super.onResume()
    }
    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable!!)
    }




    private fun loadData(){
        val retrofit=Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)


        mDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))





    }
    private fun handleResponse(cryptoList: List<CryptoModelItem>){
        cryptoModels =ArrayList(cryptoList)
        recyclerAdapter =RecyclerAdapter(cryptoModels!!)
        binding.recyclerView.adapter =recyclerAdapter
    }

    /*override fun onDestroy() {
        super.onDestroy()
        mDisposable?.clear()
    }*/
}