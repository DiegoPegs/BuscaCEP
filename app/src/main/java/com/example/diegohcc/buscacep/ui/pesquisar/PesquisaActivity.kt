package com.example.diegohcc.buscacep.ui.pesquisar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.diegohcc.buscacep.R
import com.example.diegohcc.buscacep.api.EnderecoService
import com.example.diegohcc.buscacep.model.Endereco
import com.example.diegohcc.buscacep.repository.EnderecoRepository
import kotlinx.android.synthetic.main.activity_pesquisa.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient




class PesquisaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa)

        btPesquisar.setOnClickListener {
            pesquisar()
        }
    }

     fun pesquisar() {

         val okhttp = OkHttpClient.Builder()
                 .addNetworkInterceptor(StethoInterceptor())
                 .build();

         val retrofit = Retrofit.Builder()
                 .baseUrl("https://viacep.com.br/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .client(okhttp)
                 .build()

         val service = retrofit.create(EnderecoService::class.java)

         service.buscar(etCEP.text.toString())
                 .enqueue(object : Callback<Endereco>{
                     override fun onFailure(call: Call<Endereco>?, t: Throwable?) {
                         Toast.makeText(this@PesquisaActivity, t?.message, Toast.LENGTH_LONG).show()
                     }

                     override fun onResponse(call: Call<Endereco>?, response: Response<Endereco>?) {
                         if (response?.isSuccessful == true){
                             tvResultado.text = response.body()?.logradouro
                         }else{
                             Toast.makeText(this@PesquisaActivity, "Erro ao buscar o CEP", Toast.LENGTH_LONG).show()
                         }
                     }

                 })

    }


}
