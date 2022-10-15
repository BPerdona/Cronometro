package br.com.stopwatch.ui.theme.screen

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.stopwatch.service.ServiceHelper
import br.com.stopwatch.service.StopwatchService
import br.com.stopwatch.service.StopwatchState
import br.com.stopwatch.ui.theme.*
import br.com.stopwatch.util.Constants.ACTION_SERVICE_CANCEL
import br.com.stopwatch.util.Constants.ACTION_SERVICE_START
import br.com.stopwatch.util.Constants.ACTION_SERVICE_STOP

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    stopwatchService: StopwatchService
){
    val context = LocalContext.current
    val hours by stopwatchService.hours
    val minutes by stopwatchService.minutes
    val seconds by stopwatchService.seconds
    val currentState by stopwatchService.currentState

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
                    ServiceHelper.triggerForegroundService(
                        context=context,
                        action = if(currentState == StopwatchState.Started) ACTION_SERVICE_STOP
                        else ACTION_SERVICE_START
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if(currentState == StopwatchState.Started) Red else Blue,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = when (currentState) {
                        StopwatchState.Started -> "Parar"
                        StopwatchState.Stopped -> "Continuar"
                        else -> "Iniciar"
                    }
                )
            }
            Spacer(modifier = Modifier.width(30.dp))
            Button(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(0.8f),
                onClick = {
                    ServiceHelper.triggerForegroundService(
                        context = context, action = ACTION_SERVICE_CANCEL
                    )
                },
                enabled = currentState != StopwatchState.Idle,
                colors = ButtonDefaults.buttonColors(
                    disabledBackgroundColor = Light,
                    contentColor = Color.White,
                    backgroundColor = Red
                )
            ) {
                Text(text = "Zerar")
            }
        }
    }
}

@ExperimentalAnimationApi
fun addAnimation(duration: Int = 500): ContentTransform{
    return slideInVertically(animationSpec = tween(durationMillis = duration)){height -> height} + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutVertically(animationSpec = tween(durationMillis = duration)){height -> height} + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}