package app.presentation.products.virtualcv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import app.presentation.R
import app.presentation.base.adapter.RecyclerItem
import app.presentation.base.fragment.BaseFragment
import app.presentation.base.viewmodel.BaseViewModel
import app.presentation.databinding.FragmentVirtualCvBinding
import app.presentation.databinding.ItemSkillsLayoutBinding
import app.presentation.products.entity.base.ViewSettingsUIModel
import app.presentation.products.repolist.RepoListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentVirtualCV : BaseFragment<FragmentVirtualCvBinding, BaseViewModel>() {
    override fun getLayoutRes() = R.layout.fragment_virtual_cv
    override val viewModel: BaseViewModel by viewModels()
    override fun updateHeader() {
        mainActivityViewModel.viewSettingsUIModel.postValue(
            ViewSettingsUIModel(
                isHeaderVisible = false,
                title = "",
                isBackButtonVisible = false,
                isCloseBtnVisible = false,
                isBottomNavVisible = false,
                isThemeChangeButtonVisible = false
            )
        )
    }

    private val galleryListAdapter: GalleryListAdapter by lazy {
        GalleryListAdapter(::galleryClick)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addSkill(getSkillList())
        setupGallery()
    }


    private fun setupGallery() {
        binding?.galleryRv?.apply {
            adapter = galleryListAdapter
        }
        galleryListAdapter.submitList(getGalleryItems())

    }

    fun getGalleryItems(): List<GalleryUI> {
        return listOf(
            GalleryUI(0, R.drawable.ic_gallery1),
            GalleryUI(1, R.drawable.ic_gallery2),
            GalleryUI(2, R.drawable.ic_gallery3),
            GalleryUI(3, R.drawable.ic_gallery4))
    }

    private fun addSkill(
        skills: List<String>,
    ) {
        skills.forEachIndexed { index, skill ->
            val item = ItemSkillsLayoutBinding.inflate(
                LayoutInflater.from(requireContext()),
                binding?.skillCg,
                false
            )
            item.text.text = skill
            binding?.skillCg?.addView(item.root)
        }
    }

    private fun galleryClick(item: RecyclerItem, view: View) {
        //TODO $item $view
        println("$item ${view.hashCode()}")
    }

    fun getSkillList(): List<String> {
        return listOf("HTML", "CSS", "JavaScript", "Git", "Photoshop")
    }
}