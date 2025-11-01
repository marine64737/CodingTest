import java.io.*;
import java.util.*;

public class BenchIO {

    // ---------- FastScanner (byte) ----------
    static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16]; // 64KB
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) { this.in = is; }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do { c = read(); } while (c <= 32);
            if (c == '-') { sign = -1; c = read(); }
            while (c > 32) { val = val * 10 + (c - '0'); c = read(); }
            return val * sign;
        }
    }

    // ---------- StringTokenizer Reader ----------
    static final class TokReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1 << 16);
        StringTokenizer st;
        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }
        int nextInt() throws IOException {
            String s = next();
            return (s == null) ? Integer.MIN_VALUE : Integer.parseInt(s);
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println("Usage: java BenchIO <scanner|buffered|fast>");
            return;
        }
        String mode = args[0];

        long start = System.nanoTime();
        long sum = 0;
        int n;

        switch (mode) {
            case "scanner": {
                Scanner sc = new Scanner(new BufferedInputStream(System.in, 1 << 16));
                n = sc.nextInt();
                for (int i = 0; i < n; i++) sum += sc.nextInt();
                break;
            }
            case "buffered": {
                TokReader tr = new TokReader();
                n = tr.nextInt();
                for (int i = 0; i < n; i++) sum += tr.nextInt();
                break;
            }
            case "fast": {
                FastScanner fs = new FastScanner(System.in);
                n = fs.nextInt();
                for (int i = 0; i < n; i++) sum += fs.nextInt();
                break;
            }
            default:
                System.err.println("Unknown mode: " + mode);
                return;
        }

        long end = System.nanoTime();
        long ms = (end - start) / 1_000_000L;
        // 출력해서 dead-code 제거 방지
        System.out.println(sum);
        System.err.println("elapsed_ms=" + ms);
    }
}
