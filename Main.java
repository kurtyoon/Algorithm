import java.io.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.out.println("Usage: java Main <JavaFilePath>");
            return;
        }

        File sourceFile = new File(args[0]);
        if (!sourceFile.exists() || !sourceFile.getName().endsWith(".java")) {
            System.out.println("Error: File is not a valid Java source file.");
            return;
        }

        String filePath = sourceFile.getAbsolutePath();
        String fileName = sourceFile.getName();
        String className = fileName.substring(0, fileName.lastIndexOf("."));

        File inputFile = new File("input.txt");
        if (!inputFile.exists()) {
            System.out.println("Error: Input file not found.");
            return;
        }

        // 컴파일
        Process compile = new ProcessBuilder("javac", filePath)
                .inheritIO()
                .start();
        compile.waitFor();

        // 실행 시간 측정 시작
        long startTime = System.nanoTime();

        // 실행 준비
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", sourceFile.getParent(), className);
        pb.directory(sourceFile.getParentFile());
        pb.redirectInput(inputFile);

        Process process = pb.start();

        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }

        int exitCode = process.waitFor();
        long endTime = System.nanoTime();
        long duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        long memoryUsage = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;

        System.out.println("Output:");
        System.out.println(output.toString());
        System.out.println("Time taken: " + duration + "ms");
        System.out.println("Memory usage: " + memoryUsage + "KB");

        if (exitCode != 0) {
            System.out.println("Error: Program exited with code " + exitCode);
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                errorReader.lines().forEach(System.out::println);
            }
        }

        // 컴파일된 클래스 파일 삭제
        File classFile = new File(sourceFile.getParent(), className + ".class");
        if (classFile.exists()) {
            classFile.delete();
        }
    }
}