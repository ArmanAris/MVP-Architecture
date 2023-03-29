package com.aris.mvp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.lifecycleScope
import com.aris.mvp.model.Comments
import com.aris.mvp.model.Post
import com.aris.mvp.presenter.contract.*
import com.aris.mvp.presenter.presnet.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

lateinit var posts: List<Post>

class MainActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            GlobalScope.launch {
                val time = measureTimeMillis {
                    myTestFlow()
                        .buffer()
                        .collect {
                            delay(300)
                            Log.e("7171", it.toString())
                        }
                }
                Log.e("7171", time.toString()) //3136
            }


/*            var postList by remember { mutableStateOf(emptyList<Post>()) }

            lifecycleScope.launchWhenCreated { postList = getAllPostRequest() }

            Column() { PostView(postList = postList) }*/


        }


    }


    private fun myTestFlow(): Flow<Int> = flow {
        for (i in 1..10) {
            delay(100)
            emit(i)
        }
    }


    @Composable
    fun PostView(postList: List<Post>) {
        LazyColumn() {
            items(postList) { post ->
                Text(text = post.title)
            }
        }
    }

    suspend fun getAllPostRequest(): List<Post> {
        val presenter = PostPresent(object : IPost.View {
            override fun onSuccess(postList: List<Post>) {
                posts = postList
            }

            override fun onError(massage: String) {
                Log.e("7171", massage)
            }

            override fun onFail(massage: String) {
                Log.e("7171", massage)
            }
        })
        presenter.getAllPostRequest()
        return posts
    }

    suspend fun getPostRequest(postId: String) {
        val presenter = SinglePostPresent(object : ISinglePost.View {
            override fun onSuccess(post: Post) {
                Log.e("7171", post.title)
            }

            override fun onError(massage: String) {
                Log.e("7171", massage)
            }

            override fun onFail(massage: String) {
                Log.e("7171", massage)
            }
        })
        presenter.getPostRequest(postId)
    }

    suspend fun getAllPostComment(postId: Int) {
        val presenter = CommentPresent(object : IComments.View {
            override fun onSuccess(commentList: List<Comments>) {
                commentList.forEach { comment ->
                    Log.e("7171", comment.id.toString())
                }
            }

            override fun onError(massage: String) {
                Log.e("7171", massage)
            }

            override fun onFail(massage: String) {
                Log.e("7171", massage)
            }
        })
        presenter.getAllPostComments(postId)
    }

    suspend fun crateNewPost(post: Post) {
        val presenter = NewPostPresent(object : INewPost.View {
            override fun onSuccess(massage: String) {
                Log.e("7171", massage)
            }

            override fun onError(massage: String) {
                Log.e("7171", massage)
            }

            override fun onFail(massage: String) {
                Log.e("7171", massage)
            }
        })
        presenter.createNewPost(post)
    }

    fun login(userName: String, password: String) {
        val presenter = LoginPresenter(object : ILogin.View {
            override fun onSuccess(massage: String) {
                Log.e("7171", massage)
            }

            override fun onFail(massage: String) {
                Log.e("7171", massage)
            }
        })
        presenter.login(userName, password)
    }
}