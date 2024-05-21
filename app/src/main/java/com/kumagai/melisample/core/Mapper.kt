package com.kumagai.melisample.core

interface Mapper<F, T> {
    fun  mapFrom(from: F): T
}