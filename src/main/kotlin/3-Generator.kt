/**
 * @author KK
 * @version 1.0
 *
 */

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

fun main() {
    runBlocking {
        val channel = generatorViaChannel("Hello")
        repeat(5) {
            println(channel.receive())
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.generator(msg: String) = produce {
    var i = 0
    while (true) {
        send("$msg $i")
        delay(1000) // equivalent to time.Sleep(time.Second) in Go
        i++
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun generatorViaChannel(msg: String): ReceiveChannel<String> {
    var i = 0
    val channel = Channel<String>()
    GlobalScope.launch {
        while (true) {
            channel.send("$msg $i")
            delay(1000) // equivalent to time.Sleep(time.Second) in Go
            i++
        }
    }
    return channel
}
