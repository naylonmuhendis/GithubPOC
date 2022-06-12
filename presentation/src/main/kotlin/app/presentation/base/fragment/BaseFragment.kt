package app.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewbinding.ViewBinding
import app.presentation.base.viewmodel.BaseViewModel
import app.presentation.extension.viewBinding
import app.presentation.main.MainActivityViewModel

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    protected abstract val viewModel: VM
    var binding: DB? = null

    abstract fun updateHeader()

    val mainActivityViewModel by activityViewModels<MainActivityViewModel>()

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        println("🤣 ${this.javaClass.simpleName} #${this.hashCode()} onCreateView()")
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)

        return binding!!.root
    }

    override fun onResume() {
        super.onResume()
        updateHeader()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lifecycleOwner = viewLifecycleOwner

    }
}