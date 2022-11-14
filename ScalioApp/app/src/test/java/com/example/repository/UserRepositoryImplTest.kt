package com.example.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.example.UserResponseTest
import com.example.scalioapp.source.UsersRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserRepositoryImplTest {

    @MockK
    var userRepository = UsersRepositoryImpl()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var userResponseTest = UserResponseTest()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `check weather user repository returning same data`() = runBlocking {

        val usersList = userResponseTest.getUsersList()
        val userResponsePagingData = userResponseTest.getUsersResponsePagingData(usersList)

        coEvery { userRepository.getUsersList("") }
            .returns(userResponsePagingData)

        val usersFromRepository = userRepository.getUsersList("").value

        MatcherAssert.assertThat(usersFromRepository, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(usersFromRepository, CoreMatchers.instanceOf(PagingData::class.java))
        Assert.assertEquals(usersFromRepository, userResponsePagingData.value)

        coVerify(exactly = 1) { userRepository.getUsersList("") }
        confirmVerified(userRepository)

    }

}