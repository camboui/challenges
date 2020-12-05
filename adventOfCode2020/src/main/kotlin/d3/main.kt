package d3

const val tree = '#'

fun main() {
    part1()
    part2()
}

fun part1() {
    val lines = FileReader.readFile("/input-day3")
    val treeCount = countTrees(lines, 3, 1)
    println("Trees : $treeCount")
}

fun part2() {
    val lines = FileReader.readFile("/input-day3")
    val treeCounts = listOf(
        countTrees(lines, 1, 1),
        countTrees(lines, 3, 1),
        countTrees(lines, 5, 1),
        countTrees(lines, 7, 1),
        countTrees(lines, 1, 2)
    )
    // Use BigDecimal or it will overflow int
    println(
        "Trees ${treeCounts.joinToString(" * ")} = " +
                treeCounts.map(Int::toBigDecimal).reduce { acc, i -> acc.multiply(i) }.toPlainString()
    )
}

fun countTrees(lines: List<String>, stepX: Int, stepY: Int): Int {
    val maxX = lines.first().length
    val maxY = lines.size
    var treeCount = 0
    for (y in stepY until maxY step stepY) {
        val row = lines[y]
        val x = ((y / stepY)/* = nb of y iterations*/ * stepX) % maxX
        if (row[x] == tree) {
            treeCount++
        }
    }
    return treeCount
}
