package app.domain.products.usecase

import androidx.paging.PagingData
import app.domain.products.factory.RepoFactory
import app.domain.products.repository.GithubApiRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString

@RunWith(JUnit4::class)
class GetGithubRepoSearchUseCaseTest {

    @MockK
    lateinit var productsListRepository: GithubApiRepository

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test invoking GetBeersListByCoroutineUseCase gives list of products`() =
        runTest {
            // Given
            val usecase = GetGithubRepoSearchUseCase(productsListRepository)
            val givenProducts = RepoFactory.createProducts(3)
            val expectedProducts = PagingData.from(givenProducts)

            // When
            coEvery { productsListRepository.getKotlinReposSearch(anyString()) }
                .returns(flowOf(expectedProducts))

            // Invoke
            val productsListFlow = usecase(GetBeersListByCoroutineParams(""))

            // Then
            assertThat(productsListFlow, notNullValue())

            val productsListDataState = productsListFlow.first()
            assertThat(productsListDataState, notNullValue())
            assertThat(productsListDataState, instanceOf(PagingData::class.java))
//            assertThat(productsListDataState, equalTo(expectedProducts))
        }
}