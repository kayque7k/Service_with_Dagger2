package com.wolfdevelloper.estudo.statement

import androidx.lifecycle.LiveData
import com.wolfdevelloper.estudo.entity.ListStatement
import com.wolfdevelloper.estudo.viewmodel.Statement

interface StatementContract {

    interface StatementPresenterInput {
        fun loadStatement()
        fun cleanAndAddStatement(listStatement: MutableList<Statement>)
    }

    interface StatementInteractorInput {
        fun loadStatement(id: Int)
    }

    interface StatementPresenterOutput {
        fun resultStatement(listStatement: MutableList<Statement>)
        fun getStatement(): MutableList<Statement>
    }

    interface StatementInteractorOutput {
        fun resultStatement(listStatement: ListStatement?)
    }
}