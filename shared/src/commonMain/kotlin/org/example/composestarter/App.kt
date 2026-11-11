package org.example.composestarter

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp


import org.jetbrains.compose.resources.painterResource

import composestarter.shared.generated.resources.Res
import composestarter.shared.generated.resources.compose_multiplatform


@Composable
fun Adder(){
    var counter = remember{ mutableStateOf(0)}
    Column{
        Button(onClick = {counter.value++}){
            Text("Add one")
        }
        Text("Counter = "+ counter.value.toString(),fontSize = 34.sp,color = Color.Red)
    }
}

@Composable
fun Converter(){
    val numstate = remember{ mutableStateOf("0")}
    Column{
        OutlinedTextField(value = numstate.value,onValueChange = {
            numstate.value = it
        },label = {Text("In feet :")}, singleLine = true)
        if((numstate.value.toDoubleOrNull()?:("er"))!="er"){
            Text("In meters :"+((numstate.value.toDoubleOrNull()?:(0.0))*0.305).toString())
        }
        else{
            Text("Error You Have Entered Something Wrong",fontSize = 60.sp,color = Color.Red)
        }

    }
}

@Composable
fun Login(){
    val passstate = remember{ mutableStateOf("")}
    val usestate = remember{ mutableStateOf("")}
    val instate = remember{ mutableStateOf(false)}
    val passwordstate = remember{ mutableStateOf("password")}
    val changer : (String) -> Unit = {passwordstate.value = it}
    val erstate = remember{ mutableStateOf("")}
    Column{

        if(instate.value){
            Text("Logged in as alex")
            Button(onClick = {
                instate.value = false

            }){
                Text("Login Out")
            }
            ChangePassword(changer)
        }
        else{
            OutlinedTextField(value = usestate.value,onValueChange ={
                usestate.value = it
            },label = {Text("Username")}, singleLine = true)

            OutlinedTextField(value = passstate.value,onValueChange ={
                passstate.value = it
            }, visualTransformation = PasswordVisualTransformation(),label = {Text("Password")}, singleLine = true)
            Button(onClick = {
                if(passstate.value == passwordstate.value&&usestate.value == "alex"){
                    instate.value = true
                    erstate.value = ""
                }
                else{
                    erstate.value = "error wrong password or username"
                }
            }){
                Text("Login")
            }
            Text(text = erstate.value,color = Color.Red)
        }



    }

}

@Composable
fun ChangePassword(changer:(String) -> Unit){
    val newpassstate = remember{ mutableStateOf("")}
    Column{
        Text("\n\nChange password here")
        OutlinedTextField(value = newpassstate.value,onValueChange ={
            newpassstate.value = it
        }, visualTransformation = PasswordVisualTransformation(),label = {Text("New Password")}, singleLine = true)
        Button(onClick = {
            changer(newpassstate.value)
        }){
            Text("Submit")
        }
    }
}


@Composable
@Preview
fun App() {
    MaterialTheme {
        val nameState = remember { mutableStateOf("") }
        Column {
            Text(text = "Enter your favourite programming language:",fontSize = 24.sp)
            OutlinedTextField(value = nameState.value, onValueChange = {
                nameState.value = it
            }, singleLine = true)
            Text("Your favourite programming language is "+nameState.value,fontSize = 24.sp)
            Adder()
            Converter()
            Login()
        }

    }
}