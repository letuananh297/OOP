package `lab1(2)`

class WikipediaPage(
    var title: String,
    var pageid: String,
) {
    override fun toString(): String {
        return "title: ${this.title}, pageid: ${this.pageid}"
    }
}