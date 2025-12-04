package reto1.Challenge;

import java.util.Scanner;

public class DeleteReservation {

    public static void eliminarReserva(Reserva[] reservas, int reservationCount, BuyTicket system) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- DELETE RESERVE ---");
        System.out.println("¿Which reservation do you want to delete? (Enter ID)");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid ID. Must be a number.");
            sc.nextLine();
            return;
        }
        int resId = sc.nextInt();
        sc.nextLine();

        int indexToDelete = -1;
        for (int i = 0; i < reservationCount; i++) {
            if (reservas[i] != null && reservas[i].getId() == resId) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == -1) {
            System.out.println("The Reserve with ID " + resId + " doesn't exist.\n");
            return;
        }

        Reserva reservationToDelete = reservas[indexToDelete];
        reservationToDelete.mostrarReserva();

        System.out.println("¿I'm sure you want to cancel the reservation? (y/n)");
        String seguro = sc.nextLine().trim();

        if (seguro.equalsIgnoreCase("s")) {
            if (reservationToDelete.getRoute().refundTicket()) {
                System.out.println("Seat availability restored on the route.");
            }

            if (reservationToDelete.getAsiento() != null) {
                reservationToDelete.getAsiento().liberar();
            }

            reservas[indexToDelete] = null;
            System.out.println("Reservation successfully deleted.\n");
        } else {
            System.out.println("Elimination canceled.\n");
        }
    }
}