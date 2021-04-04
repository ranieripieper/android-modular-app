package me.ranieripieper.android.github.core.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import me.ranieripieper.android.github.core.di.module.ViewModelFactory
import me.ranieripieper.android.github.core.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getViewBinding()
        setContentView(binding.root)

        initializeDagger()

        viewModel = run {
            ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
        }

    }

    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return type as Class<VM>
    }

    /**
     * Get ViewBinding from Activity
     * Sample: return ActivityRepositoryBinding.inflate(layoutInflater)
     */
    abstract fun getViewBinding(): VB

    /**
     * Initialize Dagger
     */
    abstract fun initializeDagger()
}