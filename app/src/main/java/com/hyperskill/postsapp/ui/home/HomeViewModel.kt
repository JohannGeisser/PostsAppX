package com.hyperskill.postsapp.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyperskill.postsapp.domain.item.PostItem
import com.hyperskill.postsapp.domain.item.UserItem
import com.hyperskill.postsapp.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val getPostByIdUseCase: GetPostByIdUseCase,
    private val getUsersUseCase: GetUsersUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val setFavoritePostUseCase: SetFavoritePostUseCase,
    private val deletePostByIdUseCase: DeletePostByIdUseCase,
    private val deleteAllPostsUseCase: DeleteAllPostsUseCase
) : ViewModel() {

//    private val _posts = MutableStateFlow(emptyList<PostItem>())
//    val posts: StateFlow<List<PostItem>> get() = _posts
    val posts: MutableState<List<PostItem>> =  mutableStateOf(listOf())

    //Check difference between mutable live data and mutable state flow
    private val _post = MutableLiveData<PostItem>()
    val post: LiveData<PostItem> get() = _post



    private val _users = MutableStateFlow(emptyList<UserItem>())
    val users: StateFlow<List<UserItem>> get() = _users
    private val _user = MutableLiveData<UserItem>()
    val user: LiveData<UserItem> get() = _user


    init {
        getPosts()
        getUsers()
    }

    fun getPosts() {
        viewModelScope.launch {
            try {
                //val posts = getPostsUseCase()
                //_posts.value = posts
                val postsResult = getPostsUseCase()
                posts.value = postsResult
            } catch (_: Exception) {

            }
        }
    }

    fun getPostById(id: Int) {
        viewModelScope.launch {
            try {
                val post = getPostByIdUseCase(id)
                _post.value = post
            } catch (_: Exception) {

            }
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            try {
                val users = getUsersUseCase()
                _users.value = users
            } catch (_: Exception) {

            }
        }
    }

    fun getUserById(id: Int) {
        viewModelScope.launch {
            try {
                val user = getUserByIdUseCase(id)
                _user.value = user
            } catch (_: Exception) {

            }
        }
    }

    fun setFavorite(id: Int, favoriteState: Boolean) {
        viewModelScope.launch {
            try {
                setFavoritePostUseCase(id, favoriteState)
                getPosts()
            } catch (_: Exception) {

            }
        }
    }

    fun deletePostById(id: Int) {
        viewModelScope.launch {
            try {
                deletePostByIdUseCase(id)
                getPosts()
            } catch (_: Exception) {

            }
        }
    }

    fun deleteAllPosts() {
        viewModelScope.launch {
            try {
                deleteAllPostsUseCase()
                posts.value = emptyList<PostItem>()
            } catch (_: Exception) {

            }
        }
    }
}