import java.util.*;

public class GameMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Arena de Guerreros: 3 Héroes vs 3 Villanos ===");

        // Lista de armas disponibles con diferentes rangos de daño
        List<Weapon> weapons = Arrays.asList(
                new Weapon("Espada legendaria", 15, 25),
                new Weapon("Arco largo", 10, 20),
                new Weapon("Hacha de guerra", 20, 30),
                new Weapon("Daga envenenada", 10, 30),
                new Weapon("Maza pesada", 25, 35),
                new Weapon("Bastón mágico", 5, 40)
        );

        Random random = new Random();

        // Equipo de héroes: ingresados por el usuario
        List<Player> heroes = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            System.out.print("Ingrese el nombre del héroe " + i + ": ");
            String name = scanner.nextLine();

            Weapon weapon = weapons.get(random.nextInt(weapons.size()));
            heroes.add(new Player(name, 100, weapon));

            System.out.println("Héroe " + name + " entra a la arena con " + weapon.getName());
        }

        // Equipo de villanos: definidos en el código
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy("Orco Salvaje", 100, weapons.get(2)));     // Hacha
        enemies.add(new Enemy("Esqueleto Guerrero", 90, weapons.get(1))); // Arco
        enemies.add(new Enemy("Hechicero Oscuro", 80, weapons.get(5)));  // Bastón

        System.out.println("\n=== ¡Comienza la Batalla! ===");

        // Bucle principal de batalla por rondas
        while (!heroes.isEmpty() && !enemies.isEmpty()) {
            // Turnos de los héroes
            for (Player hero : new ArrayList<>(heroes)) {
                if (!hero.isAlive()) {
                    heroes.remove(hero);
                    continue;
                }
                                                                                                                                                                                                                                                                                                                                                                                                                                                              
                System.out.println("\nTurno de " + hero.getName());
                System.out.println("Elige a qué villano atacar:");
                for (int j = 0; j < enemies.size(); j++) {
                    Enemy e = enemies.get(j);
                    if (e.isAlive()) {
                        System.out.println((j + 1) + ". " + e.getName()
                                + " (Vida: " + e.getHealth() + ")");
                    }
                }

                int choice;
                do {
                    System.out.print("Selecciona villano (número): ");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // limpiar buffer
                } while (choice < 1 || choice > enemies.size());

                Enemy target = enemies.get(choice - 1);
                hero.attack(target);
            }

            // Eliminar enemigos derrotados al final del turno de héroes
            enemies.removeIf(e -> !e.isAlive());
            if (enemies.isEmpty()) break;

            // Turnos de los villanos
            for (Enemy enemy : new ArrayList<>(enemies)) {
                if (!enemy.isAlive()) continue;
                if (!heroes.isEmpty()) {
                    enemy.autoAttack(heroes);
                }
            }

            // Eliminar héroes derrotados al final del turno de villanos
            heroes.removeIf(h -> !h.isAlive());
        }

        // Resultado final de la batalla
        if (heroes.isEmpty()) {
            System.out.println("\nLos villanos ganaron la batalla.");
        } else {
            System.out.println("\nLos héroes ganaron la batalla.");
        }
    }
}


