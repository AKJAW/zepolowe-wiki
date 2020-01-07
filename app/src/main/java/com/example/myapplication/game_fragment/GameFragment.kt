package com.example.myapplication.game_fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.game_fragment.adapter.LinkAdapter
import kotlinx.android.synthetic.main.article_header.view.*
import kotlinx.android.synthetic.main.main_game.*
import kotlinx.android.synthetic.main.main_game.view.*

class GameFragment: Fragment(){
    private var parentContext: Context? = null
    private lateinit var linkAdapter: LinkAdapter
    private lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linkAdapter = LinkAdapter(listOf())

        gameViewModel = ViewModelProviders.of(this)[GameViewModel::class.java]

        gameViewModel.targetArticle.observe(this@GameFragment, Observer {
            target_article_view.setArticle(it)
            target_article_view.setIsLoading(false)
        })

        gameViewModel.currentArticle.observe(this@GameFragment, Observer {
            current_article_view.setArticle(it)
            current_article_view.setIsLoading(false)

            //TODO linkAdapter z lista
            linkAdapter.setLinks(it.links)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.main_game, container, false)
        (activity as? AppCompatActivity)?.setSupportActionBar(root.my_toolbar)

        val recyclerView = root.wiki_navigation_recycler_view

        recyclerView.apply {
            adapter = linkAdapter
            layoutManager = LinearLayoutManager(parentContext)
            setHasFixedSize(true)
        }

        gameViewModel.getRandomArticleForTarget()
        root.target_article_view.setIsLoading(true)

        gameViewModel.getRandomArticleForCurrent()
        root.current_article_view.setIsLoading(true)

        root.current_article_view.article_header_button_text_view.setOnClickListener {
            current_article_view.toggleView()
        }

        root.target_article_view.article_header_button_text_view.setOnClickListener {
            target_article_view.toggleView()
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