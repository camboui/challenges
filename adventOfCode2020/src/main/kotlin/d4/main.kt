package d4

fun main() {
    validatePassports(
        mapOf(
            "byr" to Regex(".*"),
            "iyr" to Regex(".*"),
            "eyr" to Regex(".*"),
            "hgt" to Regex(".*"),
            "hcl" to Regex(".*"),
            "ecl" to Regex(".*"),
            "pid" to Regex(".*")
        )
    )
    validatePassports(
        mapOf(
            "byr" to Regex("19[2-9][0-9]|200[0-2]"),
            "iyr" to Regex("201[0-9]|2020"),
            "eyr" to Regex("202[0-9]|2030"),
            "hgt" to Regex("(1[5-8][0-9]|19[0-3])cm|(59|6[0-9]|7[0-6])in"),
            "hcl" to Regex("^#([0-9]|[a-f]){6}$"),
            "ecl" to Regex("amb|blu|brn|gry|grn|hzl|oth"),
            "pid" to Regex("[0-9]{9}")
        )
    )
}

fun validatePassports(requiredPassportRules: Map<String, Regex>) {
    val lines = FileReader.readFile("/input-day4")
    val currentFields = mutableListOf<String>()
    var countValid = 0
    lines.forEach { line ->
        line.split(" ").forEach {
            if (line.isEmpty()) {
                if (currentFields.containsAll(requiredPassportRules.keys)) {
                    countValid++
                }
                currentFields.clear()
            } else {
                val (key, value) = it.split(':')
                if (requiredPassportRules.containsKey(key) && requiredPassportRules[key]!!.matches(value)) {
                    currentFields.add(key)
                }
            }
        }
    }
    println("Valid passports $countValid")
}
