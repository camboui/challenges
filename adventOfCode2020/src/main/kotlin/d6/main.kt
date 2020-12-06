package d6

fun main() {
    val result = getYesAnswersByGroup()
    println("Part 1 - Yes anyone count :${result.sumBy(Group::getAnyYesCount)}")
    println("Part 2 - Yes everyone count : ${result.sumBy(Group::getEveryYesCount)}")
}

class Group(private val yesAnswers: HashMap<Char, Int>, private var size: Int = 0) {
    fun getEveryYesCount(): Int {
        return yesAnswers.values.sumBy { if (it == size) 1 else 0 }
    }

    fun getAnyYesCount(): Int {
        return yesAnswers.size
    }

    fun addPersonAnswer(answer: String) {
        if (answer.isNotBlank()) {
            answer.toCharArray().forEach {
                yesAnswers[it] = yesAnswers.getOrDefault(it, 0) + 1
            }
            size++
        }
    }
}

fun getYesAnswersByGroup(): MutableList<Group> {
    val lines = FileReader.readFile("/d6/input")
    val result = mutableListOf<Group>()
    var currentGroup = Group(hashMapOf(), 0)

    lines.forEachIndexed { index, value ->
        currentGroup.addPersonAnswer(value)
        // last linebreak is read as EOF => handle this case
        if (value.isBlank() || index == lines.size - 1) {
            result.add(currentGroup)
            currentGroup = Group(hashMapOf(), 0)
        }
    }
    return result
}
