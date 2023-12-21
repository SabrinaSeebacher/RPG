import kotlin.random.Random

open class Held (val name: String){



    var health = 100
    val attacks = listOf("Angriff 1", "Angriff 2", "Angriff 3", "Angriff 4")
    val inventory = mutableListOf("Heilungstrank", "Booster", "Schutzzauber")

    fun attack(gegner: Gegner) {
        val attackIndex = readLine()?.toIntOrNull()
        if (attackIndex != null && attackIndex in 1..4) {
            val damage = Random.nextInt(10, 20)
            println("$name greift ${gegner.name} mit ${attacks[attackIndex - 1]} an und verursacht $damage Schaden.")
            gegner.takeDamage(damage)
        } else {
            println("Ungültige Eingabe. Der Held führt einen Standardangriff aus.")
            val damage = Random.nextInt(5, 10)
            gegner.takeDamage(damage)
        }
    }

    fun useItem(item: String) {
        if (inventory.contains(item)) {
            println("$name verwendet $item.")
            inventory.remove(item)
            when (item) {
                "Heilungstrank" -> health += 20
                "Booster" -> println("$name wird verstärkt und richtet nun mehr Schaden an.")
                "Schutzzauber" -> println("$name ist geschützt und nimmt weniger Schaden.")
            }
        } else {
            println("$name hat dieses Item nicht im Inventar.")
        }
    }
}
