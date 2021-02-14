package signature

import java.io.File
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    print("Enter name and surname: ")
    val name = scanner.next().toUpperCase()
    val surname = scanner.next().toUpperCase()
    scanner.nextLine()
    print("Enter person's status: ")
    val status = scanner.nextLine()

    val fontMap = makeMap()

    var nameLength = name.length - 1 // This is spaces
    for (c in name) nameLength += fontMap[c]?.size ?: 0
    var surnameLength = surname.length - 1 // This is spaces
    for (c in surname) surnameLength += fontMap[c]?.size ?: 0
    //println("$nameLength $surnameLength")
    val fullNameLength = nameLength + 6 + surnameLength
    val lineWidth = maxOf(
        1 + 2 + fullNameLength + 2 + 1,
        1 + 2 + status.length + 2 + 1
    )

    val padNameStart: Int
    val padNameEnd: Int
    val padStatusStart: Int
    val padStatusEnd: Int
    if (fullNameLength > status.length) {
        padNameStart = 0
        padNameEnd = 0
        padStatusStart = (fullNameLength - status.length) / 2
        padStatusEnd = padStatusStart + (fullNameLength - status.length) % 2 // +1 if odd
    } else {
        padNameStart = (status.length - fullNameLength) / 2
        padNameEnd = padNameStart + (status.length - fullNameLength) % 2 // +1 if odd
        padStatusStart = 0
        padStatusEnd = 0
    }

    val starLine = String(CharArray(lineWidth) {'*'})
    println(starLine)
    // Name
    for (lineNum in 0..2) {
        print("*  ".padEnd(3 + padNameStart))

        for (c in name) print("${fontMap[c]?.line?.get(lineNum) ?: ""} ") // + extra space
        print(" ".repeat(5))    // one space goes with the letter
        for (c in surname) print("${fontMap[c]?.line?.get(lineNum) ?: ""} ") // + extra space

        println(" *".padStart(padNameEnd + 2)) // one space goes with the letter
    }
    // Status
    print("*  ".padEnd(3 + padStatusStart))
    print(status)
    println("  *".padStart(padStatusEnd + 3))

    println(starLine)
}

fun makeMap(): MutableMap<Char, Letter> {
    val fileReader = Scanner(File("C:/fonts/roman.txt"))
    val face = """
        ____ ___  ____ ___  ____ ____ ____ _  _ _  _ _  _ _    _  _ _  _ ____ ___  ____ ____ ____ ___ _  _ _  _ _ _ _ _  _ _   _ ___ 
        |__| |__] |    |  \ |___ |___ | __ |__| |  | |_/  |    |\/| |\ | |  | |__] |  | |__/ [__   |  |  | |  | | | |  \/   \_/    / 
        |  | |__] |___ |__/ |___ |    |__] |  | | _| | \_ |___ |  | | \| |__| |    |_\| |  \ ___]  |  |__|  \/  |_|_| _/\_   |    /__
    """.trimIndent().lines()
    val faceLength = maxOf(face[0].length, face[1].length, face[2].length)

    val map: MutableMap<Char, Letter> = mutableMapOf()

    var start = 0
    var letter = 'A'
    for (i in 0 until faceLength) {
        if (face[0][i] == ' ' && face[1][i] == ' ' && face[2][i] == ' ') {
            map += (letter to Letter(
                i - start,
                face[0].substring(start, i),
                face[1].substring(start, i),
                face[2].substring(start, i)))
            start = i + 1
            letter++
        }
    }
    map += (letter to Letter(
        faceLength - start,
        face[0].substring(start),
        face[1].substring(start),
        face[2].substring(start)))

//    map.forEach() {
//        println("${it.key} ${it.value.size}")
//        println(it.value.line[0])
//        println(it.value.line[1])
//        println(it.value.line[2])
//    }

    return map

}

class Letter(val size: Int, line1: String, line2: String, line3: String) {
    val line = listOf(
        line1,
        line2,
        line3
    )
}