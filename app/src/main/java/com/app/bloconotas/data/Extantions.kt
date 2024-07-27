package com.app.bloconotas.data
import java.security.Key
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun converteMillisSegundo(millis: Long): Date {
    return Date(millis)
}
fun formatDate(date: Date): String {
    val format = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return format.format(date)
}
