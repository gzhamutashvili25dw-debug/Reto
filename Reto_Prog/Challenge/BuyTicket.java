package reto1.Challenge;

public class BuyTicket {

        private final Route[] allRoutes;
        private final int INITIAL_SEATS = 25;
        private final int NUM_ROUTES = 5;
        private final int routeCount;

        public BuyTicket() {
            this.allRoutes = new Route[NUM_ROUTES];
            this.routeCount = initializeRoutes();
        }

        private int initializeRoutes() {
            allRoutes[0] = new Route("Madrid", "Barcelona", "08:00", "11:00", "3h 00m", "AVE", 55.00, INITIAL_SEATS);
            allRoutes[1] = new Route("Barcelona", "Valencia", "12:30", "14:15", "1h 45m", "Talgo", 35.50, INITIAL_SEATS);
            allRoutes[2] = new Route("Sevilla", "Madrid", "15:00", "17:45", "2h 45m", "AVE", 62.00, INITIAL_SEATS);
            allRoutes[3] = new Route("Madrid", "Sevilla", "10:00", "12:45", "2h 45m", "AVE", 62.00, INITIAL_SEATS);
            allRoutes[4] = new Route("Bilbao", "Madrid", "07:30", "13:00", "5h 30m", "Alvia", 48.00, INITIAL_SEATS);
            return NUM_ROUTES;
        }

        public Route[] searchRoutes(String origin, String destination) {
            String standardizedOrigin = standardizeStationName(origin);
            String standardizedDestination = standardizeStationName(destination);

            int matchCount = 0;
            for (int i = 0; i < routeCount; i++) {
                Route route = allRoutes[i];
                if (route.getOrigin().equalsIgnoreCase(standardizedOrigin) &&
                        route.getDestination().equalsIgnoreCase(standardizedDestination)) {
                    matchCount++;
                }
            }

            Route[] foundRoutes = new Route[matchCount];
            int foundIndex = 0;

            for (int i = 0; i < routeCount; i++) {
                Route route = allRoutes[i];
                if (route.getOrigin().equalsIgnoreCase(standardizedOrigin) &&
                        route.getDestination().equalsIgnoreCase(standardizedDestination)) {
                    foundRoutes[foundIndex++] = route;
                }
            }

            return foundRoutes;
        }

        private String standardizeStationName(String name) {
            if (name == null || name.isEmpty()) return name;
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }
    }
