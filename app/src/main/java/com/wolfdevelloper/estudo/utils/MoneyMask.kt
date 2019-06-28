package com.wolfdevelloper.estudo.utils

import java.text.NumberFormat
import java.util.*

object MoneyMask {

    @JvmStatic
    fun mask(cash: Double): String {
        var money = cash

        if (java.lang.Double.isNaN(money))
            money = 0.0

        val ptBR = Locale("pt", "BR")
        return NumberFormat.getCurrencyInstance(ptBR).format(money)
    }
}