package com.steppi.steppifitness.fcm

import java.util.concurrent.atomic.AtomicInteger

object STNotificationID {
    private val c =
        AtomicInteger(0)
    val id: Int
        get() = c.incrementAndGet()
}
