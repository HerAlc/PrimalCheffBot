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

  on("/help", "Show help") { implicit msg => _ => reply("No implemented") }
  on("/suggestMe", "Suggest some recipe according to your taste") { implicit msg => _ => reply("No implemented") }
  on("/popular", "Popular recipes") { implicit msg => _ => reply("No implemented") }
  on("/random", "A random recipe") { implicit msg => _ => reply("No implemented") }
  on("/byIngredient", "Give recipes containing the given ingredient") { implicit msg => _ => reply("No implemented") }
  on("/dessert", "Dessert recipes") { implicit msg => _ => reply("No implemented") }
  on("/dinner", "Recipes suited for dinners") { implicit msg => _ => reply("No implemented") }
  on("/lunch", "Recipes suited for lunch") { implicit msg => _ => reply("No implemented") }
  on("/breakfast", "Recipes suited for breakfast") { implicit msg => _ => reply("No implemented") }

}

object Hello extends App {
 SafeBot.run()
}
