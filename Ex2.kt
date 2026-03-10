package org.example
import org.jsoup.Jsoup

fun procesareEbook(Ebook: String) :String{
        var procesare=Ebook

        val spatiiRegex= Regex("[ \t]+")
        procesare=spatiiRegex.replace(procesare," ")

        val newlineRegex= Regex("\n+")
        procesare=newlineRegex.replace(procesare,"\n")

        val pageNumberRegex= Regex("(?<=\\s)\\d+(?=\\s)")
        procesare=pageNumberRegex.replace(procesare,"\n")

        return procesare

}

fun main(args: Array<String>)
{
    val mockEbookText = """
        23rv3r  32c23  gegdrgsef.
        
        
        seffsf        33w3w ff
        
        
          44 
        
    """.trimIndent()
    val cleanText = procesareEbook(mockEbookText)
    println(cleanText)
}