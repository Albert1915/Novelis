package com.alz19.novelis.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alz19.novelis.data.dummy.storyList
import com.alz19.novelis.model.Story


@Composable
fun CardStoryItem(
    modifier: Modifier = Modifier,
    story: Story
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(70.dp),
                painter = painterResource(id = story.image),
                contentDescription = "Story illustration",
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = story.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = story.genre,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Ditulis Oleh ${story.author}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.secondary

                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardStoryItemPreview() {
    CardStoryItem(
        story = storyList[0]
    )
}
