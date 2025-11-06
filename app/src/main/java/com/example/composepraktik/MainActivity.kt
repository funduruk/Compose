package com.example.composepraktik

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.view.GestureDetector
import android.view.GestureDetector.OnGestureListener
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composepraktik.dataBase.Entity.User


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicUI()
        }
            }
}


@Composable
fun LinearProgressBar(){
    var progress by remember { mutableStateOf(0.0f) }
    Spacer(modifier = Modifier.height(16.dp))

    Slider(
        value = progress,
        onValueChange = {progress = it},
        modifier = Modifier.fillMaxWidth()
            .padding(5.dp)
            .pointerInput(Unit){
                detectDragGestures(
                    onDrag = { change ,dragAmount->
                        change.consume()
                        if(dragAmount.x > 0){
                            progress = (progress + 0.1f).coerceAtMost(1f)
                        } else if (dragAmount.x < 0){
                            progress = (progress + 0.1f).coerceAtLeast(0.0f)
                        }
                    }
                )
            }
    )
}
@Composable
fun BackgroundSample(){
    val colorList = listOf(Color.Black, Color.White, Color.Red, Color.Blue)
    var colorId by remember { mutableStateOf(0)}
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorList.get(colorId))
            .pointerInput(Unit){
                detectTapGestures (
                    onDoubleTap = { offset ->
                        if(colorId < 3)
                            colorId++
                        else
                            colorId = 0

                    }
                )
            }
    ){

    }
}

@Composable
fun ChangeSizeSelf(){
    var boxSize by remember { mutableStateOf(100.dp) }
    var boxRotation by remember { mutableStateOf(0f) }
    Box(

        modifier = Modifier
            .size(boxSize)
            .rotate(boxRotation)
            .background(Color.White)
            .pointerInput(Unit){
                detectTransformGestures(
                    onGesture = {centroid, pan, zoom, rotation ->
                        val newSize = boxSize * zoom
                        boxSize = newSize
                        boxSize = boxSize.coerceIn(50.dp, 300.dp)
                        val newRotate = boxRotation + rotation
                        boxRotation = newRotate
                    }
                )
            }

    )
}


@Preview
@Composable
fun BasicUI() {
    val colorList = listOf(Color.Black, Color.White, Color.Red, Color.Blue)
    var colorId by remember { mutableStateOf(0)}
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorList.get(colorId))
            .pointerInput(Unit){
                detectTapGestures (
                    onDoubleTap = { offset ->
                        if(colorId < 3)
                            colorId++
                        else
                            colorId = 0

                    },
                    onLongPress = {offset ->
                        if(colorId > 0)
                            colorId--
                        else
                            colorId = 3
                    }

                )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgressBar()
            Spacer(modifier = Modifier.height(32.dp))
            ChangeSizeSelf()
        }
    }
}
