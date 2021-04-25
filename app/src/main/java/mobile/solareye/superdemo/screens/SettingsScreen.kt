package mobile.solareye.superdemo.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mobile.solareye.superdemo.R
import mobile.solareye.superdemo.data.Lexems
import mobile.solareye.superdemo.data.MockDataSource
import mobile.solareye.superdemo.data.Operation
import mobile.solareye.superdemo.data.OperationType
import mobile.solareye.superdemo.ui.theme.SuperDemoTheme
import mobile.solareye.superdemo.ui.view.SimpleFlowRow

object SettingsScreen {

    private val sberColor = Color(0xFF08A652)

    @Composable
    fun SettingsScreen() {
        Scaffold {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                Header()
                Accept()
                Section(Lexems.profileTitle, Lexems.profileDescription) {
                    Row(Modifier.horizontalScroll(rememberScrollState())) {
                        MockDataSource.operations.forEach { operation ->
                            OperationCard(operation)
                            Spacer(modifier = Modifier.size(8.dp))
                        }
                    }
                }
                Section(Lexems.profileTitle, Lexems.profileDescription) {
                    Column {
                        Row {
                            Image(
                                imageVector = Icons.Default.ShareLocation,
                                contentDescription = null,
                                modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Row {
                                Column {
                                    Text(text = Lexems.limitTitle)
                                    Text(text = Lexems.limitDescription)
                                }
                                Image(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = null,
                                    modifier = Modifier.size(36.dp)
                                )
                                Box(modifier = Modifier.height(height = 1.dp))
                            }
                        }
                    }
                }
                Section(Lexems.interestsTitle, Lexems.interestsDescription) {
                    SimpleFlowRow {
                        MockDataSource.sampleTags.forEach {

                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = Color.Black.copy(alpha = .08f)
                            ) {
                                Text(
                                    text = it,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp)
                                )
                            }

                            Spacer(modifier = Modifier.size(8.dp))
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun OperationCard(operation: Operation) {
        Card(
            modifier = Modifier.defaultMinSize(216.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        imageVector = when (operation.type) {
                            OperationType.TRANSACTION -> Icons.Default.AlarmOn
                            OperationType.PRIME -> Icons.Default.AlarmOff
                        },
                        contentDescription = null,
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(text = operation.title)
                }
                Text(text = operation.subtitle)
                Text(text = operation.description)
            }

        }
    }

    @Composable
    private fun Section(title: String, subtitle: String, content: @Composable () -> Unit) {
        Column {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                lineHeight = 24.sp
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = subtitle,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                color = Color.Black.copy(alpha = .45f)
            )
            Spacer(modifier = Modifier.size(20.dp))
            content()
            Spacer(modifier = Modifier.size(40.dp))
        }
    }

    @Composable
    private fun Header() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.shadow(elevation = 2.dp)
        ) {
            Toolbar()
            UserName()
            Spacer(modifier = Modifier.height(14.dp))
            Tabs()

        }
    }

    @Composable
    private fun Tabs() {
        var selectedId by remember { mutableStateOf(1) }

        TabRow(
            selectedTabIndex = selectedId,
            contentColor = Color.Black,
            backgroundColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[0]),
                    color = sberColor
                )
            },
            modifier = Modifier.shadow(elevation = 2.dp),
            tabs = {
                Tab(selected = selectedId == 0, onClick = {
                    selectedId = 0
                    Log.i("tag", " tab $selectedId is clicked ")
                }) {
                    Text(
                        text = Lexems.profile_tab,
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(top = 17.dp, bottom = 19.dp)
                    )
                }
                Tab(selected = selectedId == 1, onClick = {
                    selectedId = 1
                    Log.i("tag", " tab $selectedId is clicked ")
                }) {
                    Text(
                        text = Lexems.settings_tab,
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(top = 17.dp, bottom = 19.dp)
                    )
                }
            }
        )
//    {
//        Tab(selected = selectedId == 0, onClick = {
//            selectedId = 0
//            Log.i("tag", " tab $selectedId is clicked ")
//        }) {
//            Text(
//                text = Lexems.profile_tab,
//                fontSize = 16.sp,
//                lineHeight = 20.sp,
//                modifier = Modifier.padding(top = 17.dp, bottom = 19.dp)
//            )
//        }
//        Tab(selected = selectedId == 1, onClick = {
//            selectedId = 1
//            Log.i("tag", " tab $selectedId is clicked ")
//        }) {
//            Text(
//                text = Lexems.settings_tab,
//                fontSize = 16.sp,
//                lineHeight = 20.sp,
//                modifier = Modifier.padding(top = 17.dp, bottom = 19.dp)
//            )
//        }
//    }
    }

    @Composable
    private fun UserName() {
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = Lexems.profileNameMe,
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                lineHeight = 32.sp,
                fontSize = 24.sp
            )
        )
    }

    @Composable
    private fun Toolbar() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ToolbarIcon(Icons.Default.Close)
            Avatar()
            ToolbarIcon(Icons.Default.Logout)
        }
    }

    @Composable
    private fun Avatar() {
        val avatarShape = RoundedCornerShape(38.dp)
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.roberto),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 12.dp)
                .shadow(elevation = 4.dp, shape = avatarShape)
                .clip(avatarShape)
                .size(110.dp)
        )
    }

    @Composable
    private fun ToolbarIcon(icon: ImageVector) {
        IconButton(onClick = { }) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = sberColor
            )
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Composable
    fun Accept() {
        var checkState by remember { mutableStateOf(false) }
        Checkbox(checked = checkState, onCheckedChange = { isChecked ->
            checkState = isChecked
        })
    }

}

@Preview(showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    SuperDemoTheme {
        SettingsScreen.SettingsScreen()
    }
}