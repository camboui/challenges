package fr.vco.codecontest.battledev16

import java.util.*
import java.math.*
import kotlin.text.*

object Exo1 {


    // Copy only the main method in the Isograd platform
    fun main() {
        val input = generateSequence(::readLine)
        val lines = input.toList()
        val p = Regex("^.*[0-9]{5}\$")

        println(lines.filter { l -> p.matches(l) }.count())
    }
}
