import java.util.Random;

// Clase que representa un arma con un rango de daño aleatorio
public class Weapon {
    private String name;       // nombre del arma
    private int minDamage;     // daño mínimo posible
    private int maxDamage;     // daño máximo posible
    private Random random;     // generador aleatorio

    // Constructor: inicializa el arma con un nombre y un rango de daño
    public Weapon(String name, int minDamage, int maxDamage) {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.random = new Random();
    }

    // Devuelve el nombre del arma
    public String getName() {
        return name;
    }

    // Calcula y devuelve un daño aleatorio entre minDamage y maxDamage
    public int getDamage() {
        return random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }
}


