package reto1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketReservationSystem {
    private final List<TrainRoute> allRoutes;
    private final int INITIAL_SEATS = 25;

    public TicketReservationSystem() {
        this.allRoutes = new ArrayList<>();
        initializeRoutes();
    }

    private void initializeRoutes() {

        String[][] stringData = {
                {"Asturias", "San Sebastián", "08:00", "11:50", "3h 50 min", "ALVIA"},
                {"Cartagena", "San Sebastián", "07:00", "15:50", "8h 50 min", "INTERCITY + ALVIA"},
                {"Madrid", "Cádiz", "10:00", "13:50", "3h 50 min", "AVE"},
                {"Valencia", "Cádiz", "06:45", "14:10", "7h 25 min", "AVE + MD"},
                {"Madrid", "Bilbao", "09:00", "13:30", "4h 30 min", "ALVIA"},
                {"Valencia", "Bilbao", "11:30", "19:20", "7h 50 min", "INTERCITY"},
                {"Sevilla", "Bilbao", "07:45", "15:30", "7h 45 min", "AVE + ALVIA"},
                {"Bilbao", "Málaga", "09:00", "16:40", "7h 40 min", "ALVIA + AVE"},
                {"Cartagena", "Málaga", "12:00", "18:20", "6h 20 min", "INTERCITY"},
                {"Bilbao", "Cartagena", "06:45", "14:40", "7h 55 min", "INTERCITY"},
                {"Málaga", "Cartagena", "07:30", "13:50", "6h 20 min", "INTERCITY"},
                {"Barcelona", "Madrid", "08:30", "11:15", "2h 45 min", "AVE"},
                {"Málaga", "Madrid", "12:10", "14:50", "2h 40 min", "AVE"},
                {"Alicante", "Madrid", "11:45", "14:00", "2h 15 min", "AVE"},
                {"Cádiz", "Sevilla", "11:00", "12:35", "1h 35 min", "Media Distancia"},
                {"Cartagena", "Sevilla", "09:00", "15:15", "6h 15 min", "INTERCITY"},
                {"Bilbao", "Asturias", "08:10", "11:35", "3h 25 min", "ALVIA"},
                {"Sevilla", "Asturias", "07:00", "13:55", "6h 55 min", "ALVIA"}
        };

        double[] prices = {
                37.80, 105.00, 89.00, 119.00, 65.00, 85.50, 99.90, 109.00,
                78.50, 78.50, 78.50, 85.00, 88.90, 72.00, 24.00, 81.00, 32.50, 82.10
        };

        for (int i = 0; i < stringData.length; i++) {
            String[] d = stringData[i];
            allRoutes.add(new TrainRoute(
                    d[0], d[1], d[2], d[3], d[4], d[5], prices[i], INITIAL_SEATS
            ));
        }
    }


    public List<String> getAllStations() {
        return allRoutes.stream()
                .flatMap(route -> List.of(route.getOrigin(), route.getDestination()).stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<TrainRoute> getAllRoutes() {
        return allRoutes;
    }

    public List<TrainRoute> searchRoutes(String origin, String destination) {
        String standardizedOrigin = standardizeStationName(origin);
        String standardizedDestination = standardizeStationName(destination);

        return allRoutes.stream()
                .filter(route -> route.getOrigin().equalsIgnoreCase(standardizedOrigin) &&
                        route.getDestination().equalsIgnoreCase(standardizedDestination))
                .collect(Collectors.toList());
    }

    private String standardizeStationName(String name) {
        if (name == null || name.isEmpty()) return name;
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}

