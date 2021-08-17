package medium.app.try_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import medium.app.try_compose.ui.theme.Try_composeTheme

class MainActivity : ComponentActivity() {
    val listData = listOf("Rois", "Khoironi")

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
            name = listOf("Rois"),
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
    Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
}

@Composable
fun ShowList(name: List<String>, counterState: MutableState<Int>) {
    Column {
        Column(modifier = Modifier.weight(1f)) {
            for (names in name) {
                Greeting(name = names)
                Divider(color = Color.Gray)
            }
        }
        Divider(color = Color.Transparent, thickness = 32.dp)
        Box(modifier = Modifier.weight(2f)) {
            Counter(
                count = counterState.value,
                updateCount = { newCount ->
                    counterState.value = newCount
                }
            )
        }
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(onClick = { updateCount(count + 1) }) {
        Text(text = "I've been cliked $count times")
    }
}