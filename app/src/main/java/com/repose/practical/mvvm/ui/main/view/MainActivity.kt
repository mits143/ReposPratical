package com.repose.practical.mvvm.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.repose.practical.mvvm.R
import com.repose.practical.mvvm.data.model.Article
import com.repose.practical.mvvm.sessionManager
import com.repose.practical.mvvm.ui.main.adapter.MainAdapter
import com.repose.practical.mvvm.ui.main.viewmodel.MainViewModel
import com.repose.practical.mvvm.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MainAdapter.clickListener {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupObserver()

    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf(), this)
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.users.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users ->
                        sessionManager.saveData(users)
                        renderList(users.articles)
                    }
                    recyclerView.visibility = View.VISIBLE

                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<Article>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    override fun click(data: Article) {
        startActivity(Intent(this, DetailActivity::class.java).putExtra("data", data))
    }
}
