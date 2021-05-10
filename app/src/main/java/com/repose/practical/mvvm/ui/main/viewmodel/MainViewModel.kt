package com.repose.practical.mvvm.ui.main.viewmodel

import androidx.lifecycle.*
import com.repose.practical.mvvm.data.model.NewsResponse
import com.repose.practical.mvvm.data.repository.MainRepository
import com.repose.practical.mvvm.sessionManager
import com.repose.practical.mvvm.utils.NetworkHelper
import com.repose.practical.mvvm.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _users = MutableLiveData<Resource<NewsResponse>>()
    val users: LiveData<Resource<NewsResponse>>
        get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getUsers().let {
                    if (it.isSuccessful) {
                        _users.postValue(Resource.success(it.body()))
                    } else _users.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _users.postValue(Resource.success(sessionManager.getData()))
        }
    }
}