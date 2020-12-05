package fr.vco.codecontest.battledev16

import java.util.*
import java.math.*
import kotlin.text.*

object Exo2 {

    // Copy only the main method in the Isograd platform
    fun main() {
        val input = generateSequence(::readLine)
        val lines = input.toList()

        val p = lines.subList(1, lines.size).filter { t ->
            val array = t.split(':')
            array[0].toInt() >= 20 || array[0].toInt() < 8
        }.count()

        if (p >= (lines.size - 1) / 2) {
            println("SUSPICIOUS ")
        } else {
            println("OK")
        }

    }
}
