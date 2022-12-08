package aoc.day8

fun main() {
    val trees = input.split('\n').map { it.map { it.digitToInt() } }
    val visible = trees.mapIndexed { index, it ->
        it.filterIndexed { filterIndex, tree ->
            val column = trees.map { it[filterIndex] }
            val row = trees[index].toList()
            val (up, down) = Pair(column.slice(0..index-1), column.slice(index+1..column.size-1))
            val (left, right) = Pair(row.slice(0..filterIndex-1), row.slice(filterIndex+1..row.size-1))

            listOf(up, down, left, right).any {
                it.isEmpty() || it.none { it >= tree }
            }
        }
    }
    println("Part 1: ${visible.flatten().sum()}")

    val trees2 = trees.mapIndexed { index, it ->
        it.mapIndexed { mapIndex, tree ->
            val column = trees.map { it[mapIndex] }
            val row = trees[index].toList()
            val (up, down) = Pair(column.slice(0..index-1).reversed(), column.slice(index+1..column.size-1))
            val (left, right) = Pair(row.slice(0..mapIndex-1).reversed(), row.slice(mapIndex+1..row.size-1))
            listOf(up, down, left, right).map {
                val id = it.indexOfFirst { it >= tree }
                val fallback = if (it.size == 0) 0 else it.size
                if (id == -1) fallback else id + 1
            }.reduce { acc, i -> acc * i }
        }
    }
    println("Part 2: ${trees2.flatten().max()}")
}