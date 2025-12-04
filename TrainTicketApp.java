package reto1;

import java.util.List;
import java.util.Scanner;


public class TrainTicketApp {
    private static TicketReservationSystem system;
    private static Scanner scanner;

    public static void main(String[] args) {
        system = new TicketReservationSystem();
        scanner = new Scanner(System.in);


        System.out.println("  Welcome to the Train Ticket Reservation System!");

        showMainMenu();
    }


    private static void showMainMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Search Available Routes");
            System.out.println("2. View All Available Routes");
            System.out.println("0. Exit Application");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        searchAndBookRoutes();
                        break;
                    case 2:
                        viewAllRoutes();
                        break;
                    case 0:
                        System.out.println("Thank you for using the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }


    private static void searchAndBookRoutes() {
        System.out.println("\n--- Search Routes ---");
        System.out.print("Enter Origin Station: ");
        String origin = scanner.nextLine().trim();

        System.out.print("Enter Destination Station: ");
        String destination = scanner.nextLine().trim();

        List<TrainRoute> foundRoutes = system.searchRoutes(origin, destination);

        if (foundRoutes.isEmpty()) {
            System.out.printf("\nNo routes found from %s to %s.\n", origin, destination);
            return;
        }



        TrainRoute selectedRoute = foundRoutes.get(0);






        processBooking(selectedRoute);

    }


    private static void processBooking(TrainRoute route) {

        System.out.printf("You selected the route:\n%s\n", route.toString());


        System.out.print("How many tickets do you want to purchase? ");

        if (scanner.hasNextInt()) {
            int numTickets = scanner.nextInt();
            scanner.nextLine();

            if (numTickets <= 0) {
                System.out.println("You must purchase at least one ticket.");
                return;
            }

            if (route.getAvailableSeats() < numTickets) {
                System.out.printf("Only %d seats are available on this train. Cannot book %d tickets.\n", route.getAvailableSeats(), numTickets);
                return;
            }

            double totalCost = numTickets * route.getPrice();
            System.out.printf("Total cost for %d tickets: %.2f€\n", numTickets, totalCost);
            System.out.print("Confirm purchase? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("yes")) {
                if (route.bookTickets(numTickets)) {
                    System.out.println("\n");
                    System.out.println("BOOKING SUCCESSFUL!");

                    System.out.printf("  Route: %s\n", route.toString());
                    System.out.printf("  Tickets: %d | Total Paid: %.2f€\n", numTickets, totalCost);
                    System.out.printf("  Remaining seats on this route: %d\n", route.getAvailableSeats());

                } else {

                    System.out.println("BOOKING FAILED: Could not reserve seats.");
                }
            } else {
                System.out.println("Purchase cancelled. Returning to main menu.");
            }
        } else {
            System.out.println("Invalid input for number of tickets.");
            scanner.nextLine();
        }
    }


    private static void viewAllRoutes() {
        List<TrainRoute> routes = system.getAllRoutes();
        System.out.println("\n--- All Available Routes in the Network ---");

        for (int i = 0; i < routes.size(); i++) {

            System.out.printf(" - %s\n", routes.get(i).toString());
        }
        System.out.println("-----------------------------------------");
    }
}

