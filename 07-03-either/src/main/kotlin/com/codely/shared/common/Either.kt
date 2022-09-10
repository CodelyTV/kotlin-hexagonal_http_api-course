package com.codely.shared.common

sealed class Either<L, R> {
    abstract fun <ML, MR> flatMap(leftF: (L) -> Either<ML, MR>, rightF: (R) -> Either<ML, MR>): Either<ML, MR>
}

data class Right<L, R>(val value: R) : Either<L, R>() {

    override fun <ML, MR> flatMap(leftF: (L) -> Either<ML, MR>, rightF: (R) -> Either<ML, MR>) = rightF(value)
}

data class Left<L, R>(
    val value: L
) : Either<L, R>() {

    override fun <ML, MR> flatMap(leftF: (L) -> Either<ML, MR>, rightF: (R) -> Either<ML, MR>) = leftF(value)
}
