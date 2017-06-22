package example

import scala.io.Source
import scala.util.Random

import info.mukel.telegrambot4s.Implicits._
import info.mukel.telegrambot4s.api._

object SafeBot extends TelegramBot with Polling with Commands {
  // Use 'def' or 'lazy val' for the token, using a plain 'val' may/will
  // lead to initialization order issues.
  // Fetch the token from an environment variable or file.
  lazy val token = scala.util.Properties
    .envOrNone("BOT_TOKEN")
    .getOrElse(Source.fromFile("bot.token").getLines().mkString)

  val rng = new Random(System.currentTimeMillis())

  on("/help", "Show help") { implicit msg => _ => reply("No implemented") }

  on("/coin", "head or tail") { implicit msg => _ => reply(if (rng.nextBoolean()) "Head!" else "Tail!") }
  on("/real", "real number in [0, 1]") { implicit msg => _ => reply(rng.nextDouble().toString) }
  on("/die", "classic die [1 .. 6]") { implicit msg => _ => reply((rng.nextInt(6) + 1).toString) }
  on("/dice", "throw two classic dice [1 .. 6]") { implicit msg => _ => reply((rng.nextInt(6) + 1) + " " + (rng.nextInt(6) + 1)) }
  on("/random", "integer in [0, n)") { implicit msg => {
    case Seq(Extractor.Int(n)) if n > 0 =>
      reply(rng.nextInt(n).toString)
    case _ =>
      reply("Invalid argumentヽ(ಠ_ಠ)ノ")
  }
  }
  on("/choose", "randomly picks one of the arguments") { implicit msg => args =>
    reply(if (args.isEmpty) "Empty list." else args(rng.nextInt(args.size)))
  }
}

object Hello extends App {
 // SafeBot.run()
}
