package com.example.kanjipararecordar.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kanjipararecordar.ui.presentation.BaseViewModel
import com.example.kanjipararecordar.ui.presentation.ViewAction
import com.example.kanjipararecordar.ui.presentation.ViewIntent
import com.example.kanjipararecordar.ui.presentation.ViewState
import com.example.kanjipararecordar.ui.theme.KanjiParaRecordarTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Extension function to collect Flow values when the lifecycle is at least STARTED
 */
fun <T> Flow<T>.launchAndCollectIn(
    activity: ComponentActivity,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    block: (T) -> Unit
) {
    activity.lifecycleScope.launch {
        activity.lifecycle.repeatOnLifecycle(minActiveState) {
            collect {
                block(it)
            }
        }
    }
}

abstract class BaseActivity<S : ViewState, A : ViewAction, I : ViewIntent> : AppCompatActivity() {

    abstract val viewModel: BaseViewModel<S, A, I>

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
        applyOverrideConfiguration(newBase.resources.configuration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            BaseActivityScreenContent()
        }

        viewModel.viewActions.launchAndCollectIn(this) { action ->
            handleAction(action)
        }

        load()
    }

    open fun load() {
        viewModel.load()
    }

    /**
     * Called when viewActions flow emit value
     */
    abstract fun handleAction(action: A)

    @Stable
    @Composable
    protected open fun Toolbar() = Unit

    @Stable
    @Composable
    protected open fun ScreenContent() = Unit

    @Composable
    private fun BaseActivityScreenContent() {
        KanjiParaRecordarTheme {
            Column {
                Toolbar()
                Box {
                    ScreenContent()
                }
            }
        }
    }
}
