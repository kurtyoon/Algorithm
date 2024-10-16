import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static class Position {
        private final int y;
        private final int x;
        private static final int[] dy = {-1, 1, 0, 0};
        private static final int[] dx = {0, 0, 1, -1};

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Position move(int speed, int direction, int R, int C) {
            int ny = this.y;
            int nx = this.x;
            while (speed > 0) {
                if (direction <= 1) {
                    if (direction == 0) {
                        if (ny - speed < 0) {
                            speed -= ny;
                            ny = 0;
                            direction = 1;
                        } else {
                            ny -= speed;
                            break;
                        }
                    } else {
                        if (ny + speed >= R) {
                            speed -= (R - 1 - ny);
                            ny = R - 1;
                            direction = 0;
                        } else {
                            ny += speed;
                            break;
                        }
                    }
                } else {
                    if (direction == 2) {
                        if (nx + speed >= C) {
                            speed -= (C - 1 - nx);
                            nx = C - 1;
                            direction = 3;
                        } else {
                            nx += speed;
                            break;
                        }
                    } else {
                        if (nx - speed < 0) {
                            speed -= nx;
                            nx = 0;
                            direction = 2;
                        } else {
                            nx -= speed;
                            break;
                        }
                    }
                }
            }
            return new Position(ny, nx);
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

    public static class Shark {
        private Position position;
        private int speed;
        private int direction;
        private final int size;
        private boolean isAlive;

        public Shark(int y, int x, int speed, int direction, int size) {
            this.position = new Position(y, x);
            this.speed = speed;
            this.direction = direction;
            this.size = size;
            this.isAlive = true;
        }

        public void move(int R, int C) {
            if (isAlive) {
                this.position = this.position.move(speed, direction, R, C);
            }
        }

        public Position getPosition() {
            return position;
        }

        public int getSize() {
            return size;
        }

        public boolean isAlive() {
            return isAlive;
        }

        public void die() {
            isAlive = false;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }
    }

    public static class FishingSpot {
        private final int[][] sharkMap;
        private final int R;
        private final int C;

        public FishingSpot(int R, int C) {
            this.R = R;
            this.C = C;
            sharkMap = new int[R][C];
        }

        public int fishShark(Shark[] sharks, int column) {
            for (int y = 0; y < R; y++) {
                if (sharkMap[y][column] != 0) {
                    int sharkIndex = sharkMap[y][column] - 1;
                    Shark caughtShark = sharks[sharkIndex];
                    caughtShark.die();
                    sharkMap[y][column] = 0;
                    return caughtShark.getSize();
                }
            }
            return 0;
        }

        public void updateSharkMap(Shark[] sharks, int M) {
            for (int y = 0; y < R; y++) {
                for (int x = 0; x < C; x++) {
                    sharkMap[y][x] = 0;
                }
            }
            for (int i = 0; i < M; i++) {
                if (!sharks[i].isAlive()) continue;
                Position pos = sharks[i].getPosition();
                int ny = pos.getY();
                int nx = pos.getX();
                if (sharkMap[ny][nx] != 0) {
                    Shark existingShark = sharks[sharkMap[ny][nx] - 1];
                    if (existingShark.getSize() < sharks[i].getSize()) {
                        existingShark.die();
                        sharkMap[ny][nx] = i + 1;
                    } else {
                        sharks[i].die();
                    }
                } else {
                    sharkMap[ny][nx] = i + 1;
                }
            }
        }
    }

    public static int solution(int R, int C, int M, Shark[] sharks) {
        FishingSpot fishingSpot = new FishingSpot(R, C);
        int totalCatch = 0;

        for (int t = 0; t < C; t++) {
            totalCatch += fishingSpot.fishShark(sharks, t);
            fishingSpot.updateSharkMap(sharks, M);
            for (Shark shark : sharks) {
                shark.move(R, C);
            }
        }

        return totalCatch;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Shark[] sharks = new Shark[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            if (d <= 1) s %= (2 * (R - 1));
            else s %= (2 * (C - 1));

            sharks[i] = new Shark(y, x, s, d, z);
        }

        int result = solution(R, C, M, sharks);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}