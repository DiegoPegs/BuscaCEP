package com.example.diegohcc.buscacep.api

import com.example.diegohcc.buscacep.model.Endereco
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoService {
    @GET("ws/{cep}/json/")
    fun buscar(@Path("cep")cep:String): Call<Endereco>
}