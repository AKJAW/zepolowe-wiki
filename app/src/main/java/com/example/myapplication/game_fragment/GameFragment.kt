package com.example.myapplication.game_fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.game_fragment.adapter.LinkAdapter
import kotlinx.android.synthetic.main.main_game.view.*

class GameFragment: Fragment(){
    private var parentContext: Context? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameViewModel = ViewModelProviders.of(this)[GameViewModel::class.java]

        gameViewModel.targetArticle.observe(this@GameFragment, Observer {
            val s = ":"
        })

        gameViewModel.currentArticle.observe(this@GameFragment, Observer {
            val s = ":"
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.main_game, container, false)
        (activity as? AppCompatActivity)?.setSupportActionBar(root.my_toolbar)

        recyclerView = root.wiki_navigation_recycler_view

        recyclerView.apply {
            adapter = LinkAdapter(listOf())
            layoutManager = LinearLayoutManager(parentContext)
            setHasFixedSize(true)
        }

        return root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        parentContext = context
    }

    override fun onDetach() {
        super.onDetach()

        parentContext = context
    }
}