package com.ioinsiders.marvelheroes.models

sealed class Filter {
    class gender(val value: String) : Filter()
    class minAge(val value: Int) : Filter()
    class maxAge(val value: Int) : Filter()
    class page(val value: Int) : Filter()
    class size(val value: Int) : Filter()
    class radius(val value: Float): Filter()
}
