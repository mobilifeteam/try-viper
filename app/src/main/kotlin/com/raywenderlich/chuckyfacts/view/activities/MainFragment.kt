package com.raywenderlich.chuckyfacts.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.chuckyfacts.BaseApplication
import com.raywenderlich.chuckyfacts.MainContract
import com.raywenderlich.chuckyfacts.R
import com.raywenderlich.chuckyfacts.entity.Joke
import com.raywenderlich.chuckyfacts.view.adapters.JokesListAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.toast
import org.koin.androidx.scope.ScopeFragment
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

class MainFragment : ScopeFragment(), MainContract.View {

    private val presenter: MainContract.Presenter by inject { parametersOf(this) }
    private val recyclerView: RecyclerView by lazy { rv_jokes_list_activity_main }
    private val progressBar: ProgressBar by lazy { prog_bar_loading_jokes_activity_main }
    private val textSalt: TextView by lazy { tv_salt }
    private val webViewAnnouncement: WebView by lazy { wv_announcement }

    private val navigator: Navigator? by lazy {
        object : Navigator {
            override fun applyCommand(command: Command) {
                if (command is Forward) {
                    forward(command)
                }
            }

            private fun forward(command: Forward) {
                val data = (command.transitionData as Joke)

                when (command.screenKey) {
                    DetailActivity.TAG -> startActivity(Intent(context, DetailActivity::class.java)
                            .putExtra("data", data as Parcelable))
                    else -> Log.e("Cicerone", "Unknown screen: " + command.screenKey)
                }
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onResume() {
        super.onResume()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.setNavigator(navigator)
        presenter.onViewCreated()
    }

    override fun onPause() {
        super.onPause()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.removeNavigator()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun initInstance() {
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = JokesListAdapter({ joke -> presenter.listItemClicked(joke) }, null)
    }

    override fun showLoading() {
        recyclerView.isEnabled = false
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        recyclerView.isEnabled = true
        progressBar.visibility = View.GONE
    }

    override fun publishDataList(data: List<Joke>) {
        (recyclerView.adapter as JokesListAdapter).updateData(data)
        presenter.getSalt()
    }

    override fun showInfoMessage(msg: String) {
        context?.toast(msg)
    }

    override fun showSalt(salt: String) {
        textSalt.text = getString(R.string.text_salt, salt)
    }

    override fun showAnnouncement(content: String) {
        webViewAnnouncement.setBackgroundColor(Color.TRANSPARENT)
        webViewAnnouncement.loadDataWithBaseURL(null,
                content,
                "text/html",
                "utf-8", null
        )
    }
}