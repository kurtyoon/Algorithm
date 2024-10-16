import java.io.*;
import java.util.*;

public class Solution {

    public static class ParkingCalculator {
        private final int A, B, C;
        private final int[] cnt;
        private int totalCost;

        public ParkingCalculator(int A, int B, int C) {
            this.A = A;
            this.B = B;
            this.C = C;

            this.cnt = new int[104];
            this.totalCost = 0;
        }

        public void addParkingTime(int start, int end) {
            for (int i = start; i < end; i++) {
                cnt[i]++;
            }
        }

        public void calculateTotalCost() {
            for (int i = 1; i < 100; i++) {
                if (cnt[i] > 0) {
                    if (cnt[i] == 1) {
                        totalCost += A;
                    } else if (cnt[i] == 2) {
                        totalCost += B * 2;
                    } else if (cnt[i] == 3) {
                        totalCost += C * 3;
                    }
                }
            }
        }

        public int getTotalCost() {
            return totalCost;
        }
    }

    public int solution(int A, int B, int C, List<int[]> parkingTimes) {
        ParkingCalculator calculator = new ParkingCalculator(A, B, C);

        // 주차 시간을 처리
        for (int[] time : parkingTimes) {
            calculator.addParkingTime(time[0], time[1]);
        }

        // 요금 계산
        calculator.calculateTotalCost();

        // 총 요금 반환
        return calculator.getTotalCost();
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            // 요금 입력 받기
            String[] rateInput = br.readLine().split(" ");
            int A = Integer.parseInt(rateInput[0]);
            int B = Integer.parseInt(rateInput[1]);
            int C = Integer.parseInt(rateInput[2]);

            // 주차 시간 입력 받기
            List<int[]> parkingTimes = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                String[] timeInput = br.readLine().split(" ");
                int start = Integer.parseInt(timeInput[0]);
                int end = Integer.parseInt(timeInput[1]);
                parkingTimes.add(new int[]{start, end});
            }

            // Solution 객체 생성 및 solution 메서드 호출
            Main main = new Main();
            int totalCost = main.solution(A, B, C, parkingTimes);

            // 결과 출력
            bw.write(totalCost + "\n");

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("오류 발생: " + e.getMessage());
        }
    }
}