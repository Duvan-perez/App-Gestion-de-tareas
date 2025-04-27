package com.example.proyectopoli.screens.fragments.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import kotlinx.coroutines.launch
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.google.accompanist.pager.*
import com.example.proyectopoli.R

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun VideosFragment(navController: NavController) {
    val context = LocalContext.current
    val videoUris = listOf(
        "android.resource://${context.packageName}/${R.raw.video1}",
        "android.resource://${context.packageName}/${R.raw.video2}",
        "android.resource://${context.packageName}/${R.raw.video3}"
    )
    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFE6F4F1))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Text(
                text = "Galería de Videos",
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 35.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(videoUris.indices.toList()) { index ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clickable { selectedIndex = index }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayCircleFilled,
                            contentDescription = "Video $index",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(64.dp)
                        )
                    }
                }
            }
        }
    }

    selectedIndex?.let { startIndex ->
        Dialog(onDismissRequest = { selectedIndex = null }) {
            val pagerState = rememberPagerState(
                initialPage = startIndex,
                pageCount = { videoUris.size }
            )
            val coroutineScope = rememberCoroutineScope()
            var playing by remember { mutableStateOf(true) }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                ) { page ->
                    val exoPlayer = remember {
                        ExoPlayer.Builder(context).build().apply {
                            setMediaItem(MediaItem.fromUri(Uri.parse(videoUris[page])))
                            prepare()
                            playWhenReady = true
                        }
                    }

                    DisposableEffect(key1 = exoPlayer) {
                        onDispose {
                            exoPlayer.release()
                        }
                    }

                    Card(
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        AndroidView(
                            factory = {
                                PlayerView(it).apply {
                                    player = exoPlayer
                                    useController = false
                                }
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                (pagerState.currentPage - 1 + videoUris.size) % videoUris.size
                            )
                        }
                    }) {
                        Icon(Icons.Default.SkipPrevious, contentDescription = "Anterior")
                    }
                    IconButton(onClick = {
                        playing = !playing
                    }) {
                        Icon(
                            imageVector = if (playing) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = "Pausar/Reproducir"
                        )
                    }
                    IconButton(onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                (pagerState.currentPage + 1) % videoUris.size
                            )
                        }
                    }) {
                        Icon(Icons.Default.SkipNext, contentDescription = "Siguiente")
                    }
                }
            }
        }
    }
}
