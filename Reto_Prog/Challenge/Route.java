package reto1.Challenge;

public class Route {
        private final String origin;
        private final String destination;
        private final String departureTime;
        private final String arrivalTime;
        private final String duration;
        private final String trainType;
        private final double price;
        private int availableSeats;
        private final int MAX_SEATS = 100;

        public Route(String origin, String destination, String departureTime, String arrivalTime,
                     String duration, String trainType, double price, int initialSeats) {
            this.origin = origin;
            this.destination = destination;
            this.departureTime = departureTime;
            this.arrivalTime = arrivalTime;
            this.duration = duration;
            this.trainType = trainType;
            this.price = price;
            this.availableSeats = initialSeats;
        }

        public String getOrigin() { return origin; }
        public String getDestination() { return destination; }
        public double getPrice() { return price; }
        public int getAvailableSeats() { return availableSeats; }

        public boolean bookTickets(int numberOfTickets) {
            if (numberOfTickets > 0 && availableSeats >= numberOfTickets) {
                availableSeats -= numberOfTickets;
                return true;
            }
            return false;
        }

        public boolean refundTicket() {
            if (availableSeats < MAX_SEATS) {
                availableSeats += 1;
                return true;
            }
            return false;
        }

        public String toString() {
            return String.format("%s -> %s | Departure: %s | Arrival: %s | Duration: %s | Train: %s | Price: %.2f€ | Seats Available: %d",
                    origin, destination, departureTime, arrivalTime, duration, trainType, price, availableSeats);
        }
    }
