package lab2

fun main() {
    do {
        println("Enter the path to the file or 'exit' to shut down: ")
        val path = readln()
        if (path != "exit") {
            try {
                val start = System.currentTimeMillis()
                val data = ReadFiles(path)
                val parseData = ParseData()
                parseData.duplicate(data.addresses)
                parseData.storeyBuildings(data.addresses)
                val end = System.currentTimeMillis()
                println("Processing time: ${end - start} milliseconds.")
            } catch (e: Exception) {
                println(e.message)
            }
        }
    } while (path != "exit")
}