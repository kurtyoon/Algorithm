import java.io.*;

public class Solution {

    public static class TimeUtils {
        public static String formatTime(int minutes) {
            String hours = String.format("%02d", minutes / 60);
            String mins = String.format("%02d", minutes % 60);
            return hours + ":" + mins;
        }

        public static int convertToMinutes(String time) {
            String[] parts = time.split(":");
            return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
        }
    }

    public static class ScoreManager {
        private int scoreA = 0;
        private int scoreB = 0;
        private int totalTimeA = 0;
        private int totalTimeB = 0;
        private String lastTime = "00:00";

        public void updateScore(int team, String currentTime) {
            if (scoreA > scoreB) {
                totalTimeA += TimeUtils.convertToMinutes(currentTime) - TimeUtils.convertToMinutes(lastTime);
            } else if (scoreB > scoreA) {
                totalTimeB += TimeUtils.convertToMinutes(currentTime) - TimeUtils.convertToMinutes(lastTime);
            }
            lastTime = currentTime;

            if (team == 1) {
                scoreA++;
            } else {
                scoreB++;
            }
        }

        public void finalizeGame(String endTime) {
            if (scoreA > scoreB) {
                totalTimeA += TimeUtils.convertToMinutes(endTime) - TimeUtils.convertToMinutes(lastTime);
            } else if (scoreB > scoreA) {
                totalTimeB += TimeUtils.convertToMinutes(endTime) - TimeUtils.convertToMinutes(lastTime);
            }
        }

        public String getResultA() {
            return TimeUtils.formatTime(totalTimeA);
        }

        public String getResultB() {
            return TimeUtils.formatTime(totalTimeB);
        }
    }

    public static String[] solution(int n, int[][] events) {
        ScoreManager manager = new ScoreManager();

        for (int[] event : events) {
            int team = event[0];
            String time = String.format("%02d:%02d", event[1] / 60, event[1] % 60);
            manager.updateScore(team, time);
        }

        manager.finalizeGame("48:00");

        return new String[]{manager.getResultA(), manager.getResultB()};
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(br.readLine());
            int[][] events = new int[n][2];

            for (int i = 0; i < n; i++) {
                String[] input = br.readLine().split(" ");
                events[i][0] = Integer.parseInt(input[0]);
                events[i][1] = TimeUtils.convertToMinutes(input[1]);
            }

            String[] result = solution(n, events);

            bw.write(result[0] + "\n");
            bw.write(result[1] + "\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("입출력 오류 발생: " + e.getMessage());
        }
    }
}