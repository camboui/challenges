package d9

import d1.findXNumberSumEqualY

fun main() {
    val lines = FileReader.readFile("/d9/input").map(String::toDouble)

    val invalidNumber = getInvalidXmas(25, lines)
    println("Part 1 : invalid is ${invalidNumber.toBigDecimal().toPlainString()}")

    val invalidContiguousList = getContiguousNumbersEqualsX(invalidNumber, lines)
    val sumFirstAndLast = invalidContiguousList.minOrNull()!! + invalidContiguousList.maxOrNull()!!
    println("Part 2 : invalid is ${sumFirstAndLast.toBigDecimal().toPlainString()}")
}

fun getInvalidXmas(preambleSize: Int, numbers: List<Double>): Double {
    val validators = numbers.subList(0, preambleSize).toMutableList()
    numbers.subList(preambleSize, numbers.size)
        .forEach {
            val validCombinations = findXNumberSumEqualY(2, it, mutableListOf(), validators, mutableListOf())
            if (validCombinations.isEmpty()) {
                return it
            }
            validators.removeFirst()
            validators.add(it)
        }
    return -1.0
}

fun getContiguousNumbersEqualsX(x: Double, numbers: List<Double>): List<Double> {
    for (i in numbers.indices) {
        for (j in i until numbers.size) {
            val subList = numbers.subList(i, j)
            if (subList.sum() == x) {
                return subList
            }
        }
    }
    return listOf()
}
