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

    val heroes = listOf(
        Held("Aric Windläufer"),
        Held("Elara Feuerschmiedin"),
        Held("Lyra Mondtänzerin"),
        Held("Thoren Sturmhammer"),
        Held("Seraphina Sternensängerin"),
        Held("Kael Schattenjäger")
    )

    val selectedHeroes = mutableListOf<Held>()

    // Instanzen für die Bosse und Gegner
    val sylthara = SyltharaDieGiftige("Sylthara die Giftige", 150)
    val gromm = GrommDerZerstörer("Gromm der Zerstörer", 90)
    val morlok = MorlokDerDunkle("Morlok der Dunkle", 200)

    // Spielstartausgabe
    println()
    println("${hintergrundBlau}${black}${bold}Reich der Legenden: Schicksalserwachen${reset}")

    // Helden-Auswahl
    println("${cyan}${bold}Wähle 3 Helden aus:${reset}")
    for (i in 1..3) {
        println()
        println("${cyan}${bold}Wähle Held $i:${reset}")
        println()
        heroes.forEachIndexed { index, hero -> println("$index. ${hero.name}") }

        // Benutzereingabe für Heldenwahl
        val heroIndex = readln().toIntOrNull()

        // Überprüfung der Eingabe
        if (heroIndex != null && heroIndex in heroes.indices) {
            selectedHeroes.add(heroes[heroIndex])
        } else {
            println("Ungültige Eingabe. Ein zufälliger Held wird ausgewählt.")
            selectedHeroes.add(heroes.random())
        }
    }

    // Kampf gegen die Unterbosse
    println("${cyan}${bold}Die Schlacht gegen die Unterbosse beginnt!${reset}")
    println()
    for (enemy in listOf(sylthara, gromm)) {
        // Kampfzyklus, bis der Unterboss besiegt ist oder die Helden besiegt sind
        while (enemy.health > 0 && selectedHeroes.any { it.health > 0 }) {
            println("${cyan}${bold}${enemy.name} greift an!")
            selectedHeroes.forEach { it.attack(enemy) }
            if (enemy.health > 0) {
                enemy.performRandomAttack(selectedHeroes.random())
            }
        }
    }

    // Kampf gegen den Endboss Morlok
    println()
    println("${cyan}${bold}Morlok der Dunkle erscheint!${reset}")
    println()
    // Kampfzyklus, bis Morlok besiegt ist oder die Helden besiegt sind
    while (morlok.health > 0 && selectedHeroes.any { it.health > 0 }) {
        println("${cyan}${bold}${morlok.name} greift an!")
        selectedHeroes.forEach { it.attack(morlok) }
        if (morlok.health > 0) {
            morlok.performRandomAttack(selectedHeroes.random())
        }
    }

    // Ausgabe des Spielendes basierend auf dem Zustand der Helden
    if (selectedHeroes.all { it.health > 0 }) {
        println()
        println("${cyan}Die Helden haben Morlok den Dunklen besiegt und die Welt gerettet!${reset}")
    } else {
        println()
        println("${red}Die Helden wurden besiegt. Das Abenteuer endet hier.${reset}")
    }
}

