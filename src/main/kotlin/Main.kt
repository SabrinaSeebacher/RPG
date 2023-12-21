import kotlin.random.Random

fun main() {
println()
    println("Reich der Legenden: Schicksalserwachen")
println()


    val heroes = listOf(Held("Aric Windläufer"), Held("Elara Feuerschmiedin"), Held("Lyra Mondtänzerin"),
                Held("Thoren Sturmhammer"), Held("Seraphina Sternensängerin"), Held("Kael Schattenjäger"))
    val selectedHeroes = mutableListOf<Held>()
    val enemies = listOf(Gegner("Sylthara die Giftige"), Gegner("Gromm der Zerstörer"))
    val endBoss = Gegner("Morlok der Dunkle")

    println()
    println("Wähle 3 Helden aus:")
    for (i in 1..3) {
        println()
        println("Wähle Held $i:")
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
    println("Die Schlacht beginnt!")
    println()
    for (gegner in ) {
        while (gegner.health > 0 && selectedHeroes.any { it.health > 0 }) {
            println("\n$gegner greift an!")
            selectedHeroes.forEach { it.attack(gegner) }
            if (gegner.health > 0) {
                gegner.performRandomAttack(selectedHeroes.random())
            }
        }
    }

    if (selectedHeroes.all { it.health > 0 }) {
        println()

        println("\nMorlok der Dunkle erscheint!")
        println()
        while (endBoss.health > 0 && selectedHeroes.any { it.health > 0 }) {
            println("\n $endBoss greift an!")
            selectedHeroes.forEach { it.attack(endBoss) }
            if (endBoss.health > 0) {
                endBoss.performRandomAttack(selectedHeroes.random())
            }
        }
        if (selectedHeroes.all { it.health > 0 }) {
            println()
            println("\nDie Helden haben Morlok den Dunkle besiegt und die Welt gerettet!")
        } else {
            println()
            println("\nDie Helden wurden besiegt. Das Abenteuer endet hier.")
        }
    } else {
        println()
        println("\nDie Helden wurden besiegt. Das Abenteuer endet hier.")
    }
}

