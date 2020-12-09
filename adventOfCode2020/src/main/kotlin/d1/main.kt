package d1

fun main() {
    findXNumberSumEqual2020(2)
    findXNumberSumEqual2020(3)
    // 4 -> 64684950 Combination so recursion is really bad !
}

fun findXNumberSumEqual2020(x: Int) {

    val lines = FileReader.readFile("/d1/input").map(String::toDouble)

    val result = findXNumberSumEqualY(x, 2020.0, mutableListOf(), lines, mutableListOf())
    val printedResult = result.first()

    println("Results : ${result.size}")
    println(" ${printedResult.joinToString(" * ")} = ${printedResult.reduce { acc, i -> acc * i }.toBigDecimal().toPlainString()}")
}

fun findXNumberSumEqualY(x: Int, targetEqual: Double, combination: List<Double>, currentList: List<Double>, results: MutableList<List<Double>>): List<List<Double>> {

    currentList.forEachIndexed { index, item ->

        if (x == 0) {
            if (combination.sum() == targetEqual) {
                results.add(combination)
            }
            return results
        }
        val newCurrentList = currentList.toMutableList()
        newCurrentList.removeAt(index)
        val newCombination = combination.toMutableList()
        newCombination.add(item)
        findXNumberSumEqualY(x - 1, targetEqual, newCombination, newCurrentList, results)
    }
    return results
}
