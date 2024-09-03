package br.senai.sp.jandira.Screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import br.senai.sp.jandira.model.Character
import br.senai.sp.jandira.service.RetrofitFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ListAllCharacters(modifier: Modifier = Modifier) {

    val characterList = remember {
        mutableStateOf(listOf<Character>())
    }

    val character = remember {
        mutableStateOf(Character())
    }

    val id = remember {
        mutableStateOf("")
    }


    Column {
        OutlinedTextField(value = id.value,
            onValueChange = { id.value = it })
        Button(onClick = {
            //Efetuar a chamada para a API
            // RetrofitFactory pegue no Character service, o método de buscar o personagem por id

            val callCharacterByID = RetrofitFactory()
                .getCharacterService()
                .getCharacterByID(id.value.toInt())

            //enqueue enfileira a chamada para API quando ela retornar ela vai retornar ou no onResponse ou no onFailure
            //Esse overide fun aparece pois quando escrevemos o object: ... essa palavra vai ficar com um sublinhado amarelo, ao colocar o curso sobre a palavra vai aparecer um more implementes ai clicamos, damos ok e aparece as duas funções

            callCharacterByID.enqueue(object : Callback<Character> {
                override fun onResponse(p0: Call<Character>, p1: Response<Character>) {
                    //Apagamos o T0d0 e escrevemoso código
                    character.value = p1.body()!!
                    Log.i("RICK_MORTY", "${character.value.name} -  ${character.value.origin!!.name}")
                }

                override fun onFailure(p0: Call<Character>, p1: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        })
        {
            Text(text = "Buscar")
        }
        AsyncImage(model = character.value.image,
            contentDescription = "")

        Text(text = character.value.name)
        Text(text = character.value.origin?.name ?: "")
        Text(text = character.value.species)
        Text(text = character.value.gender)
        Text(text = character.value.status)
        Text(text = character.value.location?.name ?: "")
        Text(text = character.value.type)

    }
}