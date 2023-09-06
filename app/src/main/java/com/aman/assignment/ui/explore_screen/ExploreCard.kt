package com.aman.assignment.ui.explore_screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aman.assignment.R
import com.aman.assignment.ui.theme.AmanAssignmentTheme


@Composable
fun ExploreCard(
    exploreCardItem: ExploreCardItem
) {

    Box {


        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .padding(start = 50.dp)
                .padding(10.dp).clickable {  }
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Text(
                        text = "+ INVITE"
                    )
                }

                Column(modifier = Modifier.padding(start = 40.dp)) {
                    Text(
                        text = exploreCardItem.personName,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "${exploreCardItem.city} | ${exploreCardItem.category}"
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Within ${exploreCardItem.distance}",
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    LinearProgressIndicator(
                        modifier = Modifier
                            .clip(RoundedCornerShape(30.dp))
                            .width(100.dp)
                            .height(10.dp),
                        progress = exploreCardItem.progress
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = exploreCardItem.tagline,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = exploreCardItem.message,
                )

            }

        }

        Image(
            modifier = Modifier
                .padding(30.dp)
                .size(width = 60.dp, height = 100.dp)
                .clip(RoundedCornerShape(20.dp))
                .align(Alignment.TopStart),
            painter = painterResource(id = exploreCardItem.image),
            contentDescription = null,
        )

    }


}

data class ExploreCardItem(
    @DrawableRes val image: Int,
    val personName: String,
    val city: String,
    val category: String,
    val distance: String,
    val progress: Float,
    val tagline: String,
    val message: String,
)


@Preview(showBackground = true)
@Composable
fun ExploreCardPreview() {
    AmanAssignmentTheme {
        ExploreCard(
            ExploreCardItem(
                image = R.drawable.person,
                personName = "Aman Naruka",
                city = "Alwar",
                category = "Student",
                progress = 0.2f,
                tagline = "Coffe | Business | Freindship",
                message = "Hi, Community I am open to new Connections 🙂",
                distance = "2.2 Km"
            )
        )
    }
}