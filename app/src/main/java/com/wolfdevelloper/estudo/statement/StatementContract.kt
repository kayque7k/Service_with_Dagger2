package com.wolfdevelloper.estudo.statement

import androidx.lifecycle.LiveData
import com.wolfdevelloper.estudo.entity.ListStatement
import com.wolfdevelloper.estudo.viewmodel.Statement

class StatementContract {

    interface StatementPresenterInput {
        fun loadStatement()
    }

    interface StatementInteractorInput {
        fun loadStatement(id: Int)
    }

    interface StatementPresenterOutput {
        fun resultStatement(listStatement: MutableList<Statement>)
    }

    interface StatementInteractorOutput {
        fun resultStatement(listStatement: ListStatement?)
    }
}