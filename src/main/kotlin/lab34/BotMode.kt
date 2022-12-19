package lab34

interface BotMode {
    fun allCountriesBeginWithLetter(letter: Char) : List<String>
    fun allCountriesLeft(letter: Char) : List<String>
    fun chooseCountries(letter: Char) : String?
    fun clearSelected()
    fun addToSelected(country: String)
    fun checkEnd(letter: Char) : Boolean
    fun checkRightName(country: String) : Boolean
    fun checkSelected(country: String) : Boolean
}