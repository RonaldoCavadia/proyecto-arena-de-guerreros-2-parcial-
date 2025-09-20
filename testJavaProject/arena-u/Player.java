// Clase que representa a un héroe controlado por el usuario
public class Player {
    private String name;     // nombre del héroe
    private int health;      // vida actual del héroe
    private Weapon weapon;   // arma equipada

    // Constructor: inicializa un héroe con nombre, vida y un arma
    public Player(String name, int health, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
    }

    // Devuelve el nombre del héroe
    public String getName() {
        return name;
    }

    // Devuelve la vida actual
    public int getHealth() {
        return health;
    }

    // Indica si el héroe sigue vivo
    public boolean isAlive() {
        return health > 0;
    }

    // Método para recibir daño
    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) health = 0; // evitar valores negativos
        System.out.println(name + " recibe " + amount + " de daño. Vida restante: " + health);
    }

    // Método para atacar a un enemigo
    public void attack(Enemy enemy) {
        int damage = weapon.getDamage();
        System.out.println(name + " ataca con " + weapon.getName() + " y causa " + damage + " de daño a " + enemy.getName());
        enemy.takeDamage(damage);
    }
}


