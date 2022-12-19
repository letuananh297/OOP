package `lab2(nhap)`

data class Address(
    val city: String,
    val street: String,
    val house: Int,
    val floor: Int
) {
    override fun toString(): String {
        return "Адрес: '$city', '$street', $house ($floor floor(s))."
    }
}