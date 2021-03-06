package mobile.solareye.superdemo.ui.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import mobile.solareye.superdemo.data.MockDataSource
import kotlin.math.max

@Composable
fun SimpleFlowRow(
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal = Alignment.Start,
    verticalGap: Dp = 0.dp,
    horizontalGap: Dp = 0.dp,
    content: @Composable () -> Unit
) = Layout(content, modifier) { measurables, constraints ->
    val hGapPx = horizontalGap.roundToPx()
    val vGapPx = verticalGap.roundToPx()

    val rows = mutableListOf<MeasuredRow>()
    val itemConstraints = constraints.copy(minWidth = 0)

    for (measurable in measurables) {
        val lastRow = rows.lastOrNull()
        val placeable = measurable.measure(itemConstraints)

        if (lastRow != null && lastRow.width + hGapPx + placeable.width <= constraints.maxWidth) {
            lastRow.items.add(placeable)
            lastRow.width += hGapPx + placeable.width
            lastRow.height = max(lastRow.height, placeable.height)
        } else {
            val nextRow = MeasuredRow(
                items = mutableListOf(placeable),
                width = placeable.width,
                height = placeable.height
            )

            rows.add(nextRow)
        }
    }

    val width = rows.maxOfOrNull { row -> row.width } ?: 0
    val height = rows.sumBy { row -> row.height } + max(vGapPx.times(rows.size - 1), 0)

    val coercedWidth = width.coerceIn(constraints.minWidth, constraints.maxWidth)
    val coercedHeight = height.coerceIn(constraints.minHeight, constraints.maxHeight)

    layout(coercedWidth, coercedHeight) {
        var y = 0

        for (row in rows) {
            var x = when(alignment) {
                Alignment.Start -> 0
                Alignment.CenterHorizontally -> (coercedWidth - row.width) / 2
                Alignment.End -> coercedWidth - row.width

                else -> throw Exception("unsupported alignment")
            }

            for (item in row.items) {
                item.place(x, y)
                x += item.width + hGapPx
            }

            y += row.height + vGapPx
        }
    }
}

private data class MeasuredRow(
    val items: MutableList<Placeable>,
    var width: Int,
    var height: Int
)

@Preview(
    widthDp = 16,
    heightDp = 16,
    showBackground = true,
    backgroundColor = 0xffffff
)
@Composable
fun PreviewEmptySimpleFlowRow() {
    SimpleFlowRow {}
}

@Preview(
    widthDp = 200,
    showBackground = true,
    backgroundColor = 0xffffff
)
@Composable
fun PreviewCenteredSimpleFlowRow(
    @PreviewParameter(AlignmentProvider::class) alignment: Alignment.Horizontal
) {
    SimpleFlowRow(
        alignment = alignment,
        horizontalGap = 16.dp,
        verticalGap = 8.dp
    ) {
        for (tag in MockDataSource.sampleTags) {
            Text(
                text = "#$tag",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

class AlignmentProvider : PreviewParameterProvider<Alignment.Horizontal> {
    override val values: Sequence<Alignment.Horizontal> = sequenceOf(
        Alignment.Start,
        Alignment.CenterHorizontally,
        Alignment.End
    )
}