package br.com.stopwatch.ui.theme.screen

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.stopwatch.ui.theme.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(){
    // Todo -> Aplicar modificações para receber os parametros do Service
    val context = LocalContext.current
    val hours = "00"
    val minutes = "00"
    val seconds = "00"
    val currentState = false

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(9f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedContent(targetState = hours, transitionSpec = { addAnimation() }) {
                Text(
                    text = hours,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.h1.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = if(hours=="00") Color.White else Blue
                    )
                )
            }
            AnimatedContent(targetState = minutes, transitionSpec = { addAnimation() }) {
                Text(
                    text = minutes,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.h1.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = if(minutes=="00") Color.White else Blue
                    )
                )
            }
            AnimatedContent(targetState = seconds, transitionSpec = { addAnimation() }) {
                Text(
                    text = seconds,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.h1.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = if(seconds=="00") Color.White else Blue
                    )
                )
            }
        }
        Row(modifier = Modifier.weight(1f)){
            Button(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(0.8f),
                onClick = {
                    //Todo -> Alterar Service
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if(true) Red else Blue, //Todo -> Alterar com base no Service
                    contentColor = Color.White
                )
            ) {
                Text(text = "Iniciar")//Todo -> Alterar com base nos valores do service
            }
            Spacer(modifier = Modifier.width(30.dp))
            Button(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(0.8f),
                onClick = {
                    //Todo -> Alterar Service: Cancelar
                },
                enabled = false,
                colors = ButtonDefaults.buttonColors(disabledBackgroundColor = Light)
            ) {
                Text(text = "Cancelar")
            }
        }
    }
}

@ExperimentalAnimationApi
fun addAnimation(duration: Int = 600): ContentTransform{
    return slideInVertically(animationSpec = tween(durationMillis = duration)){height -> height} + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutVertically(animationSpec = tween(durationMillis = duration)){height -> height} + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}