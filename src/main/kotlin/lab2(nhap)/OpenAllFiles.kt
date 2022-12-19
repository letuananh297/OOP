package `lab2(nhap)`

import org.w3c.dom.*
import java.io.File
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.w3c.dom.NamedNodeMap
import java.nio.file.Files
import java.nio.file.Paths

class OpenAllFiles(path: String) {
    var listData: HashMap<Address, Int> = hashMapOf()
    private val item = "item"

    init {
        if (path.substring(path.length - 4) == ".csv" ||
            path.substring(path.length - 4) == ".xml"
        ) {
            if (path[path.length - 3] == 'c')
                readCsv(path)
            else if (path[path.length - 3] == 'x')
                readXml(path)
        } else throw Exception("File is not csv or xml")
    }

    private fun readCsv(inputStream: String) {
        val reader = Files.newBufferedReader(Paths.get(inputStream))
        val csvParser = CSVParser(reader, CSVFormat.newFormat(';'))
        var newKey: Address
        csvParser.records.drop(1)
            .forEach {
                newKey = Address(it[0], it[1], it[2].toInt(), it[3].toInt())
                if (listData.isEmpty() || !listData.containsKey(newKey))
                    listData[newKey] = 1
                else
                    listData[newKey]?.let { it1 ->
                        listData.replace(
                            newKey, it1, it1 + 1
                        )
                    }
            }
    }

    private fun readXml(inputStream: String) {
        val factory = DocumentBuilderFactory.newInstance()
        val builder: DocumentBuilder = factory.newDocumentBuilder()
        val document: Document = builder.parse(File(inputStream))
        val elements = document.getElementsByTagName(item)
        for (i in 0 until elements.length) {
            val attributes: NamedNodeMap = elements.item(i).attributes
            val city: String = attributes.getNamedItem("city").nodeValue
            val street: String = attributes.getNamedItem("street").nodeValue
            val house: String = attributes.getNamedItem("house").nodeValue
            val floor: String = attributes.getNamedItem("floor").nodeValue
            val address = Address(city, street, house.toInt(), floor.toInt())
            if (listData.containsKey(address)) {
                listData[address]?.let { listData.replace(address, it.plus(1)) }
            } else
                listData[address] = 1
        }
    }
}