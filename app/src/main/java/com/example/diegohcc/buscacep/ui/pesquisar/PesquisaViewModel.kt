package com.example.diegohcc.buscacep.ui.pesquisar

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.diegohcc.buscacep.model.Endereco
import com.example.diegohcc.buscacep.repository.EnderecoRepository

class PesquisaViewModel : ViewModel() {

    val enderecoRepository = EnderecoRepository()
    val endereco = MutableLiveData<Endereco>()
    val msgErro = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    fun buscar(cep: String) {
        isLoading.value = true
        enderecoRepository.buscar(
                cep,
                onComplete = {
                    endereco.value = it
                    msgErro.value = ""
                    isLoading.value = false
                },
                onError = {
                    endereco.value = null
                    msgErro.value = it?.message
                    isLoading.value = false
                }
        )
    }
}