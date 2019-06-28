package com.wolfdevelloper.estudo.statement


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wolfdevelloper.estudo.R
import com.wolfdevelloper.estudo.recyclerview.adapter.StatementAdapter
import com.wolfdevelloper.estudo.viewmodel.Statement
import com.wolfdevelloper.estudo.viewmodel.StatementViewModel
import kotlinx.android.synthetic.main.fragment_statement.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class StatementFragment : Fragment(), StatementContract.StatementPresenterOutput {

    @Inject
    lateinit var iStatementPresenterInput: StatementContract.StatementPresenterInput

    private lateinit var statementViewModel: StatementViewModel
    private lateinit var myAdapter: StatementAdapter

    init {
        DaggerStatementComponents
            .builder()
            .statementModule(StatementModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            statementViewModel = activity?.run { ViewModelProviders.of(this).get(StatementViewModel::class.java) }
                ?: StatementViewModel()
            myAdapter = StatementAdapter(list = statementViewModel.list.value ?: ArrayList())

            list_statement.apply {
                adapter = myAdapter
                layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            }

            statementViewModel.list.observe(this@StatementFragment, Observer {
                myAdapter.updateList(it)
            })

            if (statementViewModel.list.value?.isEmpty() ?: true)
                iStatementPresenterInput.loadStatement()
        }
    }

    override fun resultStatement(listStatement: MutableList<Statement>) {
        statementViewModel.list.postValue(listStatement)
    }
}
