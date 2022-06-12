package app.presentation.products.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import app.domain.products.entity.Repo
import app.domain.products.factory.RepoFactory
import app.domain.products.usecase.GetBeersListByCoroutineParams
import app.domain.products.usecase.GetGithubRepoSearchUseCase
import app.presentation.base.adapter.RecyclerItem
import app.presentation.products.repolist.RepoListViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString


@RunWith(JUnit4::class)
class RepoListViewModelTest {

    private lateinit var viewModel: RepoListViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var getBeersListByCoroutineUseCase: GetGithubRepoSearchUseCase

    @MockK
    lateinit var savedStateHandle: SavedStateHandle

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
    fun `test when ProductsListViewModel is initialized, products are fetched`() =
        runTest {
            // Given
            val givenProducts = RepoFactory.createProducts(3)
            val flow = flow<PagingData<Repo>> {
//                emit(LoadState.Loading)
                delay(10)
                emit(PagingData.from(givenProducts))
            }
            val productsListObserver = mockk<Observer<PagingData<RecyclerItem>>>(relaxed = true)

            // When
            coEvery {
                getBeersListByCoroutineUseCase.invoke(GetBeersListByCoroutineParams(anyString()))
            }
                .returns(flow)

            // Invoke

            viewModel = RepoListViewModel(
                getGithubRepoSearchUseCase = getBeersListByCoroutineUseCase,
            )
            viewModel.productsListByCoroutine.asLiveData().observeForever(productsListObserver)

            // Then
            advanceTimeBy(10)
            coVerify(exactly = 1) {
                getBeersListByCoroutineUseCase.invoke(
                    GetBeersListByCoroutineParams(anyString())
                )
            }
            verify { productsListObserver.onChanged(matchNullable { it != null }) }
        }

}