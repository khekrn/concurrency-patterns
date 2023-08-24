import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author KK
 * @version 1.0
 *
 */

fun main() = runBlocking {
    val channel = Channel<String>()
    launch { displayChannelMessage("Hello", channel) }
    for (i in 1..3){
        println(channel.receive())
    }
    println("Done!")
}
suspend fun displayChannelMessage(message: String, channel: SendChannel<String>){
    var i = 0
    while (true){
        channel.send("$message $i")
        delay(1000)
        i++
    }
}