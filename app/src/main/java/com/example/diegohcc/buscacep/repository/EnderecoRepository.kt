package com.example.diegohcc.buscacep.repository

import com.example.diegohcc.buscacep.api.getEnderecoService
import com.example.diegohcc.buscacep.model.Endereco
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnderecoRepository {
    fun buscar(cep:String,
               onComplete: (Endereco?) -> Unit,
               onError: (Throwable?) -> Unit){
        getEnderecoService()
                .buscar(cep)
                .enqueue(object : Callback<Endereco>{
                    override fun onFailure(call: Call<Endereco>?, t: Throwable?) {
                        onError(t)
                    }

                    override fun onResponse(call: Call<Endereco>?, response: Response<Endereco>?) {
                        onComplete(response?.body())
                    }
                })
    }
}

