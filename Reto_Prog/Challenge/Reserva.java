package reto1.Challenge;

public class Reserva {
    private final int id;
    private final Route route;
    private Seat asiento;
    private final String clientName;

    public Reserva(int id, Route route, Seat asiento, String clientName) {
        this.id = id;
        this.route = route;
        this.asiento = asiento;
        this.clientName = clientName;
    }

    public int getId() { return id; }
    public Route getRoute() { return route; }
    public Seat getAsiento() { return asiento; }
    public void setAsiento(Seat nuevoAsiento) { this.asiento = nuevoAsiento; }

    public void mostrarReserva() {
        System.out.printf("\n--- Reserve Detail ID %d ---\n", id);
        System.out.printf("Client: %s\n", clientName);
        System.out.printf("Tour: %s -> %s\n", route.getOrigin(), route.getDestination());
        System.out.printf("Seat Reserved: %s\n", asiento != null ? asiento.getNumber() : "N/A");
        System.out.printf("Cost: %.2f€\n", route.getPrice());
        System.out.println("--------------------------------");
    }
}