package reto1.Challenge;
import java.util.Scanner;

public class ModifyReserve {

    public static void modifyReservation(Reserva[] reservas, int reservationCount, Scanner scanner) {
        System.out.println("\n--- MODIFY SEAT ---");
        System.out.println("Enter the ID of the reservation to modify:");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid ID. Must be a number..");
            scanner.nextLine();
            return;
        }
        int resId = scanner.nextInt();
        scanner.nextLine();

        int indexToModify = -1;
        for (int i = 0; i < reservationCount; i++) {
            if (reservas[i] != null && reservas[i].getId() == resId) {
                indexToModify = i;
                break;
            }
        }

        if (indexToModify == -1) {
            System.out.println("The reserve with ID " + resId + " doesn't exist.\n");
            return;
        }

        Reserva reservationToModify = reservas[indexToModify];
        reservationToModify.mostrarReserva();

        System.out.print("Enter the new seat number (e.x., 5C): ");
        String newSeatNumber = scanner.nextLine().trim().toUpperCase();

        Seat oldSeat = reservationToModify.getAsiento();
        if(oldSeat != null) {
            oldSeat.liberar();
        }

        Seat newSeat = new Seat(newSeatNumber);
        reservationToModify.setAsiento(newSeat);

        System.out.printf("Reserve ID %d modified. Seat changed from %s to %s.\n",
                resId, oldSeat.getNumber(), newSeat.getNumber());
    }
}
