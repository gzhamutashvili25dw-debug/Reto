package reto1.Challenge;
import java.util.Scanner;

public class SearchTrain {

    public static Route[] searchAndDisplay(BuyTicket system, Scanner scanner) {
        System.out.println("\n--- Search Train ---");
        System.out.print("Origin: ");
        String origin = scanner.nextLine().trim();

        System.out.print("Destination: ");
        String destination = scanner.nextLine().trim();

        Route[] foundRoutes = system.searchRoutes(origin, destination);

        if (foundRoutes.length == 0) {
            System.out.printf("\nNo routes found from %s to %s.\n", origin, destination);
            return new Route[0];
        }

        System.out.println("\n--- Tours Found ---");
        for (int i = 0; i < foundRoutes.length; i++) {
            System.out.printf("%d. %s\n", (i + 1), foundRoutes[i].toString());
        }

        return foundRoutes;
    }
}