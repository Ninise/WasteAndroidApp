package com.ndteam.wasteandroidapp.view.main.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ndteam.wasteandroidapp.models.GarbageItem
import com.ndteam.wasteandroidapp.models.RecycleType
import com.ndteam.wasteandroidapp.ui.theme.*

@Composable
fun SearchMainScreen(navController: NavController?) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    val searchSuggestions = arrayListOf<String>("plastic bag", "meat", "cup", "pan", "banana")

    val garbage = arrayListOf<GarbageItem>(
        GarbageItem(
            icon = "https://im.indiatimes.in/content/2021/Jul/plastic-bottle_60df027c2b119.jpg",
            name = "Plastic bottle",
            wayToRecycler = "Clean it and put it in recycle bin",
            type = RecycleType.RECYCLE
        ),
        GarbageItem(
            icon = "https://img.huffingtonpost.com/asset/5bad6d8b2200003501daad00.jpeg",
            name = "Plastic bag",
            wayToRecycler = "Put it in recycler bin",
            type = RecycleType.RECYCLE
        ),
        GarbageItem(
            icon = "https://akns-images.eonline.com/eol_images/Entire_Site/2022912/rs_1200x1200-221012142652-1200-balendciaga-lays-potato-chip-purse.jpg",
            name = "Plastic pack",
            wayToRecycler = "Put it in garbage bin",
            type = RecycleType.GARBAGE
        ),
    )

    Box(modifier =
    Modifier
        .fillMaxSize()
        .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SearchView(state = textState)

            LazyRow(modifier = Modifier.padding(start = 15.dp)) {
                items(searchSuggestions) {
                    SearchChip(text = it, onItemClick = {

                    })
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn() {
                items(garbage) {
                    GarbageItemView(
                        item = it,
                        onItemClick = {}
                    )
                }
            }
        }
    }

}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        if (state.value == TextFieldValue("")) {
            IconButton(onClick = {

            }) {
                Icon(
                    Icons.Default.ArrowBack,
                    tint = IconsGray,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .size(24.dp)
                )
            }
        }

        TextField(
            value = state.value,
            onValueChange = { value ->
                state.value = value
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            textStyle = TextStyle(color = BodyText, fontSize = 16.sp),
            leadingIcon = {
                if (state.value != TextFieldValue("")) {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            tint = IconsGray,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                } else {
                    Icon(
                        Icons.Default.Search,
                        tint = IconsGray,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }

            },
            trailingIcon = {
                if (state.value != TextFieldValue("")) {
                    IconButton(
                        onClick = {
                            state.value =
                                TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                        }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            tint = IconsGray,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(16.dp), // The TextFiled has rounded corners top left and right by default
            colors = TextFieldDefaults.textFieldColors(
                textColor = BodyText,
                cursorColor = BodyText,
                leadingIconColor = IconsGray,
                trailingIconColor = Color.White,
                backgroundColor = SearchBackGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun SearchChip(text: String, onItemClick: (String) -> Unit) {
    Box(modifier = Modifier
        .padding(horizontal = 4.dp)
        .background(color = ChipBackGray, shape = RoundedCornerShape(10.dp))
        .clickable { onItemClick(text) }) {
        Text(
            text = text,
            color = BodyText,
            style = MaterialTheme.typography.body1,
            fontSize = 12.sp,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun GarbageItemView(item: GarbageItem, onItemClick: (String) -> Unit) {
   Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
       AsyncImage(
           model = item.icon,
           contentDescription = item.name,
           contentScale = ContentScale.Crop,
           modifier = Modifier
               .size(80.dp, height = 80.dp)
               .clip(RoundedCornerShape(11.dp))

       )

       Column (Modifier.weight(1f).padding(horizontal = 15.dp)) {
           Text(
               text = item.name,
               color = TitleText,
               style = MaterialTheme.typography.h1,
               fontSize = 16.sp,
               letterSpacing = 1.sp,
           )

           Spacer(modifier = Modifier.height(5.dp))

           Text(
               text = item.wayToRecycler,
               color = BodyText,
               style = MaterialTheme.typography.h1,
               fontSize = 12.sp,
               letterSpacing = 1.sp,
           )
       }

       Icon(
           Icons.Filled.Star,
           tint = IconsGray,
           contentDescription = "",
           modifier = Modifier
               .padding(end = 15.dp)
               .size(24.dp)
               .align(Alignment.CenterVertically),
       )

   }
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    SearchMainScreen(navController = null)
}