package d7

val bagList = hashMapOf<String, Bag>()

fun main() {
    val lines = FileReader.readFile("/d7/input")
    lines.forEach {
        val splittedRule = it.split(Regex("contain|bag.(s|,|\\.)?")).map(String::trim).filter(String::isNotBlank)

        val bagColor = splittedRule[0]

        val bag = Bag(bagColor)
        if (!splittedRule[1].contains("no other")) {
            splittedRule.subList(1, splittedRule.size).forEach { content ->
                val (count, color) = content.split(Regex(" "), 2)
                bag.children.add(Pair(count.toInt(), color))
            }
        }
        bagList.putIfAbsent(bagColor, bag)
    }
    val containingShinyGold =
        bagList.values.flatMap { countAtLeastContainedBag("shiny gold", it.color, mutableSetOf(), setOf()) }
    println("Part1 : ${containingShinyGold.toSet().size}")
    println("Part2 : ${countContainedBags("shiny gold")}")
}

// TODO rename this
fun countAtLeastContainedBag(targetColor: String, color: String, result: MutableSet<String>, readColors: Set<String>):
        Set<String> {
    val currentBag = bagList.getOrDefault(color, Bag())
    if (currentBag.color == targetColor) {
        result.addAll(readColors)
    } else {
        result.addAll(currentBag.children.map {
            val currentReadColors = readColors.toMutableSet()
            currentReadColors.add(currentBag.color)
            countAtLeastContainedBag(targetColor, it.second, result, currentReadColors)
        }.flatten())
    }
    return result
}

fun countContainedBags(bagColor: String): Int {
    val currentBag = bagList.getOrDefault(bagColor, Bag())

    return if (currentBag.children.isNotEmpty()) {
        currentBag.children.map { it.first * (1 + countContainedBags(it.second)) }.sum()
    } else {
        0
    }
}

class Bag(val color: String = "", var children: MutableList<Pair<Int, String>> = mutableListOf())

