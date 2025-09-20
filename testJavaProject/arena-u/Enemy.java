import java.util.List;
import java.util.Random;

// Clase que representa a un villano controlado por la computadora
public class Enemy {
    private String name;     // nombre del enemigo
    private int health;      // vida actual del enemigo
    private Weapon weapon;   // arma equipada
    private Random random;   // generador aleatorio para elegir objetivo

    // Constructor: inicializa un enemigo con nombre, vida y un arma
    public Enemy(String name, int health, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
        this.random = new Random();
    }

    // Devuelve el nombre del enemigo
    public String getName() {
        return name;
    }

    // Devuelve la vida actual
    public int getHealth() {
        return health;
    }

    // Indica si el enemigo sigue vivo
    public boolean isAlive() {
        return health > 0;
    }

    // Método para recibir daño
    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) health = 0; // evitar negativos
        System.out.println(name + " recibe " + amount + " de daño. Vida restante: " + health);
    }

    // Método para atacar automáticamente a un héroe aleatorio
    public void autoAttack(List<Player> heroes) {
        if (heroes.isEmpty()) return;

        // Elegir un héroe aleatorio de la lista
        Player target = heroes.get(random.nextInt(heroes.size()));
        int damage = weapon.getDamage();

        System.out.println(name + " ataca con " + weapon.getName() + " y causa " + damage + " de daño a " + target.getName());
        target.takeDamage(damage);
    }
}


