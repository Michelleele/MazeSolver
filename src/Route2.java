//import java.util.ArrayList;
//
//public class Route2 {
//
//    private ArrayList<Dot> dotArray = new ArrayList<Dot>();
//    private String[][] map;
//    private Dot mostRecentFork;
//
//
//    public Route2(String[][] mapArray) {
//        map = mapArray;
//        appendDotArray();
//    }
//
//    private void appendDotArray() {
//        Dot previous = new Dot(map, 0, 0);
//        dotArray.add(previous);
//        Dot current = evaluateFirst(previous);
//        System.out.println("current = " + current);
//
//        while ((current.getXCoordinate() < map[0].length - 1) || (current.getYCoordinate() < map.length - 1)) {
//            if (isFork(current)) {
//                mostRecentFork = current;
//
//            }
//            dotArray.add(current);
//            previous = new Dot(map, current.getXCoordinate(), current.getYCoordinate());
//            current = evaluatePoint(previous);
//
//
//        }
//        dotArray.add(current);
//    }
//
//    private Dot evaluateFirst(Dot first) {
//        if (first.isIfBottom()) {
//            return new Dot(map, 0, 1);
//        }
//        if (first.isIfRight()) {
//            return new Dot(map, 1, 0);
//        }
//        return null;
//    }
//
//    //能用
//    private ArrayList<Dot> findAllPossibleOutcome(Dot previous) {
//        ArrayList<Dot> possiblePoints = new ArrayList<Dot>();
//        if (previous.isIfBottom()) {
//            possiblePoints.add(moveBottom(previous));
//        }
//        if (previous.isIfLeft()) {
//            possiblePoints.add(moveLeft(previous));
//        }
//        if (previous.isIfTop()) {
//            possiblePoints.add(moveTop(previous));
//        }
//        if (previous.isIfRight()) {
//            possiblePoints.add(moveRight(previous));
//        }
//        return possiblePoints;
//    }
//
//    private ArrayList<Dot> searchPrevious(ArrayList<Dot> allPossibleSolutions) {
//        ArrayList<Dot> results = new ArrayList<Dot>();
//        for (Dot dot : allPossibleSolutions) {
//            if (!isFound(dot)) {
//                results.add(dot);
//            }
//        }
//        return results;
//    }
//
//    private boolean isDeadEnd(Dot dot) {
//        int count = 0;
//        if (dot.isIfTop()) {
//            count++;
//        }
//        if (dot.isIfLeft()) {
//            count++;
//        }
//        if (dot.isIfBottom()) {
//            count++;
//        }
//        if (dot.isIfRight()) {
//            count++;
//        }
//        if (count <= 1) {
//            return true;
//        }
//        return false;
//    }
//
//    private Dot findCorrectRoute(ArrayList<Dot> allPossibleSolutions) {
//        Dot previous;
//        Dot current = allPossibleSolutions.get(0);
//        ArrayList<Dot> possibleMoves;
//
//
//        while (isFork(current)) {
//            for (Dot dot : allPossibleSolutions) {
//                previous = dot;
//                System.out.println("Here");
//                current = dot;
//                System.out.println(current);
//
//                while (!isDeadEnd(current)) {
//
//                    possibleMoves = findAllPossibleOutcome(current);
//                    if (current.isIfRight()) {
//                        current = moveRight(previous);
//                    } else if (current.isIfTop()) {
//                        current = moveTop(previous);
//                    } else if (current.isIfBottom()) {
//                        current = moveBottom(previous);
//                    } else if (current.isIfLeft()) {
//                        current = moveLeft(previous);
//                    }
//                    previous = new Dot(map, current.getXCoordinate(), current.getYCoordinate());
//                }
//
//                if ((current.getYCoordinate() == map.length - 1) && (current.getXCoordinate() == map[0].length - 1)) {
//                    System.out.println("If false");
//                    return dot;
//                }
//                System.out.println("-------------------------------------------");
//            }
//        }
//
//        return null;
//    }
//
//    private Dot moveTop(Dot previous) {
//        int y = previous.getYCoordinate() - 1;
//        return new Dot(map, previous.getXCoordinate(), y);
//    }
//
//    private Dot moveBottom(Dot previous) {
//        int y = previous.getYCoordinate() + 1;
//        return new Dot(map, previous.getXCoordinate(), y);
//    }
//
//    private Dot moveLeft(Dot previous) {
//        int x = previous.getXCoordinate() - 1;
//        return new Dot(map, x, previous.getYCoordinate());
//    }
//
//    private Dot moveRight(Dot previous) {
//        int x = previous.getXCoordinate() + 1;
//        return new Dot(map, x, previous.getYCoordinate());
//    }
//
//    private boolean isFound(Dot target) {
//        for (Dot dot : dotArray) {
//            if ((dot.getYCoordinate() == target.getYCoordinate()) && (dot.getXCoordinate() == target.getXCoordinate())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean isFork(Dot dot) {
//        int count = 0;
//        if (dot.isIfTop()) {
//            count++;
//        }
//        if (dot.isIfLeft()) {
//            count++;
//        }
//        if (dot.isIfBottom()) {
//            count++;
//        }
//        if (dot.isIfRight()) {
//            count++;
//        }
//        if (count >= 3) {
//            return true;
//        }
//        return false;
//    }
//
//    private boolean isRouteValid(Dot first) {
//        Dot current = first;
//        ArrayList<Dot> possibleMoves;
//        while (!isDeadEnd(current)) {
//            while (isFork(current)) {
//
//            }
//        }
//    }
//
//    private Dot evaluatePoint(Dot previousDot) {
//        ArrayList<Dot> possiblePoints = findAllPossibleOutcome(previousDot);
//        possiblePoints = searchPrevious(possiblePoints);
//        if (possiblePoints.size() == 1) {
//            return possiblePoints.get(0);
//        }
//        else {
//            for (Dot dot : possiblePoints) {
//                //if the route led by the dot works
//            }
//        }
//    }
//
//    public String toString() {
//        String answer = "";
//        int index = 0;
//        for (Dot dot : dotArray) {
//            answer += "(" + dot.getYCoordinate() + ", " + dot.getXCoordinate() + ") ";
//            if (index != dotArray.size() - 1) {
//                answer += "---> ";
//            }
//            index++;
//        }
//        return answer;
//    }
//
//}