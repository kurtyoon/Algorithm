import java.util.*;

class Solution {

    public static class Position {
        private final int y;
        private final int x;

        static final int[] dy = {1, 0, -1, 0};
        static final int[] dx = {0, 1, 0, -1};

        Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Position position = (Position) obj;
            return y == position.y && x == position.x;
        }

        public Position getNextPosition(int direction) {
            return new Position(y + dy[direction], x + dx[direction]);
        }

        public boolean isValidPosition(Position startPosition, Position endPosition) {
            return y >= startPosition.y && y <= endPosition.y && x >= startPosition.x && x <= endPosition.x;
        }
    }

    public static class Board {

        private final int[][] maps;
        private final int[][] visited;
        private final int col;
        private final int row;
        private final Queue<Position> queue;

        public Board(int[][] maps) {
            this.maps = maps;
            this.col = maps.length;
            this.row = maps[0].length;
            this.visited = new int[col][row];
            this.queue = new LinkedList<>();
        }

        public int getShortestPathLength(Position startPosition, Position endPosition) {

            queue.add(startPosition);
            visited[startPosition.y][startPosition.x] = 1;

            while (!queue.isEmpty()) {

                Position currPosition = queue.poll();

                if (currPosition.equals(endPosition)) {
                    return visited[currPosition.y][currPosition.x];
                }

                for (int i = 0; i < 4; i++) {
                    Position nextPosition = currPosition.getNextPosition(i);

                    if (!nextPosition.isValidPosition(startPosition, endPosition)) {
                        continue;
                    }

                    if (maps[nextPosition.y][nextPosition.x] == 1 && visited[nextPosition.y][nextPosition.x] == 0) {
                        queue.offer(nextPosition);
                        visited[nextPosition.y][nextPosition.x] = visited[currPosition.y][currPosition.x] + 1;
                    }
                }
                
            }

            return -1;
        }
    }

    public int solution(int[][] maps) {

        Position startPosition = new Position(0, 0);
        Position endPosition = new Position(maps.length - 1, maps[0].length - 1);

        Board board = new Board(maps);

        return board.getShortestPathLength(startPosition, endPosition);
    }
}