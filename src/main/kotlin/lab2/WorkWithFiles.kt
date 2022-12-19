package lab2

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.File
import java.nio.file.Files
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.io.path.Path

class ReadFiles(path: String) {
    val addresses: HashMap<Address, Int> = hashMapOf()

    init {
        if (path.substring(path.length - 4) == ".csv")
            readFileCSV(path)
        else if (path.substring(path.length - 4) == ".xml")
            readFileXML(path)
        else
            throw Exception("Wrong path.")
    }

    private fun readFileXML(path: String) {
        val builderFactory = DocumentBuilderFactory.newInstance()
        val docBuilder = builderFactory.newDocumentBuilder()
        val doc = docBuilder.parse(File(path))
        val elements = doc.getElementsByTagName("item")
        for (i in 0 until elements.length) {
            val attributes = elements.item(i).attributes
            val city = attributes.getNamedItem("city").nodeValue
            val street = attributes.getNamedItem("street").nodeValue
            val house = attributes.getNamedItem("house").nodeValue.toInt()
            val storey = attributes.getNamedItem("floor").nodeValue.toInt()
            val newAddress = Address(city, street, house, storey)
            if (!addresses.containsKey(newAddress))
                addresses[newAddress] = 1
            else
                addresses[newAddress]?.let { it1 -> addresses.replace(newAddress, it1, it1 + 1) }
        }
    }

    private fun readFileCSV(path: String) {
        val reader = Files.newBufferedReader(Path(path))
        val csvParser = CSVParser(reader, CSVFormat.newFormat(';'))
        csvParser.records.drop(1).forEach {
            val newAddress = Address(it[0], it[1], it[2].toInt(), it[3].toInt())
            if (!addresses.containsKey(newAddress))
                addresses[newAddress] = 1
            else
                addresses[newAddress]?.let { it1 -> addresses.replace(newAddress, it1, it1 + 1) }
        }
    }
}

class ParseData {
    fun duplicate(addresses: HashMap<Address, Int>) {
        println("Addresses and repetition: ")
        addresses.forEach {
            println("${it.key}. Repetition: ${it.value}")
        }
    }

    fun storeyBuildings(addresses: HashMap<Address, Int>) {
        val storeyBuildings: HashMap<String, StoreyBuildings> = hashMapOf()
        addresses.forEach {
            if (!storeyBuildings.containsKey(it.key.city)) {
                storeyBuildings[it.key.city] = StoreyBuildings()
            } else {
                when (it.key.storey) {
                    1 -> storeyBuildings[it.key.city]?.one = storeyBuildings[it.key.city]?.one?.plus(1)!!
                    2 -> storeyBuildings[it.key.city]?.two = storeyBuildings[it.key.city]?.two?.plus(1)!!
                    3 -> storeyBuildings[it.key.city]?.three = storeyBuildings[it.key.city]?.three?.plus(1)!!
                    4 -> storeyBuildings[it.key.city]?.four = storeyBuildings[it.key.city]?.four?.plus(1)!!
                    5 -> storeyBuildings[it.key.city]?.five = storeyBuildings[it.key.city]?.five?.plus(1)!!
                }
            }
        }

        storeyBuildings.forEach {
            println("In ${it.key} city there are: ")
            println("${it.value.one} 1-storey buildings.")
            println("${it.value.two} 2-storey buildings.")
            println("${it.value.three} 3-storey buildings.")
            println("${it.value.four} 4-storey buildings.")
            println("${it.value.five} 5-storey buildings.")
        }
    }
}