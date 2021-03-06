package signature

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    print("Enter name and surname: ")
    val name = scanner.next().toUpperCase()
    val surname = scanner.next().toUpperCase()
    scanner.nextLine()
    print("Enter person's status: ")
    val status = scanner.nextLine()

    val font = Font.makeMap("C:/fonts/roman.txt")

    var nameLength = name.length - 1 // This is spaces
    for (c in name) nameLength += font.map[c]?.length ?: 0
    var surnameLength = surname.length - 1 // This is spaces
    for (c in surname) surnameLength += font.map[c]?.length ?: 0
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

        for (c in name) print("${font.map[c]?.line?.get(lineNum) ?: ""} ") // + extra space
        print(" ".repeat(5))    // one space goes with the letter
        for (c in surname) print("${font.map[c]?.line?.get(lineNum) ?: ""} ") // + extra space

        println(" *".padStart(padNameEnd + 2)) // one space goes with the letter
    }
    // Status
    print("*  ".padEnd(3 + padStatusStart))
    print(status)
    println("  *".padStart(padStatusEnd + 3))

    println(starLine)
}




