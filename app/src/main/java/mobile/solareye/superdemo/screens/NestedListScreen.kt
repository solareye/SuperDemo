package mobile.solareye.superdemo.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mobile.solareye.superdemo.data.KindsOfActivityChildrenModel
import mobile.solareye.superdemo.data.KindsOfActivityModel
import mobile.solareye.superdemo.data.MockDataSource
import mobile.solareye.superdemo.ui.theme.SuperDemoTheme

@ExperimentalAnimationApi
object NestedListScreen {

    @Composable
    fun NestedListScreen() {
        Scaffold {
            val list = MockDataSource.kindsOfActivity
            val scrollState = rememberLazyListState()

            var selectedCount by rememberSaveable { mutableStateOf(0) }

            Column {
                KindsOfActivity(scrollState, list, Modifier.weight(1f)) { isChecked ->
                    selectedCount += if (isChecked) 1 else -1
                }
                AcceptButton(selectedCount)
            }

        }
    }

    @Composable
    private fun AcceptButton(selectedCount: Int) {
        Button(
            onClick = {},
            enabled = selectedCount > 0,
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                when (selectedCount) {
                    0, 1 -> {
                        "Готово"
                    }
                    else -> {
                        "Выбрано $selectedCount"
                    }
                }
            )
        }
    }

    @Composable
    private fun KindsOfActivity(
        scrollState: LazyListState,
        list: List<KindsOfActivityModel>,
        modifier: Modifier,
        onChildChecked: (Boolean) -> Unit
    ) {
        LazyColumn(state = scrollState, modifier = modifier) {
            for (index in list.indices) {
                val parentModel = list[index]
                item {
                    ParentSection(parentModel, onChildChecked)
                }
            }
        }
    }

    @Composable
    private fun ParentSection(
        parentModel: KindsOfActivityModel,
        onChildChecked: (Boolean) -> Unit
    ) {
        var isExpanded by rememberSaveable { mutableStateOf(true) }
        Column {
            Parent(parentModel, isExpanded) {
                isExpanded = it
            }
            val children = parentModel.children
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(),
                exit = shrinkVertically(),
            ) {
                Column {
                    println("Huston")
                    for (index in children.indices) {
                        Child(children[index], onChildChecked)
                    }
                }
            }

        }
    }

    @Composable
    private fun Parent(
        parentModel: KindsOfActivityModel,
        isExpanded: Boolean,
        onClicked: (Boolean) -> Unit
    ) {
        Row(
            Modifier
                .height(56.dp)
                .clickable {
                    onClicked(!isExpanded)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                text = parentModel.parent.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                lineHeight = 24.sp,
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Image(
                imageVector = if (isExpanded) {
                    Icons.Default.ArrowDropUp
                } else {
                    Icons.Default.ArrowDropDown
                },
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .wrapContentWidth(),
            )
            Spacer(modifier = Modifier.size(32.dp))
        }
    }

    @Composable
    private fun Child(
        child: KindsOfActivityChildrenModel,
        onChildChecked: (Boolean) -> Unit
    ) {
        var isChecked by rememberSaveable { mutableStateOf(child.isSelected) }
        Row(
            Modifier
                .height(56.dp)
                .clickable {
                    isChecked = !isChecked
                    child.isSelected = isChecked
                    onChildChecked(isChecked)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                text = child.name,
                fontSize = 20.sp,
                lineHeight = 24.sp,
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(modifier = Modifier.size(16.dp))
            AnimatedVisibility(
                visible = isChecked,
                enter = fadeIn(
                    // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                    initialAlpha = 0.4f
                ),
                exit = fadeOut(
                    // Overwrites the default animation with tween
                    animationSpec = tween(durationMillis = 250)
                )
            ) {
                Image(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .wrapContentWidth(),
                )
            }
            Spacer(modifier = Modifier.size(32.dp))
        }
    }

}

@ExperimentalAnimationApi
@Preview(showSystemUi = true)
@Composable
fun NestedListScreenPreview() {
    SuperDemoTheme {
        NestedListScreen.NestedListScreen()
    }
}