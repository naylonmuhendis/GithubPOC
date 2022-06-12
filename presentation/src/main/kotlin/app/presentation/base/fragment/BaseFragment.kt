package app.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import app.presentation.base.viewmodel.BaseViewModel
import app.presentation.extension.viewBinding

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    var binding: DB? = null

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lifecycleOwner = viewLifecycleOwner

    }
}