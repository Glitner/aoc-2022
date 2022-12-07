package day6

import java.io.File

val lines = File("input.txt").readLines()[0]

// part 1
for (i in 0..lines.length) {
    val distinct = lines
        .toCharArray()
        .slice(i..i + 3)
        .distinct()
        .size == 4

    if (distinct) {
        println("part 1: ${i + 1 + 3}")
        break
    }
}

// part 2
for (i in 0..lines.length) {
    val distinct = lines
        .toCharArray()
        .slice(i..i + 13)
        .distinct()
        .size == 14

    if (distinct) {
        println("part 2: ${i + 1 + 13}")
        break
    }
}
