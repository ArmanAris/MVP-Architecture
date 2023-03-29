package com.aris.mvp.network

import com.aris.mvp.model.Comments
import com.aris.mvp.model.Post
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    // GET: /Posts  All
    @GET("/posts")
    suspend fun getAllPosts(): Response<List<Post>>

    // GET: /Posts/1  one
    @GET("/posts/{postId}")
    suspend fun getPostById(
        @Path(value = "postId",
            encoded = true) postId: String,
    ): Response<Post>

    // GET: /comments?postId=1   Group comment
    @GET("/comments")
    suspend fun getAllPostComments(
        @Query("postId") postId: Int,
    ): Response<List<Comments>>

    // GET: /comments?postId=1&body=abc   Group comment
    @GET("/comments")
    suspend fun getAllPostCommentsTowFilters(
        @Query("postId") postId: Int,
        @Query("body") body: String,
    ): Response<List<Comments>>


    // POST: /posts   one
    @POST("/posts")
    suspend fun createNewPost(
        @Body newPost: Post,
    ): Response<Post>

}