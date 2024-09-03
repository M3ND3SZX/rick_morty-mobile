package br.senai.sp.jandira.service

import br.senai.sp.jandira.model.Character
import br.senai.sp.jandira.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    //GET (retrofit2.http)
    //Call<t>(retrofit2) o tipo de call que temos que escolher ao usar o retrofit
    //Result do nosso pacote (br.senai..)
    // A URL é padrão na API tod, na requisição colocamos apenas o diferente na URL, no caso da url abaixo né a palvra character
    //https://rickandmortyapi.com/api/character
    @GET("character")
    fun getAllCharacters(): Call<Result>


    //https://rickandmortyapi.com/api/character/1
    @GET("character/{id}")
    fun getCharacterByID(@Path("id")id:Int): Call<Character>
}