import java.io.*;
import java.util.*;

public class Main {

    public static class MultiTapManager {
        private final int maxDevices;
        private final int[] schedule;
        private final boolean[] isPluggedIn;
        private final List<Integer> pluggedInDevices;
        private int replacementCount;

        public MultiTapManager(int maxDevices, int[] schedule) {
            this.maxDevices = maxDevices;
            this.schedule = schedule;
            this.isPluggedIn = new boolean[104];
            this.pluggedInDevices = new ArrayList<>();
            this.replacementCount = 0;
        }

        public int manage() {
            for (int i = 0; i < schedule.length; i++) {
                int currentDevice = schedule[i];

                if (!isPluggedIn[currentDevice]) {
                    if (pluggedInDevices.size() == maxDevices) {
                        int lastIdx = -1, deviceToReplace = -1;

                        for (int device : pluggedInDevices) {
                            int nextUse = Integer.MAX_VALUE;
                            for (int j = i + 1; j < schedule.length; j++) {
                                if (schedule[j] == device) {
                                    nextUse = j;
                                    break;
                                }
                            }

                            if (nextUse > lastIdx) {
                                lastIdx = nextUse;
                                deviceToReplace = device;
                            }
                        }

                        isPluggedIn[deviceToReplace] = false;
                        pluggedInDevices.remove(Integer.valueOf(deviceToReplace));
                        replacementCount++;
                    }
          
                    pluggedInDevices.add(currentDevice);
                    isPluggedIn[currentDevice] = true;
                }
            }
            return replacementCount;
        }
    }

    public static int solution(int k, int n, int[] schedule) {
        MultiTapManager manager = new MultiTapManager(k, schedule);
        return manager.manage();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] schedule = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            schedule[i] = Integer.parseInt(st.nextToken());
        }

        int result = solution(k, n, schedule);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}