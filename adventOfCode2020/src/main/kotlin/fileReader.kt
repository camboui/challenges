import java.io.File

class FileReader {

    companion object {
        fun readFile(fileName: String): List<String> {
            return File(this::class.java.getResource(fileName).toURI()).readLines()
        }
    }

}
