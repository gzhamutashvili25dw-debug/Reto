package reto1.Challenge;

public class Seat {
    private String number;
    private boolean isReserved;

    public Seat(String number) {
        this.number = number;
        this.isReserved = true;
    }

    public String getNumber() {
        return number;
    }

    public void liberar() {
        this.isReserved = false;
        System.out.println("Asiento " + number + " liberado.");
    }

    // Lógica simple para generar un número de asiento (e.g., 1A, 2D)
    public static String generateSeatNumber(int index) {
        int row = (index / 4) + 1;
        char column = (char) ('A' + (index % 4));
        return row + String.valueOf(column);
    }
}