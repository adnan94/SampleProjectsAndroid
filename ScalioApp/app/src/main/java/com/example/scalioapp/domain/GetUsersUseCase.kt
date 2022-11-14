package com.example.scalioapp.domain

import com.example.scalioapp.source.UsersRepository

class GetUsersUseCase(var repository:UsersRepository){

    suspend operator fun invoke(query: String) = repository.getUsersList(query)

}