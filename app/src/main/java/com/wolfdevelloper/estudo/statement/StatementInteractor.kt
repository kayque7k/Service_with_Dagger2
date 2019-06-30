package com.wolfdevelloper.estudo.statement

import com.wolfdevelloper.estudo.components.notNull
import com.wolfdevelloper.estudo.entity.ListStatement
import com.wolfdevelloper.estudo.interactor.remote.service.statement.IServiceStatement
import com.wolfdevelloper.estudo.interactor.remote.service.statement.ServiceStatement
import com.wolfdevelloper.estudo.interactor.remote.service.statement.TestApi
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class StatementInteractor(
    val iStatementInteractorOutput: StatementContract.StatementInteractorOutput,
    val iServiceStatement: IServiceStatement
) :
    StatementContract.StatementInteractorInput {

    override fun loadStatement(id: Int) =
        iServiceStatement.getStatement(id,
            sucess = {
                if (it.code() == 200) {
                    val list = it.body()
                    if (list.notNull()) {
                        if (list.error.code == 0) {
                            iStatementInteractorOutput.resultStatement(list)
                        }
                    }
                }
            },
            failure = {

            })

}