package `lab2(nhap)`

class WorkWithData(private val data: HashMap<Address, Int>) {
    fun printDublicateEntry() {
        println("Адресс и количество повторений: ")
        data.forEach {
            println("${it.key} Повторений ${it.value}")
        }
    }

    fun printCountHouse() {
        val countHouseCity = hashMapOf<String, CountFloor>()
        for (address in data) {
            if (!countHouseCity.containsKey(address.key.city))
                countHouseCity[address.key.city] = CountFloor()
            when (address.key.floor) {
                1 -> countHouseCity[address.key.city]?.one = countHouseCity[address.key.city]?.one!! + 1
                2 -> countHouseCity[address.key.city]?.two = countHouseCity[address.key.city]?.two!! + 1
                3 -> countHouseCity[address.key.city]?.three = countHouseCity[address.key.city]?.three!! + 1
                4 -> countHouseCity[address.key.city]?.four = countHouseCity[address.key.city]?.four!! + 1
                5 -> countHouseCity[address.key.city]?.five = countHouseCity[address.key.city]?.five!! + 1
            }
        }
        countHouseCity.forEach {
            println("Выводим количество 1,2,3,4,5 этажных домов в городе ${it.key}")
            println("1 этажных домов: ${it.value.one}")
            println("2 этажных домов: ${it.value.two}")
            println("3 этажных домов: ${it.value.three}")
            println("4 этажных домов: ${it.value.four}")
            println("5 этажных домов: ${it.value.five}")
        }

    }
}