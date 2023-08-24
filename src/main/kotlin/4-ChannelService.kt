import kotlinx.coroutines.runBlocking

/**
 * @author KK
 * @version 1.0
 *
 */

fun main() {
    val joe = generatorViaChannel("Hello")
    val ann = generatorViaChannel("Bye !")
    runBlocking {
        for (i in 1..5){
            println(joe.receive())
            println(ann.receive())
        }
    }
    println("You're both boring; I'm leaving")
}