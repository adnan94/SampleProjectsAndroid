package com.example.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.example.UserResponseTest
import com.example.scalioapp.source.UsersRepositoryImpl
import com.example.scalioapp.ui.viewmodels.UsersViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserViewModelTest {

    @MockK
    var viewModel = UsersViewModel(UsersRepositoryImpl())

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var userResponseTest = UserResponseTest()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `check weather userviewmodel returning same data`() = runBlocking {

        val usersList = userResponseTest.getUsersList()
        val userResponsePagingData = userResponseTest.getUsersResponsePagingData(usersList)


        coEvery { viewModel.getUsers("") }
            .returns(userResponsePagingData)

        val usersFromViewModel = viewModel.getUsers("").value

        MatcherAssert.assertThat(usersFromViewModel, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            usersFromViewModel,
            CoreMatchers.instanceOf(PagingData::class.java)
        )
        Assert.assertEquals(usersFromViewModel, userResponsePagingData.value)

        coVerify(exactly = 1) { viewModel.getUsers("") }
        confirmVerified(viewModel)

    }

}