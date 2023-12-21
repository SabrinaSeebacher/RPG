import kotlin.random.Random
import Held
import Gegner
import MorlokDerDunkle
import GrommDerZerstörer
import SyltharaDieGiftige

val red = "\u001B[31m"
val green = "\u001B[32m"
val yellow = "\u001B[33m"
val black = "\u001b[30;1m"
val hintergrundBlau = "\u001b[44m"
val cyan = "\u001B[36m"
val reset = "\u001B[0m"
val bold = "\u001B[1m"
val underline = "\u001B[4m"




fun main() {
    println()
    println("${hintergrundBlau}${black}${bold}Reich der Legenden: Schicksalserwachen${reset}")
    val heroes = listOf(
        Held("Aric Windläufer"),
        Held("Elara Feuerschmiedin"),
        Held("Lyra Mondtänzerin"),
        Held("Thoren Sturmhammer"),
        Held("Seraphina Sternensängerin"),
        Held("Kael Schattenjäger")
    )
    val selectedHeroes = mutableListOf<Held>()
    val enemies = listOf(Gegner("Sylthara die Giftige", 100), Gegner("Gromm der Zerstörer", 90))
    val endBoss = MorlokDerDunkle("Morlok der Dunkle", 140)


    println()
    println("${cyan}${bold}Wähle 3 Helden aus:${reset}")
    for (i in 1..3) {
        println()
        println("${cyan}${bold}Wähle Held $i:")
        println()
        heroes.forEachIndexed { index, hero -> println("$index. ${hero.name}") }
        val heroIndex = readLine()?.toIntOrNull()
        if (heroIndex != null && heroIndex in 0 until heroes.size) {
            selectedHeroes.add(heroes[heroIndex])
        } else {
            println("Ungültige Eingabe. Ein zufälliger Held wird ausgewählt.")
            selectedHeroes.add(heroes.random())
        }
    }
    println()
    println("${cyan}${bold}Die Schlacht beginnt!")
    println()
    for (gegner in enemies) {
        while (gegner.health > 0 && selectedHeroes.any { it.health > 0 }) {
            println("${cyan}${bold}${gegner.name} greift an!")
            selectedHeroes.forEach { it.attack(gegner) }
            if (gegner.health > 0) {
                gegner.performRandomAttack(selectedHeroes.random())
            }
        }
    }

    if (selectedHeroes.all { it.health > 0 }) {
        println()
        println("${cyan}Morlok der Dunkle erscheint!${reset}")
        println()
        while (endBoss.health > 0 && selectedHeroes.any { it.health > 0 }) {
            println("${cyan}${bold}${endBoss.name} greift an!")
            selectedHeroes.forEach { it.attack(endBoss) }
            if (endBoss.health > 0) {
                endBoss.performRandomAttack(selectedHeroes.random())
            }
        }
        if (selectedHeroes.all { it.health > 0 }) {
            println()
            println("${cyan}Die Helden haben Morlok den Dunkle besiegt und die Welt gerettet!${reset}")
        } else {
            println()
            println("${red}Die Helden wurden besiegt. Das Abenteuer endet hier.${reset}")
        }
    } else {
        println()
        println("${red}Die Helden wurden besiegt. Das Abenteuer endet hier.${reset}")
    }
}
