package aoc.day7
import java.nio.file.Paths


fun main() {
    val commands = input.split('$').filter{ line -> !line.isBlank() }
    val filesystem = mutableMapOf<String, Int>()
    val dirs = mutableListOf<String>()
    commands.map{ line -> line.split('\n') }.forEach { command -> run {
        val (cmd) = command.take(1)
        val output = command.drop(1).filter{ line -> !line.isBlank() }
        when {
            cmd.startsWith(" cd ") -> {
                val dir = cmd.split(" ").last()
                if (dir == "..") {
                    dirs.removeLast()
                } else {
                    dirs.add(dir)
                }
            }
            cmd.startsWith(" ls") -> output.forEach {
                val (size, _) = it.split(" ")
                if (size.toIntOrNull() != null) {
                    var path = "/"
                    dirs.forEach {
                        path = Paths.get(path, it).toString()
                        filesystem[path] = filesystem.getOrDefault(path, 0) + size.toInt()
                    }
                }
            }
        }
    }}
    val sizePart1 = filesystem.values.filter{ it <= 100000 }.sum()
    println("Part 1: ${sizePart1}")

    val totalSize = filesystem.getValue("/")
    val sizeNeeded = totalSize - (70000000 - 30000000)

    val smallest = filesystem.values.filter{ it >= sizeNeeded }.min()

    println("Part 2: ${smallest}")

}