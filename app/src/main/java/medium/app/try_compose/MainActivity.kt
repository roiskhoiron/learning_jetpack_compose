package medium.app.try_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import medium.app.try_compose.ui.theme.Try_composeTheme

class MainActivity : ComponentActivity() {
    val listData = List(1000) { "Rois $it" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                ShowList(
                    name = listData,
                    counterState = remember {
                        mutableStateOf(0)
                    })
            }
        }
    }
}

@Preview(name = "ListView")
@Composable
fun Preview() {
    MyApp {
        ShowList(
            name = List(1000) { "Rois $it" },
            counterState = remember {
                mutableStateOf(0)
            })
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    Try_composeTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxHeight()) {
            Box(modifier = Modifier.padding(all = 25.dp)) {
                content();
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isTapped by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(targetValue = if (isTapped) Color.Green else Color.Transparent)


    Text(
        text = "Hello $name!",
        modifier = Modifier.padding(24.dp)
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable(onClick = {isTapped = !isTapped})
    )
}

@Composable
fun ShowList(name: List<String>, counterState: MutableState<Int>) {
    Column {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items = name) { name ->
                Greeting(name = name)
                Divider(color = Color.Gray)
            }
        }
        Divider(color = Color.Transparent, thickness = 32.dp)
        Counter(
            count = counterState.value,
            updateCount = { newCount ->
                counterState.value = newCount
            }
        )
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.White
        )
    ) {
        Text(text = "I've been cliked $count times")
    }
}