package app.presentation.products.repolist

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import app.domain.base.Failure
import app.presentation.R
import app.presentation.base.adapter.LoadingStateAdapter
import app.presentation.base.adapter.RecyclerItem
import app.presentation.base.fragment.BaseFragment
import app.presentation.databinding.FragmentRepoListBinding
import app.presentation.extension.collectIn
import app.presentation.extension.gone
import app.presentation.extension.invisible
import app.presentation.extension.visible
import app.presentation.products.entity.base.ViewSettingsUIModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoListFragment : BaseFragment<FragmentRepoListBinding, RepoListViewModel>() {

    override fun getLayoutRes() = R.layout.fragment_repo_list
    override val viewModel: RepoListViewModel by viewModels()
    override fun updateHeader() {
        mainActivityViewModel.viewSettingsUIModel.postValue(
            ViewSettingsUIModel(
                isHeaderVisible = true,
                title = getString(R.string.home),
                isBackButtonVisible = false,
                isCloseBtnVisible = false,
                isBottomNavVisible = false,
                isThemeChangeButtonVisible = true
            )
        )
    }

    private val productsListAdapter: RepoListAdapter by lazy {
        RepoListAdapter(::navigateToRepoDetail)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupRecycler()
    }


    private fun navigateToRepoDetail(item: RecyclerItem, view: View) {
        //TODO $item $view
        println("$item ${view.hashCode()}")
    }

    private fun setupView() {
        viewModel.run {
            productsListByCoroutine.collectIn(viewLifecycleOwner) {
                addRepoList(it)
            }

            failure.collectIn(viewLifecycleOwner) {
                handleFailure(it)
            }
        }
    }

    private fun loadingUI(isLoading: Boolean) {
        binding?.inclItemError?.itemErrorContainer.gone()
        if (isLoading) {
            binding?.inclItemLoading?.itemLoadingContainer?.visible()
        } else {
            binding?.inclItemLoading?.itemLoadingContainer.gone()
        }
    }

    private fun handleFailure(failure: Failure) {
        binding?.repoRv.gone()
        binding?.inclItemError?.itemErrorContainer?.visible()
        when (failure) {
            is Failure.NoInternet, is Failure.Api, is Failure.Timeout -> {
                setupErrorItem(failure)
            }
            is Failure.Unknown -> {
                setupErrorItem(failure)
            }
            else -> {
                binding?.inclItemError?.itemErrorMessage?.text = failure.message
                binding?.inclItemError?.itemErrorRetryBtn?.invisible()
            }
        }
    }

    private fun adapterLoadingErrorHandling(combinedLoadStates: CombinedLoadStates) {
        if (combinedLoadStates.refresh is LoadState.Loading) {
            loadingUI(true)
        } else {
            loadingUI(false)
            val error = when {
                combinedLoadStates.prepend is LoadState.Error -> combinedLoadStates.prepend as LoadState.Error
                combinedLoadStates.source.prepend is LoadState.Error -> combinedLoadStates.prepend as LoadState.Error
                combinedLoadStates.append is LoadState.Error -> combinedLoadStates.append as LoadState.Error
                combinedLoadStates.source.append is LoadState.Error -> combinedLoadStates.append as LoadState.Error
                combinedLoadStates.refresh is LoadState.Error -> combinedLoadStates.refresh as LoadState.Error
                else -> null
            }
            error?.run {
                viewModel.handleFailure(this.error) { retryFetchData() }
            }
        }
    }

    private fun setupRecycler() {
        binding?.inclItemError?.itemErrorContainer.gone()
        binding?.repoRv?.adapter =
            productsListAdapter.withLoadStateFooter(LoadingStateAdapter())
        productsListAdapter.addLoadStateListener { adapterLoadingErrorHandling(it) }
        productsListAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        postponeEnterTransition()
        view?.doOnPreDraw { startPostponedEnterTransition() }
    }

    private fun retryFetchData() {
        binding?.repoRv?.visible()
        productsListAdapter.retry()
    }

    private fun setupErrorItem(failure: Failure) {
        binding?.inclItemError?.itemErrorMessage?.text = failure.msg
        binding?.inclItemError?.itemErrorRetryBtn?.setOnClickListener { failure.retryAction() }
    }

    private fun addRepoList(productsList: PagingData<RecyclerItem>) {
        binding?.repoRv?.visible()
        productsListAdapter.submitData(lifecycle, productsList)
    }
}