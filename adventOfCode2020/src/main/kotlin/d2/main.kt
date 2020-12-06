package d2

fun main() {
    part1()
    part2()
}

fun part1() {

    val lines = FileReader.readFile("/d2/input")
    val result = lines.filter { line ->
        val (min, max, char, pwd) = line.replace(":", "").replace("-", " ").split(" ")
        val count = pwd.count { c -> c == char.first() }
        count >= min.toInt() && count <= max.toInt()
    }.count()

    println(result)
}

fun part2() {

    val lines = FileReader.readFile("/d2/input")

    val result = lines.filter { line ->
        val (min, max, char, pwd) = line.replace(":", "").replace("-", " ").split(" ")
        val first = pwd.length >= min.toInt() &&  pwd[min.toInt()-1] == char.first()
        val second = pwd.length >= max.toInt() &&  pwd[max.toInt()-1] == char.first()
        first.xor(second)
    }.count()

    println(result)
}
