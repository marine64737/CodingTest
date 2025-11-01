//https://www.acmicpc.net/problem/9205
//First try
import java.io.*;
import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        boolean sad = false;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for (int i=0; i<t; i++){
            double beer = 20;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n+1; j++){
                st = new StringTokenizer(br.readLine());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                if (!sad){
                    if ((Math.abs(x1-x2) + Math.abs(y1-y2))*0.02 > beer){
                        sb.append("sad").append("\n");
                        sad = true;
                    }
                    else if (beer >= 1){
                        beer -= (Math.abs(x1-x2) + Math.abs(y1-y2))*0.02;
                        while (beer <= 19) beer++;
                    }
                }
                x1=x2; y1=y2;
            }
            if (!sad) sb.append("happy").append("\n");
            sad=false;
        }
        System.out.print(sb);
        return;
    }
}

//Second try
import java.io.*;
        import java.util.*;

class Scratch {
    static int[][] graph;
    static boolean[] visited;
    static boolean arrival;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for (int i=0; i<t; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            graph = new int[n+2][2];
            visited = new boolean[n+2];
            arrival = false;
            for (int j=0; j<n+2; j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[j][0] = a; graph[j][1] = b;
            }
            bfs(0, 1 , n);
            if (arrival) sb.append("happy").append("\n");
            else sb.append("sad").append("\n");
        }
        System.out.print(sb);
        return;
    }
    public static void bfs(int pos, int depth, int n){
        visited[pos] = true;
        if (depth == n+2) {
            arrival = true;
            return;
        }
        int x = graph[pos][0];
        int y = graph[pos][1];
        for (int i=1; i< graph.length; i++){
            if (!visited[i] && Math.abs(x -graph[i][0])+Math.abs(y -graph[i][1]) <= 1000)
                bfs(i, depth+1, n);
        }
        visited[pos] = false;
    }
}

//AI Recommend
import java.io.*;
        import java.util.*;

public class Main {
    static int[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            graph = new int[n+2][2];
            visited = new boolean[n+2];

            for (int i=0; i<n+2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                graph[i][0] = Integer.parseInt(st.nextToken());
                graph[i][1] = Integer.parseInt(st.nextToken());
            }

            if (bfs(0, n)) sb.append("happy\n");
            else sb.append("sad\n");
        }

        System.out.print(sb);
    }

    static boolean bfs(int start, int n) {
        Deque<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            int x = graph[cur][0];
            int y = graph[cur][1];

            if (cur == n+1) return true; // festival 도착

            for (int i=0; i<n+2; i++) {
                if (!visited[i]) {
                    int dist = Math.abs(x - graph[i][0]) + Math.abs(y - graph[i][1]);
                    if (dist <= 1000) {
                        visited[i] = true;
                        q.add(i);
                    }
                }
            }
        }
        return false;
    }
}

//Third try
import java.io.*;
        import java.util.*;

class Scratch {
    static int[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            graph = new int[n + 2][2];
            visited = new boolean[n + 2];
            for (int j = 0; j < n + 2; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[j][0] = a;
                graph[j][1] = b;
            }
            if (bfs(0, n)) sb.append("happy").append("\n");
            else sb.append("sad").append("\n");
        }
        System.out.print(sb);
        return;
    }

    public static boolean bfs(int start, int n) {
        Deque<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.add(start);
        while (!q.isEmpty()) {
            int top = q.poll();
            if (top == n + 1) return true;
            int x = graph[top][0];
            int y = graph[top][1];
            for (int i = 0; i < n + 2; i++) {
                if (!visited[i] && Math.abs(x - graph[i][0]) + Math.abs(y - graph[i][1]) <= 1000) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
        return false;
    }
}