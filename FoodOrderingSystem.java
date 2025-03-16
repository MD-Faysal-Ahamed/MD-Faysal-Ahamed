import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MenuItem {
    String name;
    double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - $" + String.format("%.2f", price);
    }
}

class CartItem {
    MenuItem item;
    int quantity;

    public CartItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return item.price * quantity;
    }

    @Override
    public String toString() {
        return item.name + " x" + quantity + " - $" + String.format("%.2f", getTotalPrice());
    }
}

public class FoodOrderingSystem {

    public static void main(String[] args) {
        List<MenuItem> menu = new ArrayList<>();
        menu.add(new MenuItem("Pizza", 10.99));
        menu.add(new MenuItem("Pasta", 8.99));
        menu.add(new MenuItem("Salad", 5.99));

        List<CartItem> cart = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Food Ordering System ---");
            System.out.println("1. View Menu");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewMenu(menu);
                    break;
                case 2:
                    addToCart(menu, cart, scanner);
                    break;
                case 3:
                    viewCart(cart);
                    break;
                case 4:
                    System.out.println("Thank you for your order!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewMenu(List<MenuItem> menu) {
        System.out.println("\n--- Menu ---");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }
    }

    private static void addToCart(List<MenuItem> menu, List<CartItem> cart, Scanner scanner) {
        viewMenu(menu);
        System.out.print("Enter the item number to add: ");
        int itemNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (itemNumber >= 1 && itemNumber <= menu.size()) {
            MenuItem selectedItem = menu.get(itemNumber - 1);
            System.out.print("Enter the quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            CartItem existingItem = null;
            for (CartItem cartItem : cart) {
                if (cartItem.item.equals(selectedItem)) {
                    existingItem = cartItem;
                    break;
                }
            }

            if (existingItem != null) {
                existingItem.quantity += quantity;
            } else {
                cart.add(new CartItem(selectedItem, quantity));
            }

            System.out.println(quantity + " " + selectedItem.name + "(s) added to cart.");
        } else {
            System.out.println("Invalid item number.");
        }
    }

    private static void viewCart(List<CartItem> cart) {
        System.out.println("\n--- Your Cart ---");
        double total = 0;
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            for (CartItem cartItem : cart) {
                System.out.println(cartItem);
                total += cartItem.getTotalPrice();
            }
            System.out.println("Total: $" + String.format("%.2f", total));
        }
    }
}
