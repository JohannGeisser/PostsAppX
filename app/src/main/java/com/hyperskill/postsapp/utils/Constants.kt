package com.hyperskill.postsapp.utils

class Constants {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val POSTS_ENDPOINT = "posts"
        const val POST_ID_ENDPOINT = "posts/{id}"
        const val POST_DATABASE_NAME = "post_database"
        const val KEY_POST_ID = "com.hyperskill.postsapp.id"

        const val USERS_ENDPOINT = "users"
        const val USER_ID_ENDPOINT = "users/{id}"

        const val COMMENTS_ENDPOINT = "posts/{id}/comments"
    }

    object Screens {
        const val HOME_SCREEN = "home_screen"
        const val DETAIL_SCREEN = "detail_screen"
    }
}