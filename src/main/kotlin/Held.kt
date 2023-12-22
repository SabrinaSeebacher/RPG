import kotlin.random.Random



open class Held(val name: String) {
    // Lebenspunkte des Helden, standardmäßig auf 100 gesetzt
    var health = 100
    // Liste von Angriffen, die der Held ausführen kann
    val attacks = listOf("Feuerschlag", "Axthieb", "", "Angriff 4")
    // Inventar/Beutel des Helden mit standardmäßigen Gegenständen
    val inventory = mutableListOf("Heilungstrank", "Booster", "Schutzzauber")

    // Methode für den Angriff auf einen Gegner
    fun attack(enemy: Gegner) {

        println("$name, wähle deine Attacke:")
        // Ausgabe der verfügbaren Angriffe mit ihren Indexnummern
        attacks.forEachIndexed { index, attack -> println("$index. $attack") }

        // Einlesen des Angriffsindex vom Benutzer
        val attackIndex = readln().toIntOrNull()

        // Überprüfung Eingabe gültig ist und im Bereich der Angriffe liegt
        if (attackIndex != null && attackIndex in attacks.indices) {
            // Auswahl des ausgewählten Angriffs
            val selectedAttack = attacks[attackIndex]

            // Schadensbestimmung auf dem ausgewählten Angriff
            val damage = when (selectedAttack) {
                "Angriff 1" -> Random.nextInt(10, 20)
                "Angriff 2" -> Random.nextInt(15, 25)
                "Angriff 3" -> Random.nextInt(5, 30)
                "Angriff 4" -> Random.nextInt(20, 25)
                else -> Random.nextInt(5, 10)
            }

            // Gibt eine Nachricht über den Angriff und den verursachten Schaden
            println("$name greift ${enemy.name} mit $selectedAttack an und verursacht $damage Schaden.")

            // Verursacht den Schaden am Gegner
            enemy.takeDamage(damage)
        } else {
            // Wenn die Eingabe ungültig ist, führe einen Standardangriff aus
            println("Ungültige Eingabe. Der Held führt einen Standardangriff aus.")

            // Bestimmung des Schadens für den Standardangriff
            val damage = Random.nextInt(5, 10)


            println("$name führt einen Standardangriff aus und verursacht $damage Schaden.")

            // Verursache den Schaden am Gegner
            enemy.takeDamage(damage)
        }
    }

    // Methode zum Verwenden eines Gegenstands aus dem Inventar
    fun useItem(item: String) {
        //Gegenstand im Inventar vorhanden ist
        if (inventory.contains(item)) {
            // Nachricht über die Verwendung des Gegenstands
            println("$name verwendet $item.")

            // Entfert Gegenstand aus dem Inventar
            inventory.remove(item)


            when (item) {
                "Heilungstrank" -> {
                    // Heilungsmenge für den Heiltrank
                    val healingAmount = 20
                    // Heilt den Helden, begrenzt auf maximal 100 Lebenspunkte
                    health = minOf(health + healingAmount, 100)
                    // Ausgabe einer Nachricht über die Heilung
                    println("$name wird um $healingAmount Lebenspunkte geheilt.")
                }
                "Booster" -> println("$name wird verstärkt und richtet nun mehr Schaden an.")
                "Schutzzauber" -> println("$name ist geschützt und nimmt weniger Schaden.")
            }
        } else {
            // Wenn der Gegenstand nicht im Inventar ist
            println("$name hat dieses Item nicht im Inventar.")
        }
    }
}