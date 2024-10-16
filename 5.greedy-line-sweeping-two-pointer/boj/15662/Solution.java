import java.io.*;
import java.util.*;

public class Main {

    public static class Gear {
        private final Deque<Character> teeth;

        public Gear(String initialState) {
            teeth = new ArrayDeque<>();
            for (char c : initialState.toCharArray()) {
                teeth.add(c);
            }
        }

        public void rotate(int direction) {
            if (direction == 0) {
                teeth.addLast(teeth.pollFirst());
            } else {
                teeth.addFirst(teeth.pollLast());
            }
        }

        public char getLeftTooth() {
            return getNthTooth(6);
        }

        public char getRightTooth() {
            return getNthTooth(2);
        }

        public char getFrontTooth() {
            return teeth.peekFirst();
        }

        private char getNthTooth(int n) {
            Iterator<Character> iterator = teeth.iterator();
            for (int i = 0; i < n; i++) {
                iterator.next();
            }
            return iterator.next();
        }
    }

    public static class GearSystem {
        private final Gear[] gears;
        private final int n;

        public GearSystem(int n, String[] initialStates) {
            this.n = n;
            gears = new Gear[n];
            for (int i = 0; i < n; i++) {
                gears[i] = new Gear(initialStates[i]);
            }
        }

        public void rotateGears(int index, int direction) {
            int leftLimit = findLeftLimit(index);
            int rightLimit = findRightLimit(index);

            int cnt = 0;
            for (int pos = index; pos >= leftLimit; pos--) {
                gears[pos].rotate(cnt % 2 == 0 ? direction : 1 - direction);
                cnt++;
            }

            cnt = 1;
            for (int pos = index + 1; pos <= rightLimit; pos++) {
                gears[pos].rotate(cnt % 2 == 0 ? direction : 1 - direction);
                cnt++;
            }
        }

        private int findLeftLimit(int index) {
            for (int i = index; i >= 1; i--) {
                if (gears[i].getLeftTooth() == gears[i - 1].getRightTooth()) {
                    return i;
                }
            }
            return 0;
        }

        private int findRightLimit(int index) {
            for (int i = index; i < n - 1; i++) {
                if (gears[i].getRightTooth() == gears[i + 1].getLeftTooth()) {
                    return i;
                }
            }
            return n - 1;
        }

        public int countActiveFrontTeeth() {
            int count = 0;
            for (Gear gear : gears) {
                if (gear.getFrontTooth() == '1') {
                    count++;
                }
            }
            return count;
        }
    }

    public static int solution(int n, int k, String[] initialStates, int[][] operations) {
        GearSystem gearSystem = new GearSystem(n, initialStates);

        for (int[] operation : operations) {
            int gearIndex = operation[0] - 1;
            int direction = (operation[1] == -1) ? 0 : 1;
            gearSystem.rotateGears(gearIndex, direction);
        }

        return gearSystem.countActiveFrontTeeth();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        String[] initialStates = new String[n];
        for (int i = 0; i < n; i++) {
            initialStates[i] = br.readLine();
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        int[][] operations = new int[k][2];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            operations[i][0] = Integer.parseInt(st.nextToken());
            operations[i][1] = Integer.parseInt(st.nextToken());
        }

        int result = solution(n, k, initialStates, operations);
        bw.write(result + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}