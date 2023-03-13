package com.hyperskill.postsapp.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.hyperskill.postsapp.R
import com.hyperskill.postsapp.domain.item.PostItem
import com.hyperskill.postsapp.ui.nav.Screens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(homeViewModel: HomeViewModel, navController: NavController) {

    val posts = homeViewModel.posts.value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    val showDialog = remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize()) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                homeViewModel.getPosts()
            }
        ) {
            Scaffold(
                topBar = { HomeTopAppBar(onAddPostClick = { showDialog.value = true }) },
                content = { HomeContent(posts, navController, homeViewModel) },
                bottomBar = { HomeBottomBar(homeViewModel) }
            )
        }
        if (showDialog.value) {
            AddPostDialog(
                onDismiss = { showDialog.value = false }
            ) { post ->
                //homeViewModel.addPost(post)
                showDialog.value = false
            }
        }
    }
}

@Composable
fun PostCard(post: PostItem, navController: NavController) {

    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(
                top = 5.dp,
                bottom = 5.dp,
                start = 10.dp,
                end = 10.dp
            )
            .fillMaxSize()
            .clickable {
                navController.navigate(Screens.Detail.route + "/${post.id}")
            }
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {

            Text(text = post.title, fontWeight = FontWeight.Bold)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(text = post.body, overflow = TextOverflow.Ellipsis)
                if (post.isFavorite) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorite",
                        tint = Color.Red,
                        modifier = Modifier.align(Alignment.BottomEnd)
                    )
                }
            }
        }
    }
}

@Composable
fun DeleteConfirmationDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Delete All Posts") },
        text = { Text(text = "Are you sure you want to delete all posts?") },
        confirmButton = {
            Button(onClick = { onConfirm() }) {
                Text("Yes")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun HomeTopAppBar(onAddPostClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.home_title),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        actions = {
            IconButton(onClick = {onAddPostClick()}) {
                Icon(Icons.Filled.Add, contentDescription = "Add Post")
            }
        }
    )
}

@Composable
fun HomeContent(
    posts: List<PostItem>,
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    if (posts.isNotEmpty()) {
        PostList(posts = posts, navController = navController)
    } else {
        EmptyState(homeViewModel = homeViewModel)
    }
}

@Composable
fun PostList(
    posts: List<PostItem>,
    navController: NavController
) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(posts) { post ->
            PostCard(post = post, navController = navController)
        }
    }
}

@Composable
fun EmptyState(homeViewModel: HomeViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .clickable { homeViewModel.getPosts() }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "No available Posts, Click to fetch Posts"
            )
        }
    }
}

@Composable
fun HomeBottomBar(homeViewModel: HomeViewModel) {
    var showDeleteConfirm by remember { mutableStateOf(false) }

    Button(
        onClick = { showDeleteConfirm = true },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(16.dp)
    ) {
        Text(
            text = "Delete All Posts",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }

    if (showDeleteConfirm) {
        DeleteConfirmationDialog(
            onDismiss = { showDeleteConfirm = false },
            onConfirm = {
                showDeleteConfirm = false
                homeViewModel.deleteAllPosts()
            }
        )
    }
}

@Composable
fun AddPostDialog(
    onDismiss: () -> Unit,
    onConfirm: (title: String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Add New Post") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = body,
                    onValueChange = { body = it },
                    label = { Text("Body") },
                    modifier = Modifier.heightIn(min = 120.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(title)
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}

