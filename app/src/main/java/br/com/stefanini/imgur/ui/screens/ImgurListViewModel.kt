package br.com.stefanini.imgur.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.stefanini.imgur.domain.models.ImgurModel

class ImgurListViewModel : ViewModel() {

    private val _images = MutableLiveData<List<ImgurModel>>()
    val images: LiveData<List<ImgurModel>> get() = _images

}