package io.github.shkschneider.awesome.core

@Target(AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
annotation class Event(val reason: String)
