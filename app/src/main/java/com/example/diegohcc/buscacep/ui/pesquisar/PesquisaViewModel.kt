package com.example.diegohcc.buscacep.ui.pesquisar

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.diegohcc.buscacep.model.Endereco
import com.example.diegohcc.buscacep.repository.EnderecoRepository

class PesquisaViewModel : ViewModel() {

    val enderecoRepository = EnderecoRepository()
    val endereco = MutableLiveData<Endereco>()
    val msgErro = MutableLiveData<String>()

    fun buscar(cep: String) {
        enderecoRepository.buscar(
                cep,
                onComplete = {
                    endereco.value = it
                    msgErro.value = ""
                },
                onError = {
                    endereco.value = null
                    msgErro.value = it?.message
                }
        )
    }
}