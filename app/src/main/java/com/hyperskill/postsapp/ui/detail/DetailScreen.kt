package com.hyperskill.postsapp.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.hyperskill.postsapp.ui.home.HomeViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hyperskill.postsapp.R
import com.hyperskill.postsapp.domain.item.PostItem
import kotlinx.coroutines.delay

@Composable
fun DetailScreen(id: String, homeViewModel: HomeViewModel, navController: NavController) {

    homeViewModel.getPostById(id.toInt())
    val post = homeViewModel.post.observeAsState().value

    post?.userId?.let { homeViewModel.getUserById(it) }
    val user = homeViewModel.user.observeAsState().value





    LazyColumn {
        item {
            Column {
                // var isFavorite by remember { mutableStateOf(post?.isFavorite ?: false) }
                //var postsxD = homeViewModel.posts.value
//                for (item in postsxD) {
//                    println(item)
//                }

                val foundPost = homeViewModel.posts?.value?.filter { it.id == id.toInt() }
                var isFavorite by remember { mutableStateOf(foundPost?.first()?.isFavorite ?: false) }

                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                        }
                    },
                    title = {
                        Text(
                            text = post?.title ?: stringResource(R.string.detail_title),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    actions = {
                        Row(modifier = Modifier.padding(end = 12.dp)) {
                            IconButton(
                                onClick = {
                                    isFavorite = !isFavorite
                                    homeViewModel.setFavorite(id.toInt(), isFavorite)
                                }
                            ) {
                                Icon(
                                    imageVector = if (isFavorite) {
                                        Icons.Filled.Favorite
                                    } else {
                                        Icons.Default.FavoriteBorder
                                    },
                                    tint = if (isFavorite) {
                                        colorResource(R.color.favorite)
                                    } else {
                                        LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                                    },
                                    contentDescription = stringResource(R.string.favorite_button_description)
                                )
                            }
                        }
                    }

                )
                Text(
                    text = stringResource(id = R.string.description_title),
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(text = post?.body ?: "", modifier = Modifier.padding(10.dp))
                Text(
                    text = stringResource(id = R.string.user_title),
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = "${stringResource(id = R.string.name)} ${user?.name ?: ""}",
                    modifier = Modifier.padding(
                        start = 10.dp,
                        top = 10.dp,
                        end = 10.dp,
                        bottom = 5.dp
                    )
                )
                Text(
                    text = "${stringResource(id = R.string.email)} ${user?.email ?: ""}",
                    modifier = Modifier.padding(
                        start = 10.dp,
                        top = 5.dp,
                        end = 10.dp,
                        bottom = 5.dp
                    )
                )
                Text(
                    text = "${stringResource(id = R.string.phone)} ${user?.phone ?: ""}",
                    modifier = Modifier.padding(
                        start = 10.dp,
                        top = 5.dp,
                        end = 10.dp,
                        bottom = 5.dp
                    )
                )
                Text(
                    text = "${stringResource(id = R.string.website)} ${user?.website ?: ""}",
                    modifier = Modifier.padding(
                        start = 10.dp,
                        top = 5.dp,
                        end = 10.dp,
                        bottom = 10.dp
                    )
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = {
                            homeViewModel.deletePostById(id.toInt())
                            navController.popBackStack()
                            },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(R.string.delete_button_description),
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun FavoriteButton(
        modifier: Modifier = Modifier,
        color: Color = Color(0xffE91E63)
    ) {
        var isFavorite by remember { mutableStateOf(false) }

        IconToggleButton(
            checked = isFavorite,
            onCheckedChange = {
                isFavorite = !isFavorite
            }
        ) {
            Icon(
                tint = color,
                modifier = modifier.graphicsLayer {
                    scaleX = 1.3f
                    scaleY = 1.3f
                },
                imageVector = if (isFavorite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                contentDescription = null
            )
        }
    }
}