package reto1.Challenge;

import java.util.Scanner;

public class ReservationManager {

        private static BuyTicket system = new BuyTicket();
        private static Scanner scanner = new Scanner(System.in);

        private static Reserva[] reservations = new Reserva[100];
        private static int nextReservationId = 1;
        private static int reservationCount = 0;

        public static void main(String[] args) {
            System.out.println(" Welcome To The RENFE System !");
            showMainMenu();
        }

        private static void showMainMenu() {
            int choice = -1;
            while (choice != 5) {
                System.out.println("\n/N --- MENÚ ---");
                System.out.println("1. Search for Train");
                System.out.println("2. Buy Ticket");
                System.out.println("3. Modify Seat");
                System.out.println("4. Delete Reservation");
                System.out.println("5. Exit");
                System.out.print("Choose an Option: ");

                try {
                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1:
                            case 2:
                                searchAndBookFlow();
                                break;
                            case 3:
                                ModifyReserve.modifyReservation(reservations, reservationCount, scanner);
                                break;
                            case 4:
                                DeleteReservation.eliminarReserva(reservations, reservationCount, system);
                                break;
                            case 5:
                                System.out.println("System Resection. ¡Good Bye!");
                                break;
                            default:
                                System.out.println("Invalid Option. Choose again");
                        }
                    } else {
                        String invalidInput = scanner.nextLine();
                        System.out.println("Error: \"Input Mismatch Exception\". Please, insert a valid Number");
                    }
                } catch (Exception e) {
                    System.out.println("Unexpected Error Ocurred: " + e.getMessage());
                }
            }
        }

        private static void searchAndBookFlow() {
            Route[] foundRoutes = SearchTrain.searchAndDisplay(system, scanner);

            if (foundRoutes.length == 0) {
                return;
            }

            System.out.print("\n¿You want to buy a Ticket for one of these Tours? (y/n): ");
            String buyOption = scanner.nextLine().trim().toLowerCase();

            if (buyOption.equals("y")) {
                System.out.print("Select the number of the Tour: ");
                if (scanner.hasNextInt()) {
                    int routeIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (routeIndex > 0 && routeIndex <= foundRoutes.length) {
                        processBooking(foundRoutes[routeIndex - 1]);
                    } else {
                        System.out.println("Invalid Tour.");
                    }
                } else {
                    System.out.println("Invalid Input.");
                    scanner.nextLine();
                }
            }
        }

        private static void processBooking(Route selectedRoute) {
            System.out.printf("\nYou chose the Tour:\n%s\n", selectedRoute.toString());
            System.out.print("¿How many tickets do you want? ");

            if (scanner.hasNextInt()) {
                int numTickets = scanner.nextInt();
                scanner.nextLine();

                if (numTickets <= 0) {
                    System.out.println("Yoy must buy at least 1 Ticket.");
                    return;
                }

                if (selectedRoute.getAvailableSeats() < numTickets) {
                    System.out.printf("there are only %d abailabe seats. You Can't buy %d Ticktes.\n",
                            selectedRoute.getAvailableSeats(), numTickets);
                    return;
                }

                if (reservationCount + numTickets > reservations.length) {
                    System.out.println("ERROR: Maximun capacity of Reserve (" + reservations.length + ") Exceded.");
                    return;
                }

                double totalCost = numTickets * selectedRoute.getPrice();
                System.out.printf("total cost of %d Tickets: %.2f€\n", numTickets, totalCost);
                System.out.print("Confirm transaction? (y/n): ");
                String confirmation = scanner.nextLine().trim().toLowerCase();

                if (confirmation.equals("y")) {
                    if (selectedRoute.bookTickets(numTickets)) {
                        for (int i = 0; i < numTickets; i++) {
                            Seat seat = new Seat(Seat.generateSeatNumber(selectedRoute.getAvailableSeats() + numTickets - i));
                            Reserva newReserva = new Reserva(nextReservationId, selectedRoute, seat, "Cliente " + nextReservationId);

                            reservations[reservationCount] = newReserva;
                            System.out.printf("¡SUCCESFULL RESERVE! ID: %d, Seat: %s\n", nextReservationId, seat.getNumber());

                            nextReservationId++;
                            reservationCount++;
                        }
                        System.out.println("\n¡SUCCESFULL RESERVE!");
                    } else {
                        System.out.println("UNSUCCESSFULL RESERVE: Seats could not be reserved.");
                    }
                } else {
                    System.out.println("Purchase cancelled. Returning to the main menu.");
                }
            } else {
                System.out.println("Entry not valid for the number of tickets.");
                scanner.nextLine();
            }
        }
    }
