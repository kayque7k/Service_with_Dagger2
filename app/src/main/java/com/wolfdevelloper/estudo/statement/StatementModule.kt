package com.wolfdevelloper.estudo.statement

import com.wolfdevelloper.estudo.interactor.remote.service.statement.*
import dagger.Module
import dagger.Provides

@Module
class StatementModule(
    val statementFragment: StatementFragment? = null,
    val statementPresenter: StatementPresenter? = null
) {

    @Provides
    @HttpApi
    fun providerIServiceStatement(): IServiceStatement = HttpServiceStatement()

    @Provides
    fun providerStatementInteractorInput(
        iServiceStatement: IServiceStatement
    ): StatementContract.StatementInteractorInput = StatementInteractor(statementPresenter!!, iServiceStatement)

    @Provides
    fun providerStatementPresenterInput(): StatementContract.StatementPresenterInput = StatementPresenter(statementFragment!!)

}