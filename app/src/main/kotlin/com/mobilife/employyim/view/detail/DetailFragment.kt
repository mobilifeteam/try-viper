package com.mobilife.employyim.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mobilife.employyim.BaseApplication
import com.mobilife.employyim.contract.DetailContract
import com.mobilife.employyim.R
import com.mobilife.employyim.entity.Joke
import kotlinx.android.synthetic.main.fragment_detail.*
import org.jetbrains.anko.toast
import org.koin.androidx.scope.ScopeFragment
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command

private const val ARG_JOKE = "joke"

class DetailFragment : ScopeFragment(), DetailContract.View {

    private var joke: Joke? = null

    private val tvId: TextView? by lazy { tv_joke_id_activity_detail }
    private val tvJoke: TextView? by lazy { tv_joke_activity_detail }
    private val presenter: DetailContract.Presenter by inject { parametersOf(this) }

    private val navigator: Navigator? by lazy {
        object : Navigator {
            override fun applyCommand(command: Command) {
                if (command is Back) {
                    back()
                }
            }

            private fun back() {
                activity?.finish()
            }
        }
    }

    companion object {
        fun newInstance(joke: Joke) =
                DetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_JOKE, joke)
                    }
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            joke = it.getParcelable(ARG_JOKE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onResume() {
        super.onResume()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.removeNavigator()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        joke?.let {
            presenter.onViewCreated(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                presenter.backButtonClicked()
                true
            }
            else -> false
        }
    }


    override fun showJokeData(id: String, joke: String) {
        tvId?.text = id
        tvJoke?.text = joke
    }

    override fun showInfoMessage(msg: String) {
        context?.toast(msg)
    }
}