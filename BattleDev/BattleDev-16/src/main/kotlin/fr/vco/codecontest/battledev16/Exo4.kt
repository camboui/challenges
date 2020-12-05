package fr.vco.codecontest.battledev16

object Exo4 {

    // Copy only the main method in the Isograd platform
    fun main() {
        val input = generateSequence(::readLine)

        val lines = mutableListOf<String>("5 4",
                "11 22 33 44 55",
                "1 3",
                "0 1",
                "2 2",
                "2 4")

        var splitted = lines.get(0).split(" ")
        val n = splitted[0]
        val m = splitted[1]
        val key = lines.get(1).split(" ").map { it.toInt() }
        val op = lines.subList(2, lines.size)

        val result = mutableListOf<Int>()
        op.forEach {
            val pair = it.split(" ")
            val start = pair[0].toInt()
            val end = pair[1].toInt()
            if (start >= end) {
                result.add(key.get(start))
            } else {
                val mapped = key.subList(start, end + 1).reduce { acc, c -> acc.xor(c) }
                result.add(mapped)
            }
        }
        println(result.map { Integer.toBinaryString(it) }.joinToString(" "))
    }
}
