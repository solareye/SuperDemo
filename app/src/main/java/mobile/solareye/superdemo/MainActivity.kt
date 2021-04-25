package mobile.solareye.superdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import mobile.solareye.superdemo.screens.NestedListScreen.NestedListScreen
import mobile.solareye.superdemo.ui.theme.SuperDemoTheme

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NestedListScreen()
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    SuperDemoTheme {
        NestedListScreen()
    }
}