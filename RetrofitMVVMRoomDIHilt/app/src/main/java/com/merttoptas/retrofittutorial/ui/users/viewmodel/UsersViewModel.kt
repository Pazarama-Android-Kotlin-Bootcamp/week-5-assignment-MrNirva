package com.merttoptas.retrofittutorial.ui.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merttoptas.retrofittutorial.data.local.database.entity.PostEntity
import com.merttoptas.retrofittutorial.data.local.database.entity.UsersEntity
import com.merttoptas.retrofittutorial.data.model.*
import com.merttoptas.retrofittutorial.data.repository.posts.PostRepository
import com.merttoptas.retrofittutorial.data.repository.users.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private var _userLiveData = MutableLiveData<DataState<List<UserDTO>?>>()
    val userLiveData: LiveData<DataState<List<UserDTO>?>>
        get() = _userLiveData

    private val _eventStateLiveData = MutableLiveData<UserViewEvent>()
    val eventStateLiveData: LiveData<UserViewEvent>
        get() = _eventStateLiveData

    init {
        getUsers()
    }

    private fun getUsers() {
        _userLiveData.postValue(DataState.Loading())
        userRepository.getUsers().enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    response.body()?.let {

                        _userLiveData.postValue(DataState.Success(it.map { safeUser ->
                            UserDTO(
                                id = safeUser.id,
                                address = safeUser.address,
                                company = safeUser.company,
                                email = safeUser.email,
                                name = safeUser.name,
                                phone = safeUser.phone,
                                username = safeUser.username,
                                website = safeUser.website,
                                isFavorite = isExists(safeUser.id)
                            )
                        }))

                    } ?: kotlin.run {
                        _userLiveData.postValue(DataState.Error("Data Empty"))
                    }
                } else {
                    _userLiveData.postValue(DataState.Error(response.message()))
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                _userLiveData.postValue(DataState.Error(t.message.toString()))
                _eventStateLiveData.postValue(UserViewEvent.ShowMessage(t.message.toString()))
            }
        })
    }

    fun onFavoriteUser(user: UserDTO) {
        userRepository.getUserById(user.id ?: 0)?.let {
            userRepository.deleteFavoriteUser(
                UsersEntity(
                    id = user.id,
                    address = user.address,
                    company = user.company,
                    email = user.email,
                    name = user.name,
                    phone = user.phone,
                    username = user.username,
                    website = user.website,
                )
            )
        } ?: kotlin.run {
            userRepository.insertFavoriteUser(
                UsersEntity(
                    id = user.id,
                    address = user.address,
                    company = user.company,
                    email = user.email,
                    name = user.name,
                    phone = user.phone,
                    username = user.username,
                    website = user.website,
                )
            )
        }
    }

    private fun isExists(userID: Int?): Boolean {
        userID?.let {
            userRepository.getUserById(it)?.let {
                return true
            }
        }
        return false
    }
}

sealed class UserViewEvent {
    object NavigateToDetail : UserViewEvent()
    class ShowMessage(val message: String?) : UserViewEvent()
}