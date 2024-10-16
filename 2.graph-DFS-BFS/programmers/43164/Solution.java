import java.util.*;

class Solution {

    public static class AirPort {
        private final String name;
        private final PriorityQueue<String> destinations;

        public AirPort(String name) {
            this.name = name;
            this.destinations = new PriorityQueue<>(); // 사전순 정렬
        }

        public void addDestination(String destination) {
            destinations.offer(destination);
        }

        public String getName() {
            return name;
        }

        public PriorityQueue<String> getAvailableDestinations() {
            return destinations;
        }
    }

    public static class RoutePlanner {
        private final Map<String, AirPort> airPortGraph;
        private final List<String> travelRoute;

        public RoutePlanner(String[][] tickets) {
            this.airPortGraph = new HashMap<>();
            this.travelRoute = new ArrayList<>();

            buildAirPortGraph(tickets);
        }

        public void findTravelRoute(String startAirPort) {
            findNextDestination(startAirPort);
        }

        public List<String> getTravelRoute() {
            return travelRoute;
        }

        private void buildAirPortGraph(String[][] tickets) {
            for (String[] ticket : tickets) {
                String origin = ticket[0];
                String destination = ticket[1];

                airPortGraph.putIfAbsent(origin, new AirPort(origin));
                airPortGraph.putIfAbsent(destination, new AirPort(destination));

                airPortGraph.get(origin).addDestination(destination);
            }
        }

        private void findNextDestination(String currentAirPort) {
            PriorityQueue<String> availableDestinations = airPortGraph.get(currentAirPort).getAvailableDestinations();

            while (!availableDestinations.isEmpty()) {
                String nextAirPort = availableDestinations.poll();
                findNextDestination(nextAirPort);
            }

            travelRoute.add(0, currentAirPort);
        }
    }

    public String[] solution(String[][] tickets) {
        RoutePlanner routePlanner = new RoutePlanner(tickets);
        routePlanner.findTravelRoute("ICN");

        List<String> finalRoute = routePlanner.getTravelRoute();
        return finalRoute.toArray(new String[0]);
    }
}