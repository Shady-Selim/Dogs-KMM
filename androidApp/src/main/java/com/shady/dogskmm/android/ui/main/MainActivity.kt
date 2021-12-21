package com.shady.dogskmm.android.ui.main

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.shady.dogskmm.android.ui.theme.DogsComposeTheme

class MainActivity : AppCompatActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            DogsComposeTheme{
                MyApp()
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun MyApp(vm: MainViewModel = viewModel()) {
    val breeds by vm.getBreeds().observeAsState()
    var expanded by remember { mutableStateOf(false) }
    var dropMenuText by remember { mutableStateOf("Select Dog Breed") }
    var breedId by remember { mutableStateOf(-1) }
    val context = LocalContext.current
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Select a dog breed", style = typography.h6)
        Button(onClick = { expanded = !expanded }, modifier = Modifier
            .fillMaxWidth()
            .align(alignment = Alignment.Start),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)){
            Text (dropMenuText)
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            breeds?.forEach { label ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    dropMenuText = label.name
                    breedId = label.id
                }) {
                    Text(text = label.name)
                }
            }
        }
        loadDogsGrid(vm, breedId)
    }

}

@ExperimentalFoundationApi
@Composable
fun loadDogsGrid(vm: MainViewModel, breedId: Int) {
    val dogs by vm.getDogs(breedId).observeAsState()
    LazyVerticalGrid(cells = GridCells.Fixed(3)){
        dogs?.let {
            items(it){ index ->
                Image(painter = rememberImagePainter(data = index.url),
                    contentDescription = index.url,
                    modifier = Modifier.height(100.dp),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}
