package fr.vco.codecontest.battledev16

object Exo3 {


    // Copy only the main method in the Isograd platform
    fun main() {
        var map = hashMapOf<Int, MutableList<Int>>()


        val input = generateSequence(::readLine)
        val lines = input.toList()
        val result = mutableListOf<Int>()

        val agents = lines.subList(1, lines.size)
        map.put(0, mutableListOf())
        agents.forEach {
            val splitted = it.split(" ")
            // b > a
            val a = splitted[0].toInt()
            val b = splitted[1].toInt()

            val existingList = map.get(b)
            if (existingList != null) {
                existingList.add(a)
            } else {
                map.putIfAbsent(b, mutableListOf(a))
            }

        }
        for (i in 0..10) {
            result.add(countSub(map, 0, i))
        }
        print(result.subList(0,10).joinToString(" "))
    }

    fun countSub(map: HashMap<Int, MutableList<Int>>, currentValue: Int, inc: Int): Int {
        return if (inc == 0) {
            1
        } else {
            val list = map.getOrDefault(currentValue, mutableListOf())
             list.map { countSub(map, it, inc - 1) }.sum()
        }
    }

}

