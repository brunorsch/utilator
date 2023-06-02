package br.dev.brunnofdc.utilator.ui.components.input

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun InputDigitos(
    modifier: Modifier = Modifier,
    state: MutableState<String>,
    digitos: Int,
) {
    val (value, setValue) = state

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    BasicTextField(
        modifier = modifier.focusRequester(focusRequester),
        value = TextFieldValue(value, selection = TextRange(value.length)),
        onValueChange = {
            if (it.text.length <= digitos && it.text.matches(Regex("\\d*"))) {
                setValue(it.text)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(digitos) { index ->
                    CharView(
                        index = index,
                        text = value
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val isNext = index > text.length
    val relativeStringChar = if(isNext || isFocused) null else text[index].toString()
    val shownChar = relativeStringChar ?: if (isFocused) "0" else ""
    val isFilled = shownChar == relativeStringChar

    val charColor =  when {
        isFocused -> Color.Red
        isFilled -> colorScheme.onSecondary
        else -> colorScheme.onTertiary
    }

    val borderSize: Dp by animateDpAsState(if (isFocused) 2.dp else 1.dp, TweenSpec(200))

    Text(
        modifier = Modifier
            .width(40.dp)
            .border(borderSize, charColor, RoundedCornerShape(8.dp))
            .padding(2.dp),
        text = shownChar,
        style = MaterialTheme.typography.headlineLarge,
        color = charColor,
        textAlign = TextAlign.Center
    )
}