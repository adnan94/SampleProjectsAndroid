package com.example.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.example.UserResponseTest
import com.example.scalioapp.domain.GetUsersUseCase
import com.example.scalioapp.source.UsersRepositoryImpl
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
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetUserUseCaseTest {

    @MockK
    var useCase = GetUsersUseCase(UsersRepositoryImpl())

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var userResponseTest = UserResponseTest()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `check weather user useCase returning same data`() = runBlocking {

        val usersList = userResponseTest.getUsersList()
        val userResponsePagingData = userResponseTest.getUsersResponsePagingData(usersList)

        coEvery { useCase.invoke("") }
            .returns(userResponsePagingData)

        val usersFromUsecase = useCase.invoke("").value

        MatcherAssert.assertThat(usersFromUsecase, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(usersFromUsecase, CoreMatchers.instanceOf(PagingData::class.java))
        Assert.assertEquals(usersFromUsecase, userResponsePagingData.value)

        coVerify(exactly = 1) { useCase.invoke("") }
        confirmVerified(useCase)

    }

}