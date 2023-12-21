import java.util.*
import kotlin.random.Random
open class Gegner(val name: String) {
    var health = 50

    fun takeDamage(damage: Int) {
        health -= damage
        if (health > 0) {
            println("$name hat $damage Schaden erlitten und hat noch $health Lebenspunkte.")
        } else {
            println("$name wurde besiegt!")
        }
    }

    fun performRandomAttack(held: Held) {
        val randomAttackIndex = Random.nextInt(1, 5)
        val damage = Random.nextInt(8, 15)
        println("$name greift ${held.name} mit Angriff $randomAttackIndex an und verursacht $damage Schaden.")
        held.health -= damage
        println("${held.name} hat noch ${held.health} Lebenspunkte.")
    }
}



