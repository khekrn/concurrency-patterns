import kotlinx.coroutines.*

/**
 * @author KK
 * @version 1.0
 *
 */

fun main() = runBlocking {
    launch { displayMessage("Hello") }
    println("Second Print Statement")
    delay(3000)
    println("Third Print Statement")
}
suspend fun displayMessage(message: String){
    var i = 0
    while (true){
        println("$message $i")
        delay(1000)
        i++
    }
}