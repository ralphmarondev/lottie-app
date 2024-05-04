package com.maronworks.myapplication.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.maronworks.myapplication.R
import com.maronworks.myapplication.details.components.ResultDialog
import com.maronworks.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    onBack: () -> Unit,
) {
    val rawComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.happy))
    val progress by animateLottieCompositionAsState(
        composition = rawComposition, iterations = 100
    )
    var showDialog by remember { mutableStateOf(false) }
    var lottie by remember {
        mutableIntStateOf(R.raw.love)
    }

    // text
    val subTitle = "May I get a chance to be with you?"
    var displayedText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        for (i in 1 until subTitle.length + 1) {
            displayedText = subTitle.substring(0, i)
            delay(100)
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Details", fontFamily = FontFamily.Monospace
            )
        }, navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack, contentDescription = ""
                )
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
        )
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                //.background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LottieAnimation(
                    composition = rawComposition,
                    progress = { progress },
                    modifier = Modifier.size(250.dp)
                )

                Spacer(modifier = Modifier.height(25.dp))

                ElevatedCard(
                    modifier = Modifier.padding(15.dp), colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 30.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "It's finally May,",
                            fontWeight = FontWeight.W600,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = displayedText,//"May I get a change to be with you?",
                            fontWeight = FontWeight.W500,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ElevatedButton(
                        onClick = {
                            showDialog = !showDialog
                            lottie = R.raw.sad
                        },
                        modifier = Modifier
                            .weight(1f),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    ) {
                        Text(
                            text = "NO",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W600,
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                        )
                    }
                    Button(
                        onClick = {
                            showDialog = !showDialog
                            lottie = R.raw.love
                        },
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "YES",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W600,
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                        )
                    }
                }
            }
        }

        if (showDialog) {
            ResultDialog(lottie = lottie) {
                showDialog = false
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetailsScreenPreview() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            DetailsScreen {

            }
        }
    }
}
