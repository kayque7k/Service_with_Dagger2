package com.wolfdevelloper.estudo.statement

import androidx.lifecycle.MutableLiveData
import com.wolfdevelloper.estudo.entity.ListStatement
import com.wolfdevelloper.estudo.interactor.remote.service.statement.ServiceStatement
import com.wolfdevelloper.estudo.utils.DateTime
import com.wolfdevelloper.estudo.viewmodel.Statement
import javax.inject.Inject
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class StatementPresenter(
    val iStatementPresenterOutput: StatementContract.StatementPresenterOutput
) :
    StatementContract.StatementPresenterInput, StatementContract.StatementInteractorOutput {

    @Inject
    lateinit var iStatementInteractorInput: StatementContract.StatementInteractorInput

    init {
        DaggerStatementComponents
            .builder()
            .statementModule(StatementModule(statementPresenter = this))
            .build()
            .inject(this)
    }

    override fun loadStatement() = iStatementInteractorInput.loadStatement(1)


    override fun resultStatement(listStatement: ListStatement?) {

        if (listStatement?.error?.code != 0) {
            return
        }

        val list = listStatement.statementList
            .sortedByDescending { DateTime.conversor(it.date) }
            .map { Statement().mapper(it) }
            .toMutableList()

        iStatementPresenterOutput.resultStatement(list)
    }

    override fun cleanAndAddStatement(listStatement: MutableList<Statement>) {
        iStatementPresenterOutput.getStatement().clear()
        iStatementPresenterOutput.getStatement().addAll(listStatement)
    }
}