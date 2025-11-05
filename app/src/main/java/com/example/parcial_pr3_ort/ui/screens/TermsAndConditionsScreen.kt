package com.example.parcial_pr3_ort.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_pr3_ort.ui.theme.CaribbeanGreen
import com.example.parcial_pr3_ort.ui.theme.LightGreen

@Composable
fun TermsAndConditionsScreen(
    onAcceptClick: () -> Unit = {}
) {
    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CaribbeanGreen)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Main content container
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp)
                .background(LightGreen, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Scrollable content
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Est Fugiat Assumenda Aut Reprehenderit",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = "Lorem ipsum dolor sit amet. Et odio officia aut voluptate internos est omnis vitae ut architecto sunt non tenetur. Aut provident vero Quo aspernatur facere et consectetur ipsum et facere corrupti est asperiatur facere. Est fugiat assumenda aut reprehenderit voluptatem sed.",
                        fontSize = 14.sp,
                        color = Color.Black,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = "1. Ea voluptates omnis aut sequi sequi.\n" +
                                "2. Est dolore quae in aliquid ducimus et qutem repellendus.\n" +
                                "3. Aut ipsum Quis qui porro quasi aut minus placeat!\n" +
                                "4. Sit consequatur neque non vitae facere.",
                        fontSize = 14.sp,
                        color = Color.Black,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = "Aut quidem accusantium nam alias autem eum officiis placeat et omnis autem id officiis perspiciatis qui corrupti officia eum aliquam provident. Eum voluptas error et optio dolorum cum molestiae nobis et odit molestiae quo magnat impedit sed fugiat nihil vitae.",
                        fontSize = 14.sp,
                        color = Color.Black,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = "• Aut fuga sequi eum voluptatibus provident.\n" +
                                "• Eos consequuntur voluptatem vel amet eaque aut dignissimos velit.",
                        fontSize = 14.sp,
                        color = Color.Black,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = "Vel exercitationem quam vel eligendi rerum At harum obcaecati et nostrum beatae? Ea accusantium dolores qui rerum aliquam est perferendis mollitia et ipsum ipsa qui enim autem At corporis sunt. Aut odit quisquam est reprehenderit itaque At accusantium dolor qui neque repellat.",
                        fontSize = 14.sp,
                        color = Color.Black,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    val annotatedText = buildAnnotatedString {
                        append("Read the terms and conditions in more detail at ")
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFF0066CC),
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append("www.finwiseapp.de")
                        }
                    }

                    Text(
                        text = annotatedText,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )
                }

                // Checkbox and Accept button
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = { isChecked = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.Black,
                                uncheckedColor = Color.Gray
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "I accept all the terms and conditions",
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }

                    Button(
                        onClick = onAcceptClick,
                        enabled = isChecked,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = CaribbeanGreen,
                            disabledContainerColor = Color.Gray
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Accept",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
