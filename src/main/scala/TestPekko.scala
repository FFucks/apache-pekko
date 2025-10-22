import org.apache.pekko.actor.{Actor, ActorRef, ActorSystem, Props}

class PrinterActor extends Actor {
    def receive: Receive = {
        case msg: String =>
            println(s"[PrinterActor] Received: $msg")
        case _ =>
            println("[PrinterActor] Unknowm Message.")
    }
}

class GreeterActor(printerActor: ActorRef) extends Actor {
    def receive: Receive = {
        case name: String =>
            val greeting = s"Hello, $name! Welcome"
            println(s"[GreeterActor] Sending message to print actor...")
            printerActor ! greeting
        case _ =>
            println("[GreeterActor] Unknown message.")
    }
}

object TestPekko extends App {
    val system = ActorSystem("TwoActorsSystem")
    val printerActor: ActorRef = system.actorOf(Props[PrinterActor](), "printerActor")
    val greeterActor: ActorRef = system.actorOf(Props(classOf[GreeterActor], printerActor), "greeterActor")

    greeterActor ! "Fabio"
    greeterActor ! "Maria"
    greeterActor ! 123

    Thread.sleep(1000)
    system.terminate()
}
