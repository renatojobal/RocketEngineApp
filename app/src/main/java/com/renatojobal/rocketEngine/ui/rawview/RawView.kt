package com.renatojobal.rocketEngine.ui.rawview

import android.widget.ScrollView
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.renatojobal.rocketEngine.SharedViewModel


@Composable
fun RawView(sharedViewModel: SharedViewModel) {

    val targetText by sharedViewModel.receivedRdf.observeAsState()

    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    var text by remember { (mutableStateOf(targetText.toString() ?: "")) }


//    // Step 1. Declare Language & Code
//    val language = CodeLang.Kotlin
//    val code = """
//    package com.wakaztahir.codeeditor
//
//    fun main(){
//        println("Hello World");
//    }
//""".trimIndent()
//
//// Step 2. Create Parser & Theme
//    val parser = remember { PrettifyParser() } // try getting from LocalPrettifyParser.current
//    var themeState by remember { mutableStateOf(CodeThemeType.Monokai) }
//    val theme = remember(themeState) { themeState.theme() }
//
//
//    val parsedCode = remember {
//        parseCodeAsAnnotatedString(
//            parser = parser,
//            theme = theme,
//            lang = lang,
//            code = code
//        )
//    }
//
//

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp)
        .verticalScroll(rememberScrollState())) {
        Button(onClick = {
            clipboardManager.setText(AnnotatedString((text)))
        }) {
            Text("Copy")
        }
        Text(text = text)


    }



}