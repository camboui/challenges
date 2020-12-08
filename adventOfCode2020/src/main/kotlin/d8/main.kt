package d8

fun main() {
    val instructions = FileReader.readFile("/d8/input").map {
        val splitted = it.split(" ")
        Pair(splitted[0], splitted[1].toInt())
    }
    val gameConsole = StateMachine(instructions)
    gameConsole.run()
    println("Part 1 : run loop stopped with acc = ${gameConsole.acc}")

    // This is bruteforcing, we could sum jmp index while running through execution to find any loop
    instructions.forEachIndexed { index: Int, pair: Pair<String, Int> ->
        if (pair.first != "acc") {
            val changedInstructions = instructions.toMutableList()
            changedInstructions[index] = Pair(if (pair.first == "nop") "jmp" else "nop", pair.second)
            val fixedGameConsole = StateMachine(changedInstructions)
            fixedGameConsole.run()
            if (fixedGameConsole.isBootTerminated()) {
                println("Part 2 : fixed run loop stopped with acc = ${fixedGameConsole.acc}")
                return@forEachIndexed
            }
        }
    }
}

class StateMachine(private val instructions: List<Pair<String, Int>>) {
    var acc: Int = 0
    var currentIndex: Int = 0
    var loopingIndex = 0
    private val executionMap: HashMap<Int, Int> = hashMapOf()

    fun run() {
        while (currentIndex < instructions.size) {
            val instuctionToRun = instructions[currentIndex]
            executionMap.putIfAbsent(currentIndex, 0)
            executionMap[currentIndex] = executionMap[currentIndex]!! + 1
            if (executionMap[currentIndex]!! > 1) {
                return
            }
            loopingIndex = currentIndex
            when (instuctionToRun.first) {
                "acc" -> {
                    acc += instuctionToRun.second
                    currentIndex++
                }
                "jmp" -> currentIndex += instuctionToRun.second
                else -> currentIndex++
            }
        }
    }

    fun isBootTerminated(): Boolean {
        return currentIndex >= instructions.size
    }
}
