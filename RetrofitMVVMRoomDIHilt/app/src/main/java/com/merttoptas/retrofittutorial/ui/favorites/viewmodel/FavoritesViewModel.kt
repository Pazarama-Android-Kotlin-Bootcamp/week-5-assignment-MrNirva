package com.merttoptas.retrofittutorial.ui.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merttoptas.retrofittutorial.data.local.database.entity.PostEntity
import com.merttoptas.retrofittutorial.data.repository.posts.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {
    private var _postLiveData = MutableLiveData<List<PostEntity>?>()
    val postLiveData: LiveData<List<PostEntity>?>
        get() = _postLiveData

    init {
        getPosts()
    }

    private fun getPosts() {

        _postLiveData.postValue(postRepository.getFavoritePosts())

    }

}