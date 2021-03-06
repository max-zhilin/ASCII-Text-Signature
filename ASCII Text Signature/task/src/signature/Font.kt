package signature

import java.io.File
import java.util.*

class Font(filePath: String) {
    private val map: Map<Char, Letter>
    val height: Int

    init {

        val map: MutableMap<Char, Letter> = mutableMapOf()

        val fileReader = Scanner(File(filePath), "US-ASCII")
        height = fileReader.nextInt()
        val symbolCount = fileReader.nextInt()
        repeat(symbolCount) {
            val letter = fileReader.next().first()
            val width = fileReader.nextInt()
            assert(fileReader.nextLine().isBlank()) { "not blank" }   // drop EOL

            val lines = mutableListOf<String>()
            for (i in 0 until height) {
                val line = fileReader.nextLine()
                assert(line.length == width) { "${line.length} != $width" }
                lines.add(line)
            }
            map += (letter to Letter(width, lines))
        }
        // add space
        val a = map['a']
        if (a != null) {
            map += (' ' to a.toSpace())
        }

        this.map = map

        // --------
//        this.map.forEach { entry ->
//            println("${entry.key} ${entry.value.width}")
//            repeat (entry.value.line.size) { println(entry.value.line[it]) }
//        }
    }

    fun lineLength(s: String): Int {
        var sum = 0
        for (c in s) sum += map[c]?.width ?: 0
        return sum
    }

    fun getLine(s: String, lineNum: Int): String {
        var result = ""
        for (c in s) result += map[c]?.line?.get(lineNum) ?: ""
        return result
    }

}

class Letter(val width: Int, val line: List<String>) {

    fun toSpace(): Letter {
        val lines = mutableListOf<String>()
        repeat (line.size) {
            lines.add(" ".repeat(width))
        }
        return Letter(width, lines)
    }
}
