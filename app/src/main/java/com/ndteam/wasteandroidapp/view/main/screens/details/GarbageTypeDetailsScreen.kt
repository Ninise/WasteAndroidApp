package com.ndteam.wasteandroidapp.view.main.screens.search

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ndteam.wasteandroidapp.R
import com.ndteam.wasteandroidapp.models.GarbageItem
import com.ndteam.wasteandroidapp.models.RecycleType
import com.ndteam.wasteandroidapp.ui.theme.*
import kotlin.math.min


@Composable
fun GarbageTypeDetailsScreen(navController: NavController?) {
    val scrollState = rememberScrollState()

    val title: String = "Why recycle is important?"
    val headerImage: Int = R.drawable.ic_recycling_details_header
    val pageColor: Color = MainOrange
    val typeIcon: Int = R.drawable.ic_recycle
    val descriptionText: String = "Recycling is important because it helps to conserve natural resources, reduce waste and pollution, save energy, create jobs, and support local economies."


    Box(modifier =
    Modifier
        .fillMaxSize()
        .background(color = pageColor)) {

        DetailsHeaderView(headerImage = headerImage, scrollStateValue = scrollState.value)

        Box(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(top = 147.dp)
                .background(
                    color = Color.White,
                    RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
                .verticalScroll(scrollState)) {


            DetailsTextView(
                title = title,
                descriptionText = descriptionText,
                typeIcon = typeIcon
            )
        }

    }
}

@Composable
private fun DetailsHeaderView(headerImage: Int, scrollStateValue: Int) {
    Image(
        painter = painterResource(id = headerImage),
        contentDescription = "City background",
        modifier = Modifier
            .height(160.dp)
            .fillMaxWidth()
            .graphicsLayer {
                alpha = min(1f, 1 - (scrollStateValue / 600f))
                translationY = -scrollStateValue * 0.1f
            },
        contentScale = ContentScale.Crop
    )
}


@Composable
private fun DetailsTextView(title: String, descriptionText: String, typeIcon: Int) {
    Column(modifier =
    Modifier.padding(start = 21.dp, end = 21.dp, top = 28.dp)) {

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title,
                color = TitleText,
                fontFamily = Nunito,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                letterSpacing = 1.sp,
                modifier = Modifier.weight(1f)
            )

            Icon(
                painterResource(id = typeIcon),
                tint = GarbageTypeIconColor,
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp),
            )
        }

        Text(
            text = descriptionText,
            color = BodyText,
            fontFamily = Nunito,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(top = 10.dp)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun GarbageTypeDetailsScreenPreview() {
    GarbageTypeDetailsScreen(navController = null)
}