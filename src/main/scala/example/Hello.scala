package example

import info.mukel.telegrambot4s._, api._, methods._, models._, Implicits._
import scala.io.Source

object SafeBot extends TelegramBot with Polling with Commands {
  // Use 'def' or 'lazy val' for the token, using a plain 'val' may/will
  // lead to initialization order issues.
  // Fetch the token from an environment variable or file.
  lazy val token = scala.util.Properties
    .envOrNone("BOT_TOKEN")
    .getOrElse(Source.fromFile("bot.token").getLines().mkString)

  on("/hello") { implicit msg => _ => reply("My token is SAFE!") }
}

object Hello extends Greeting with App {
  SafeBot.run()
}

trait Greeting {
  lazy val greeting: String = "hello"
}
