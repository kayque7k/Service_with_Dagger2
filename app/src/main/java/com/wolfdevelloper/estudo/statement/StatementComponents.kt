package com.wolfdevelloper.estudo.statement

import dagger.Component

@Component(modules = [StatementModule::class])
interface StatementComponents {

    fun inject(statementFragment: StatementFragment)
    fun inject(statementPresenter: StatementPresenter)
    fun inject(statementInteractor: StatementInteractor)
}