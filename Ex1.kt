package org.example
import org.jsoup.Jsoup

data class RssItem(
    val title: String,
    val link: String,
    val description: String
)

data class RssFeed(
    val title: String,
    val link: String,
    val description: String,
    val pubDate: String,
    val items: List<RssItem>
)

fun procesareRssFeed(url: String): RssFeed {
    val doc = Jsoup.connect(url).get()
    val channel= doc.selectFirst("channel")
    val Title = channel.selectFirst("title").text()
    val Link = channel.selectFirst("link").text()
    val Description = channel.selectFirst("description").text()
    val PubDate = channel.selectFirst("pubDate").text()

    val listItems= mutableListOf<RssItem>()
    val items= doc.select("item")

    for(item in items){
        val title = item.selectFirst("title")?.text() ?: ""
        val link = item.selectFirst("link")?.text() ?: ""
        val description = item.selectFirst("description")?.text() ?: ""

        listItems.add(RssItem(title, link, description))

    }
    return RssFeed(Title,Link,Description,PubDate,listItems)
}

fun main(args: Array<String>) {
    val url = "http://rss.cnn.com/rss/edition.rss"
    val feed = procesareRssFeed(url)

    println(feed.title)
    println(feed.link)
    println(feed.description)
    println(feed.pubDate)
    feed.items.forEach { item ->
        println(item.title)
        println(item.link)
        println(item.description)
    }
}

