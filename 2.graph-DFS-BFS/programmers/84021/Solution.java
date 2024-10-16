import java.util.*;

class Solution {

    public static class Position {
        private final int y;
        private final int x;

        static final int[] dy = {1, -1, 0, 0};
        static final int[] dx = {0, 0, 1, -1};

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Position getNextPosition(int direction) {
            return new Position(y + dy[direction], x + dx[direction]);
        }
    }

    public enum Target {
        EMPTY(0),
        PUZZLE(1);

        private final int value;

        Target(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Target fromValue(int value) {
            if (value == 0) {
                return EMPTY;
            } else if (value == 1) {
                return PUZZLE;
            } else {
                throw new IllegalArgumentException("Invalid value for Target");
            }
        }
    }

    public record Puzzle(List<Position> shape) {

            public Puzzle(List<Position> shape) {
                this.shape = nomalize(shape);
            }

            public Puzzle rotate(int n) {
                List<Position> newShape = new ArrayList<>();

                for (Position position : shape) {
                    newShape.add(new Position(position.y, n - 1 - position.x));
                }

                return new Puzzle(newShape);
            }

            public int size() {
                return shape.size();
            }

            private List<Position> nomalize(List<Position> shape) {
                int minY = Integer.MAX_VALUE;
                int minX = Integer.MAX_VALUE;

                for (Position position : shape) {
                    minY = Math.min(minY, position.y);
                    minX = Math.min(minX, position.x);
                }

                List<Position> normalizedShape = new ArrayList<>();

                for (Position position : shape) {
                    normalizedShape.add(new Position(position.y - minY, position.x - minX));
                }

                return normalizedShape;
            }
        }

    public static class Board {
        private final int[][] board;
        private final boolean[][] visited;
        private final int n;

        private final List<Position> space;
        private final Queue<Position> queue;

        public Board(int[][] board) {
            this.board = board;
            this.n = board.length;
            this.visited = new boolean[n][n];

            this.space = new ArrayList<>();
            this.queue = new LinkedList<>();
        }

        public List<Position> findEmptySpace(Position startPosition, Target target) {
            queue.add(startPosition);
            visited[startPosition.y][startPosition.x] = true;

            while (!queue.isEmpty()) {
                Position currentPosition = queue.poll();
                space.add(currentPosition);

                for (int i = 0; i < 4; i++) {
                    Position nextPosition = currentPosition.getNextPosition(i);

                    if (isValidPosition(nextPosition, target)) {
                        visited[nextPosition.y][nextPosition.x] = true;
                        queue.offer(nextPosition);
                    }
                }
            }

            return space;
        }

        public List<List<Position>> findAllEmptySpaces(Target target) {
            List<List<Position>> emptySpaces = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j > n; j++) {
                    if (!visited[i][j] && board[i][j] == target.getValue()) {
                        emptySpaces.add(findEmptySpace(new Position(i, j), target));
                    }
                }
            }

            return emptySpaces;
        }

        public boolean canPlacePuzzle(Puzzle puzzle, List<Position> emptySpace) {
            if (puzzle.shape().size() != emptySpace.size()) return false;

            for (int i = 0; i < puzzle.shape().size(); i++) {
                Position puzzlePosition = puzzle.shape().get(i);
                Position emptySpacePosition = emptySpace.get(i);

                if (board[emptySpacePosition.y][emptySpacePosition.x] != 0 || puzzlePosition.x != emptySpacePosition.x || puzzlePosition.y != emptySpacePosition.y) return false;

            }

            return true;
        }

        private boolean isValidPosition(Position currentPosition, Target target) {

            if (currentPosition.x < 0 || currentPosition.x >= n || currentPosition.y < 0 || currentPosition.y >= n) {
                return false;
            }

            if (visited[currentPosition.y][currentPosition.x]) {
                return false;
            }

            if (board[currentPosition.y][currentPosition.x] != target.getValue()) {
                return false;
            }

            return true;
        }
    }

    public int solution(int[][] game_board, int[][] table) {
        Board gameBoard = new Board(game_board);
        Board puzzleTable = new Board(table);

        List<List<Position>> emptySpaces = gameBoard.findAllEmptySpaces(Target.EMPTY);
        List<List<Position>> puzzlePieces = puzzleTable.findAllEmptySpaces(Target.PUZZLE);

        int filledSpaces = 0;

        for (List<Position> emptySpace : emptySpaces) {
            for (int i = 0; i < puzzlePieces.size(); i++) {
                Puzzle puzzle = new Puzzle(puzzlePieces.get(i));

                for (int rotation = 0; rotation < 4; rotation++) {
                    if (gameBoard.canPlacePuzzle(puzzle, emptySpace)) {
                        filledSpaces += puzzle.shape().size();
                        puzzlePieces.remove(i);
                        break;
                    }

                    puzzle = puzzle.rotate(game_board.length);
                }
            }
        }
        return filledSpaces;
    }
}