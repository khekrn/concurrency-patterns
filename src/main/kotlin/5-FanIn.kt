import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

/**
 * @author KK
 * @version 1.0
 *
 */

fun main() {
    val channel = fanInV2(generatorViaChannel("Hello"), generatorViaChannel("Bye !"))
    runBlocking {
        //val channel = fanIn(generator("Hello"), generator("Bye !"))
        repeat(10) {
            println(channel.receive())
        }
    }
    channel.cancel() // Cancel the channel when done
}

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.fanIn(ch1: ReceiveChannel<String>, ch2: ReceiveChannel<String>): ReceiveChannel<String> = produce {
    launch { for (msg in ch1) send(msg) }
    launch { for (msg in ch2) send(msg) }
}

@OptIn(DelicateCoroutinesApi::class)
fun fanInV2(ch1: ReceiveChannel<String>, ch2: ReceiveChannel<String>): ReceiveChannel<String>{
    val ch = Channel<String>()
    GlobalScope.launch { for (msg in ch1) ch.send(msg) }
    GlobalScope.launch { for (msg in ch2) ch.send(msg) }
    return ch
}