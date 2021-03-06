package signature

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    print("Enter name and surname: ")
    val name = scanner.next() + " " + scanner.next()
    scanner.nextLine()
    print("Enter person's status: ")
    val status = scanner.nextLine()

    val romanFont = Font("D:/Projects/roman.txt")
    val mediumFont = Font("D:/Projects/medium.txt")

    val nameLength = romanFont.lineLength(name)
    //println("nameLength $nameLength")
    val statusLength = mediumFont.lineLength(status)
    //println("statusLength $statusLength")

    val starLineWidth = maxOf(
        2 + 2 + nameLength + 2 + 2,
        2 + 2 + statusLength + 2 + 2
    )

    val padNameStart: Int
    val padNameEnd: Int
    val padStatusStart: Int
    val padStatusEnd: Int
    if (nameLength > statusLength) {
        padNameStart = 0
        padNameEnd = 0
        padStatusStart = (nameLength - statusLength) / 2
        padStatusEnd = padStatusStart + (nameLength - statusLength) % 2 // +1 if odd
    } else {
        padNameStart = (statusLength - nameLength) / 2
        padNameEnd = padNameStart + (statusLength - nameLength) % 2 // +1 if odd
        padStatusStart = 0
        padStatusEnd = 0
    }

    val starLine = String(CharArray(starLineWidth) {'8'})
    println(starLine)
    // Name
    for (lineNum in 0 until romanFont.height) {
        print("88  ".padEnd(4 + padNameStart))
        print(romanFont.getLine(name, lineNum))
        println("  88".padStart(padNameEnd + 4))
    }
    // Status
    for (lineNum in 0 until mediumFont.height) {
        print("88  ".padEnd(4 + padStatusStart))
        print(mediumFont.getLine(status, lineNum))
        println("  88".padStart(padStatusEnd + 4))
    }
    println(starLine)
}




