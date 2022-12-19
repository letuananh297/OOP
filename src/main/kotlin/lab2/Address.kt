package lab2

data class Address(val city: String, val street: String, val house: Int, val storey: Int) {
    override fun toString(): String {
        return "$city, $street, $house ($storey-storey building)"
    }
}

data class StoreyBuildings(
    var one: Int = 0,
    var two: Int = 0,
    var three: Int = 0,
    var four: Int = 0,
    var five: Int = 0
    )