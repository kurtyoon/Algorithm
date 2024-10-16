import java.io.*;
import java.util.*;

public class Solution {

    public static class CommandProcessor {
        private final String commands;
        private final int numberOfElements;
        private final String orderString;
        private Deque<Integer> deque;
        private boolean error;
        private boolean reversed;

        public CommandProcessor(String commands, int numberOfElements, String orderString) {
            this.commands = commands;
            this.numberOfElements = numberOfElements;
            this.orderString = orderString;
            this.deque = new LinkedList<>();
            this.error = false;
            this.reversed = false;

            processOrderString();
        }

        private void processOrderString() {
            int number = 0;
            for (char c : orderString.toCharArray()) {
                if (c == '[' || c == ']') continue;

                if (Character.isDigit(c)) {
                    number = number * 10 + (c - '0');
                } else {
                    if (number > 0) deque.addLast(number);
                    number = 0;
                }
            }

            if (number > 0) deque.addLast(number);
        }

        public String executeCommands() {
            for (char cmd : commands.toCharArray()) {
                if (cmd == 'R') {
                    reversed = !reversed;
                } else if (cmd == 'D') {
                    if (deque.isEmpty()) {
                        error = true;
                        break;
                    }

                    if (reversed) {
                        deque.removeLast();
                    } else {
                        deque.removeFirst();
                    }
                }
            }

            return error ? "error" : formatOutput();
        }

        private String formatOutput() {
            StringBuilder result = new StringBuilder("[");
            List<Integer> elements = new ArrayList<>(deque);

            if (reversed) {
                Collections.reverse(elements);
            }

            for (int i = 0; i < elements.size(); i++) {
                result.append(elements.get(i));
                if (i < elements.size() - 1) {
                    result.append(",");
                }
            }

            result.append("]");
            return result.toString();
        }
    }

    public static String solution(String commands, int n, String orderString) {
        CommandProcessor processor = new CommandProcessor(commands, n, orderString);
        return processor.executeCommands();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String P = br.readLine();
            int N = Integer.parseInt(br.readLine());
            String order = br.readLine();

            String result = solution(P, N, order);
            bw.write(result);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}