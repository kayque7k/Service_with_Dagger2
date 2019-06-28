package com.wolfdevelloper.estudo.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.wolfdevelloper.estudo.BR
import com.wolfdevelloper.estudo.entity.Statement
import com.wolfdevelloper.estudo.utils.DateTime
import com.wolfdevelloper.estudo.utils.MoneyMask

class Statement : BaseObservable() {

    @Bindable
    var title: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @Bindable
    var desc: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.desc)
        }

    @Bindable
    var date: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.date)
        }

    @Bindable
    var value: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.value)
        }

    fun mapper(statement: Statement): com.wolfdevelloper.estudo.viewmodel.Statement {
        this.title = statement.title
        this.desc = statement.desc
        this.date = DateTime.mask(statement.date)
        this.value = MoneyMask.mask(statement.value)

        return this
    }
}