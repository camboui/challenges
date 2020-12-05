package d5

import java.lang.Exception

fun main() {
    part1()
    part2()
}

fun part1() {
    val highestSeatId = getOccupiedSeats().maxOrNull()
    println("Highest seat ID : $highestSeatId")
}

fun part2() {
    val planeSeats = (1..(128 * 8)).toMutableSet().minus(getOccupiedSeats())
    val remainingSeats = planeSeats.minus(getOccupiedSeats())
    val mySeat = remainingSeats.filter { !remainingSeats.contains(it - 1) && !remainingSeats.contains(it + 1) }
    println("My seat ID : $mySeat")
}

fun getOccupiedSeats(): List<Int> {
    val lines = FileReader.readFile("/input-day5")
    return lines.map {
        val row = findSeat(0, 127, it.substring(0, 7).toMutableList())
        val seat = findSeat(0, 7, it.substring(7, it.length).toMutableList())
        //println("row : $row - seat : $seat - ID :${row * 8 + seat}")
        row * 8 + seat
    }
}

fun findSeat(start: Int, end: Int, entries: MutableList<Char>): Int {
    if (entries.isEmpty()) {
        return start
    }
    val half = (end - start) / 2
    return when (entries.removeAt(0)) {
        'F', 'L' -> findSeat(start, end - half - 1, entries)
        'B', 'R' -> findSeat(start + half + 1, end, entries)
        else -> throw Exception("Unhandled entry")
    }
}
