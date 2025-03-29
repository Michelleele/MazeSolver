import java.util.ArrayList;

public class Route {

    private ArrayList<Dot> dotArray;
    private String[][] map;
    private ArrayList<Dot> visited;

    public Route (String[][] mapArray) {
        map = mapArray;
        dotArray = new ArrayList<Dot>();
        visited = new ArrayList<Dot>();
        appendDotArray();
    }

    private void appendDotArray() {
        Dot previous = new Dot(map, 0, 0);
        dotArray.add(previous);
        visited.add(previous);
        Dot current = evaluateFirst(previous);

        if (current == null) {
            System.out.println("No valid initial move from the starting point.");
            return;
        }

        if (!isFound(current)) {
            dotArray.add(current);
            visited.add(current);
        }

        while ((current.getXCoordinate() < map[0].length - 1) || (current.getYCoordinate() < map.length - 1)) {
            previous = new Dot(map, current.getXCoordinate(), current.getYCoordinate());
            current = evaluatePoint(previous);

            if (current == null || isFound(current)) {
                System.out.println("No valid route found.");
                return;
            }

            if (!isFound2(current)) {
                dotArray.add(current);
                visited.add(current);
            }
        }
    }

    private boolean isFound2(Dot target) {
        for (Dot dot : visited) {
            if ((dot.getYCoordinate() == target.getYCoordinate()) && (dot.getXCoordinate() == target.getXCoordinate())) {
                return true;
            }
        }
        return false;
    }


    private Dot evaluateFirst(Dot first) {
        if (first.isIfBottom()) {
            return new Dot(map, 0, 1);
        }
        if (first.isIfRight()) {
            return new Dot(map, 1, 0);
        }
        return null;
    }

    //能用
    private ArrayList<Dot> findAllPossibleOutcome(Dot previous) {
        ArrayList<Dot> possiblePoints = new ArrayList<Dot>();
        if (previous.isIfBottom()) {
            possiblePoints.add(moveBottom(previous));
        }
        if (previous.isIfLeft()) {
            possiblePoints.add(moveLeft(previous));
        }
        if (previous.isIfTop()) {
            possiblePoints.add(moveTop(previous));
        }
        if (previous.isIfRight()) {
            possiblePoints.add(moveRight(previous));
        }
        return possiblePoints;
    }

    private ArrayList<Dot> searchPrevious(ArrayList<Dot> allPossibleSolutions) {
        ArrayList<Dot> results = new ArrayList<Dot>();
        for (Dot dot : allPossibleSolutions) {
            if (!isFound(dot)) {
                results.add(dot);
            }
        }
        return results;
    }

    private boolean isDeadEnd(Dot dot) {
        int count = 0;
        if (dot.isIfTop()) {
            count ++;
        }
        if (dot.isIfLeft()) {
            count ++;
        }
        if (dot.isIfBottom()) {
            count ++;
        }
        if (dot.isIfRight()) {
            count ++;
        }
        if (count <= 1) {
            return true;
        }
        return false;
    }

    private boolean isValidRoute(Dot dot) {
        int count = 1;
        Dot previous;
        Dot current = dot;

        ArrayList<Dot> routes;
        ArrayList<Dot> temp = new ArrayList<Dot>();

        if (isFound(dot)) {
            return false;
        }
        dotArray.add(current);
        visited.add(current);
        temp.add(current);

        int MAX_STEPS = 100;
        int steps = 0;

        while (!isDeadEnd(current)) {
            if (steps++ > MAX_STEPS) {
                System.out.println("Reached max steps, stopping to avoid infinite loop.");
                return false;
            }

            previous = new Dot(map, current.getXCoordinate(), current.getYCoordinate());
            routes = searchPrevious(findAllPossibleOutcome(current));

            if (routes.size() > 1) {
                current = findCorrectRoute(routes);
                count ++;
            }
            else {
                if ((current.isIfRight()) && !isFound(moveRight(previous))) {
                    current = moveRight(previous);
                    count ++;
                }
                else if ((current.isIfTop()) && !isFound(moveTop(previous))) {
                    current = moveTop(previous);
                    count ++;
                }
                else if ((current.isIfBottom()) && !isFound(moveBottom(previous))) {
                    current = moveBottom(previous);
                    count ++;
                }
                else if ((current.isIfLeft()) && !isFound(moveLeft(previous))) {
                    current = moveLeft(previous);
                    count ++;
                }
            }

            dotArray.add(current);

            System.out.println("temp = " + dotArray);
            System.out.println(count);


            if ((current != null) && isFound(current)) {
                visited.add(current);
            }

            if (current == null || isFound(current)) {
                return false;
            }

            if ((current.getYCoordinate() == map.length - 1) && (current.getXCoordinate() == map[0].length - 1)) {
                return true;
            }

            visited.add(current);
        }

        return false;
    }



    private Dot findCorrectRoute(ArrayList<Dot> allPossibleSolutions) {

        System.out.println(allPossibleSolutions);

        for (Dot dot : allPossibleSolutions) {
            if (!isFound(dot)) {
                if (isValidRoute(dot)) {
                    return dot;
                }
            }
        }
        return null;
    }

    private Dot moveTop(Dot previous) {
        int y = previous.getYCoordinate() - 1;
        return new Dot(map, previous.getXCoordinate(), y);
    }

    private Dot moveBottom(Dot previous) {
        int y = previous.getYCoordinate() + 1;
        return new Dot(map, previous.getXCoordinate(), y);
    }

    private Dot moveLeft(Dot previous) {
        int x = previous.getXCoordinate() - 1;
        return new Dot(map, x, previous.getYCoordinate());
    }

    private Dot moveRight(Dot previous) {
        int x = previous.getXCoordinate() + 1;
        return new Dot(map, x, previous.getYCoordinate());
    }

    private boolean isFound(Dot target) {
        for (Dot dot : visited) {
            if ((dot.getYCoordinate() == target.getYCoordinate()) && (dot.getXCoordinate() == target.getXCoordinate())) {
                return true;
            }
        }
        return false;
    }

    private Dot evaluatePoint(Dot previousDot) {
        ArrayList<Dot> possiblePoints = findAllPossibleOutcome(previousDot);
        possiblePoints = searchPrevious(possiblePoints);
        if (possiblePoints.size() == 1) {
            return possiblePoints.get(0);
        }
        else {
            return findCorrectRoute(possiblePoints);
        }
    }

    public String toString() {
        String answer = "";
        int index = 0;
        for (Dot dot : dotArray) {
            answer += "(" + dot.getYCoordinate() + ", " + dot.getXCoordinate() + ") ";
            if (index != dotArray.size() - 1) {
                answer += "---> ";
            }
            index ++;
        }
        return answer;
    }

}