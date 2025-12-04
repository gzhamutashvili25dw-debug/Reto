package reto1;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class TicketReservationSystem {

    private final TrainRoute[] allRoutes;
    private final int INITIAL_SEATS = 25;
    private final int NUM_ROUTES = 18;

    public TicketReservationSystem() {
        this.allRoutes = new TrainRoute[NUM_ROUTES];
        initializeRoutes();
    }

    private void initializeRoutes() { }

    public TrainRoute[] getAllRoutes() {
        return allRoutes;
    }

    public String[] getAllStations() {
        List<String> uniqueStations = new ArrayList<>();

        for (TrainRoute route : allRoutes) {
            String origin = route.getOrigin();
            String destination = route.getDestination();

            if (!uniqueStations.contains(origin)) {
                uniqueStations.add(origin);
            }

            if (!uniqueStations.contains(destination)) {
                uniqueStations.add(destination);
            }
        }

        Collections.sort(uniqueStations);

        return uniqueStations.toArray(new String[0]);
    }

    public TrainRoute[] searchRoutes(String origin, String destination) {
        String standardizedOrigin = standardizeStationName(origin);
        String standardizedDestination = standardizeStationName(destination);

        List<TrainRoute> foundRoutes = new ArrayList<>();

        for (TrainRoute route : allRoutes) {

            if (route.getOrigin().equalsIgnoreCase(standardizedOrigin) &&
                    route.getDestination().equalsIgnoreCase(standardizedDestination)) {

                foundRoutes.add(route);
            }
        }

        return foundRoutes.toArray(new TrainRoute[0]);
    }

    private String standardizeStationName(String name) {
        if (name == null || name.isEmpty()) return name;

        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}