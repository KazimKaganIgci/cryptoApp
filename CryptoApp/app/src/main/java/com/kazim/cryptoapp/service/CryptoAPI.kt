package com.kazim.cryptoapp.service

import com.kazim.cryptoapp.model.CryptoModelItem
import io.reactivex.Observable

import retrofit2.http.GET

interface CryptoAPI {
    @GET("ticker?key=api keyi gir")
    fun getData():Observable<List<CryptoModelItem>>
    //list<CrytoModelItem>
}