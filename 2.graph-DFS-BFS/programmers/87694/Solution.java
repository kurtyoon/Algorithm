import java.util.*;

class Solution {

    public static class Position {
        private final int x;
        private final int y;

        static final int[] dx = {1, 0, -1, 0};
        static final int[] dy = {0, 1, 0, -1};

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position getNextPosition(int direction) {
            return new Position(x + dx[direction], y + dy[direction]);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Position other = (Position) obj;
            return this.x == other.x && this.y == other.y;
        }
    }

    public static class Rectangle {
        private final Position startPosition;
        private final Position endPosition;

        public Rectangle(int[] rectangleCoordinate) {
            this.startPosition = new Position(rectangleCoordinate[0] * 2, rectangleCoordinate[1] * 2);
            this.endPosition = new Position(rectangleCoordinate[2] * 2, rectangleCoordinate[3] * 2);
        }

        public boolean isOnBorder(Position position) {

            // 상하 좌우 테두리 확인
            if ((position.x == startPosition.x || position.x == endPosition.x) && position.y >= startPosition.y && position.y <= endPosition.y) {
                return true;
            }
            if ((position.y == startPosition.y || position.y == endPosition.y) && position.x >= startPosition.x && position.x <= endPosition.x) {
                return true;
            }
            return false;
        }

        // 주어진 좌표가 직사각형 내부에 있는지 확인하는 메서드
        public boolean isInside(Position position) {
            return position.x > startPosition.x && position.x < endPosition.x && position.y > startPosition.y && position.y < endPosition.y;
        }
    }

    public static class Board {
        private final List<Rectangle> rectangles;
        private final int row;
        private final int col;
        private final boolean[][] visited;
        private int shortestPathLength;

        private final Queue<Position> queue;

        public Board(int row, int col, int[][] rectangleCoordinates) {
            this.rectangles = new ArrayList<>();
            this.row = row;
            this.col = col;
            this.visited = new boolean[row + 1][col + 1];

            this.queue = new LinkedList<>();
            this.shortestPathLength = 0;

            for (int[] rectangleCoordinate : rectangleCoordinates) {
                this.rectangles.add(new Rectangle(rectangleCoordinate));
            }
        }

        public int getShortestPahtLength(Position startPosition, Position endPosition) {
            queue.add(startPosition);
            visited[startPosition.y][startPosition.x] = true;

            shortestPathLength = 0;

            while (!queue.isEmpty()) {
                int size = queue.size();
                shortestPathLength++;

                for (int i = 0; i < size; i++) {
                    Position currentPosition = queue.poll();

                    if (currentPosition.equals(endPosition)) {
                        return shortestPathLength / 2;
                    }

                    for (int direction = 0; direction < 4; direction++) {
                        Position nextPosition = currentPosition.getNextPosition(direction);

                        if (isValidPosition(nextPosition)) {
                            visited[nextPosition.y][nextPosition.x] = true;
                            queue.add(nextPosition);
                        }
                    }
                }
            }

            return -1;
        }

        private boolean isValidPosition(Position currentPosition) {
            if (currentPosition.x < 0 || currentPosition.x > col || currentPosition.y < 0 || currentPosition.y > row) {
                return false;
            }

            boolean isOnBorder = false;
            boolean isInside = false;

            for (Rectangle rectangle : rectangles) {
                if (rectangle.isInside(currentPosition)) {
                    isInside = true;
                    break;
                }
                if (rectangle.isOnBorder(currentPosition)) {
                    isOnBorder = true;
                }
            }

            return isOnBorder && !isInside && !visited[currentPosition.y][currentPosition.x];
        }
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        Board board = new Board(100, 100, rectangle);

        Position characterPosition = new Position(characterX * 2, characterY * 2);
        Position itemPosition = new Position(itemX * 2, itemY * 2);

        return board.getShortestPahtLength(characterPosition, itemPosition);
    }
}