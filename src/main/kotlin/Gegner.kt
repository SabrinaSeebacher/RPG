
import kotlin.random.Random

//  offenen Klasse für Gegner
open class Gegner(var name: String, open var health: Int) {
    // Methode, um Schaden am Gegner zu verursachen
    fun takeDamage(damage: Int) {
        // Reduziere die Lebenspunkte des Gegners um den verursachten Schaden
        health -= damage
        // Überprüfe, ob der Gegner noch Lebenspunkte hat
        if (health > 0) {
            // Wenn der Gegner noch lebt, gib eine Nachricht aus
            println("$name hat $damage Schaden erlitten und hat noch $health Lebenspunkte.")
        } else {
            // Wenn der Gegner besiegt wurde, gib eine entsprechende Nachricht aus
            println("$name wurde besiegt!")
        }
    }

    // Methode für einen zufälligen Angriff auf einen Helden
    fun performRandomAttack(held: Held) {
        // Zufällige Auswahl eines Angriffsindex zwischen 1 und 4
        val randomAttackIndex = Random.nextInt(1, 5)
        // Zufällige Generierung von Schaden zwischen 8 und 15
        val damage = Random.nextInt(8, 15)
        // Ausgabe einer Nachricht über den Angriff
        println("$name greift ${held.name} mit Angriff $randomAttackIndex an und verursacht $damage Schaden.")
        // Reduziere die Lebenspunkte des Helden um den verursachten Schaden
        held.health -= damage
        // Ausgabe der verbleibenden Lebenspunkte des Helden
        println("${held.name} hat noch ${held.health} Lebenspunkte.")
    }
}
