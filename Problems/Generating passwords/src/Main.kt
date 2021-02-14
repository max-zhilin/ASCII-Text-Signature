fun main() {
    val scanner = java.util.Scanner(System.`in`)
    val a = scanner.nextInt()
    val b = scanner.nextInt()
    val c = scanner.nextInt()
    val n = scanner.nextInt()
    val pass = buildString(n) {
        var ch = 'A'
        for (i in 1..a) {
            append(ch)
            if (ch >= 'Z') ch = 'A' else ch++
        }
        ch = 'a'
        for (i in 1..b) {
            append(ch)
            if (ch >= 'z') ch = 'a' else ch++
        }
        ch = '0'
        for (i in 1..c) {
            append(ch)
            if (ch >= '9') ch = '0' else ch++
        }

        if (length > 0 && last() == '0') ch = '1' else ch = '0'
        for (i in 1..n - a - b - c) {
            append(ch)
            if (ch >= 'z') ch = '0' else ch++
        }

    }

    println(pass)

}