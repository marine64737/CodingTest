//https://www.acmicpc.net/problem/25590
//First try
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        if (K < S){
            System.out.print(0);
            return;
        }
        int[] parent_before = new int[N+1];
        for (int i=1; i<N+1; i++) parent_before[i] = i;
        int[] depth_before=new int[N+1];
        boolean[] visited_before = new boolean[N+1];
        int[] parent_after = new int[N+S+1];
        for (int i=1; i<N+1; i++) parent_after[i] = i;
        int[] depth_after=new int[N+S+1];
        boolean[] visited_after = new boolean[N+S+1];
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            int C;
            if (tmp.equals("0")) continue;
            else C = Integer.parseInt(tmp);
            for (int j=0; j<C; j++) parent_before[Integer.parseInt(st.nextToken())] = i+1;
        }
        LCA(parent_before, depth_before, visited_before, 0);
        for (int i=0; i<N+S; i++){
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            int C;
            if (tmp.equals("0")) continue;
            else C = Integer.parseInt(tmp);
            if (M < C){
                System.out.print(0);
                return;
            }
            for (int j=0; j<C; j++) parent_after[Integer.parseInt(st.nextToken())] = i+1;
        }
        LCA(parent_after, depth_after, visited_after,0);
        for (int i=1; i<N+1; i++){
            if (!isConnected(parent_after, depth_after, parent_before[i], i)){
                System.out.print(0);
                return;
            }
        }
        System.out.print(1);
    }
    static void LCA(int[] parent_arr, int[] depth_arr, boolean[] visited, int depth){
        if (depth == 0){
            for (int i = 1; i < parent_arr.length; i++) {
                if (!visited[i] && parent_arr[i] == i) {
                    visited[i] = true;
                    depth_arr[i] = depth;
                    LCA(parent_arr, depth_arr, visited, depth + 1);
                }
            }
        }
        else {
            for (int i = 1; i < parent_arr.length; i++) {
                if (!visited[i] && depth_arr[parent_arr[i]] == depth - 1) {
                    visited[i] = true;
                    depth_arr[i] = depth;
                    LCA(parent_arr, depth_arr, visited, depth + 1);
                }
            }
        }
    }
    static boolean isConnected(int[] parent_arr, int[] depth, int a, int b){
        if (depth[b] == 0) return true;
        while (b != parent_arr[b]) {
            if (a == parent_arr[b]) return true;
            b = parent_arr[b];
        }
        return false;
    }
}
//Second try
import java.io.*;
        import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        if (K < S){
            System.out.print(0);
            return;
        }
        int[] parent_before = new int[N+1];
        for (int i=1; i<N+1; i++) parent_before[i] = i;
        int[] depth_before=new int[N+1];
        boolean[] visited_before = new boolean[N+1];
        int[] parent_after = new int[N+S+1];
        for (int i=1; i<N+1; i++) parent_after[i] = i;
        int[] depth_after=new int[N+S+1];
        boolean[] visited_after = new boolean[N+S+1];
        boolean[] pass = new  boolean[N+1];
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            int C;
            if (tmp.equals("0")) continue;
            else C = Integer.parseInt(tmp);
            for (int j=0; j<C; j++) parent_before[Integer.parseInt(st.nextToken())] = i+1;
        }
        LCA(parent_before, depth_before, visited_before, 0);
        for (int i=0; i<N+S; i++){
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            int C;
            if (tmp.equals("0")) continue;
            else C = Integer.parseInt(tmp);
            if (M < C){
                System.out.print(0);
                return;
            }
            for (int j=0; j<C; j++) parent_after[Integer.parseInt(st.nextToken())] = i+1;
        }
        LCA(parent_after, depth_after, visited_after,0);
        for (int i=1; i<N+1; i++){
            if (!pass[i] && !isConnected(parent_after, depth_after, depth_before, pass, parent_before[i], i)) {
                System.out.print(0);
                return;
            }
        }
        System.out.print(1);
    }
    static void LCA(int[] parent_arr, int[] depth_arr, boolean[] visited, int depth){
        if (depth == 0){
            for (int i = 1; i < parent_arr.length; i++) {
                if (!visited[i] && parent_arr[i] == i) {
                    visited[i] = true;
                    depth_arr[i] = depth;
                    LCA(parent_arr, depth_arr, visited, depth + 1);
                }
            }
        }
        else {
            for (int i = 1; i < parent_arr.length; i++) {
                if (!visited[i] && depth_arr[parent_arr[i]] == depth - 1) {
                    visited[i] = true;
                    depth_arr[i] = depth;
                    LCA(parent_arr, depth_arr, visited, depth + 1);
                }
            }
        }
    }
    static boolean isConnected(int[] parent_arr, int[] depth_after, int[] depth_before,boolean[] pass, int a, int b){
        if (a == b) {
            pass[b] = true;
            return true;
        }
        else if (depth_after[b] < depth_before[b]) {
            pass[b] = false;
            return false;
        }
        if (isConnected(parent_arr, depth_after, depth_before, pass, a, parent_arr[b])) {
            pass[b] = true;
            return true;
        }
        return false;
    }
}
//AI Recommend
import java.io.*;
        import java.util.*;

/**
 * 마트료시카 3 류 트리-검증 파이프라인 (자바 최적화 버전)
 * 입력 (권장 가정):
 *   N
 *   (N-1)줄: oldParent oldChild   // 기존 트리, 방향 간선
 *   (N-1)줄: newParent newChild   // 수정 트리, 방향 간선
 * 출력:
 *   가능하면 1, 아니면 0
 */
public class Main {

    // ---------- FastScanner (byte 기반) ----------
    static final class FastScanner {
        private final InputStream in;
        private final byte[] buf = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is){ this.in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buf);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buf[ptr++];
        }
        int nextInt() throws IOException {
            int c, s = 1, x = 0;
            do { c = read(); } while (c <= 32);
            if (c == '-') { s = -1; c = read(); }
            while (c > 32) { x = x * 10 + (c - '0'); c = read(); }
            return x * s;
        }
    }

    // ---------- 압축 인접리스트 (무방향 트리용) ----------
    static int N;
    static int[] head, to, next; // head[u] = 첫 간선 index, to[i], next[i]
    static int ptr;

    static void initGraph(int n, int edgeCap) {
        N = n;
        head = new int[N + 1];
        Arrays.fill(head, -1);
        to = new int[edgeCap];
        next = new int[edgeCap];
        ptr = 0;
    }
    static void addEdgeUndirected(int u, int v) {
        to[ptr] = v; next[ptr] = head[u]; head[u] = ptr++;
        to[ptr] = u; next[ptr] = head[v]; head[v] = ptr++;
    }

    // ---------- Euler (반복 DFS) ----------
    static int[] tin, tout, parent;
    static int timer;

    static void eulerIterative(int root) {
        tin = new int[N + 1];
        tout = new int[N + 1];
        parent = new int[N + 1];

        int[] it = new int[N + 1];     // 각 노드의 다음 간선 인덱스
        int[] st = new int[N + 5];     // 스택
        boolean[] entered = new boolean[N + 1];

        timer = 0;
        parent[root] = 0;
        int sp = 0;
        st[sp++] = root;
        it[root] = head[root]; // head가 -1이면 루프에서 그냥 안 돈다

        while (sp > 0) {
            int u = st[sp - 1];
            if (!entered[u]) {          // 들어올 때
                entered[u] = true;
                tin[u] = ++timer;
            }

            int ei = it[u];
            boolean advanced = false;
            while (ei != -1) {
                int v = to[ei];
                ei = next[ei];
                if (v == parent[u]) continue;
                // 자식 진입
                parent[v] = u;
                it[u] = ei;             // u의 다음 간선 저장
                st[sp++] = v;
                it[v] = head[v];
                advanced = true;
                break;
            }
            if (!advanced) {            // 나갈 때
                tout[u] = timer;
                sp--;
            }
        }
    }

    static boolean isAncestorOld(int a, int b) {
        // a가 b의 조상?
        return tin[a] <= tin[b] && tout[b] <= tout[a];
    }

    // ---------- DSU ----------
    static final class DSU {
        int[] p, r;
        DSU(int n) { p = new int[n + 1]; r = new int[n + 1]; for (int i = 1; i <= n; i++) p[i] = i; }
        int find(int x) {
            while (x != p[x]) { p[x] = p[p[x]]; x = p[x]; }
            return x;
        }
        boolean union(int a, int b) {
            a = find(a); b = find(b);
            if (a == b) return false;         // 이미 같은 집합 => 사이클
            if (r[a] < r[b]) { int t = a; a = b; b = t; }
            p[b] = a;
            if (r[a] == r[b]) r[a]++;
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        if (n <= 0) { System.out.println(0); return; }
        // 1) 기존 트리 입력(방향 간선). Euler 용으로는 '무방향'으로 구성해서 부모 추적
        int[] indegOld = new int[n + 1];

        // 그래프 용량: 무방향이라 간선 2*(n-1)
        initGraph(n, 2 * (n - 1) + 5);

        // old 간선 읽으면서: indegOld[child]++ (루트 찾기용), 그래프에는 양방향 추가
        for (int i = 0; i < n - 1; i++) {
            int p = fs.nextInt();
            int c = fs.nextInt();
            addEdgeUndirected(p, c);
        }

        // 2) 원 트리의 루트 결정 (indeg==0)
        int root = 1;
        for (int v = 1; v <= n; v++) {
            if (indegOld[v] == 0) { root = v; break; }
        }

        // 3) Euler로 tin/tout (조상 판정 O(1))
        eulerIterative(root);

        // 4) 수정 트리 검사: 간선 하나씩 읽으며 즉시 체크 (O(N))
        DSU dsu = new DSU(n);
        int[] indegNew = new int[n + 1];
        boolean ok = true;

        for (int i = 0; i < n - 1; i++) {
            int p = fs.nextInt();
            int c = fs.nextInt();

            if (!ok) continue; // 이미 불가면 읽기만 소비

            // (a) 기존 트리에서 p는 c의 조상이었어야 함
            if (!isAncestorOld(p, c)) { ok = false; continue; }

            // (b) 다중 부모 방지
            if (++indegNew[c] > 1) { ok = false; continue; }

            // (c) 사이클 방지 (연결성도 함께 확보)
            if (!dsu.union(p, c)) { ok = false; }
        }

        // 5) 루트 수 = 1 체크 (indeg==0), 필요시 연결성 추가 체크
        if (ok) {
            int rootsNew = 0;
            for (int v = 1; v <= n; v++) if (indegNew[v] == 0) rootsNew++;
            if (rootsNew != 1) ok = false;
        }

        System.out.println(ok ? 1 : 0);
    }
}
//Last try(-> 16%)
import java.io.*;
        import java.util.*;

class Scratch {
    static int idx = 0;
    static int cnt = 0;
    static int N;
    static int[] in;
    static int[] out;
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        if (K < S) {
            System.out.print(0);
            return;
        }
        int[] head = new int[N + 1];
        int[] to = new int[N + 5];
        int[] next = new int[N + 5];
        int[] parent_old = new int[N+1];
        Arrays.fill(head, -1);
        int[] indeg = new int[N + 1];
        in = new int[N + 1];
        out = new int[N + 1];
        parent = new int[N + S + 1];
        int root = 1;
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());
            for (int j = 0; j < C; j++) {
                int child = Integer.parseInt(st.nextToken());
                parent_old[child] = i;
                addEdge(head, to, next, i, child);
                indeg[child]++;
                while (indeg[root] != 0) root++;
            }
        }
        for (int i = 1; i < N + 1; i++) if (indeg[i] == 0) root = i;
        euler(head, to, next, in, out, root);
        for (int i = 1; i < N + S + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());
            if (M < C) {
                System.out.print(0);
                return;
            }
            for (int j = 0; j < C; j++) {
                int child = Integer.parseInt(st.nextToken());
                parent[child] = i;
            }
        }
        for (int i = 1; i < N + 1; i++) {
            if (!isAncested(rep(parent[i]), parent_old[i])){
                System.out.print(0);
                return;
            }
        }
        System.out.print(1);
    }

    static void addEdge(int[] head, int[] to, int[] next, int u, int v) {
        to[idx] = v;
        next[idx] = head[u];
        head[u] = idx++;
    }

    static void euler(int[] head, int[] to, int[] next, int[] in, int[] out, int node) {
        in[node] = ++cnt;
        for (int i = head[node]; i != -1; i = next[i]) {
            int v = to[i];
            euler(head, to, next, in, out, v);
        }
        out[node] = cnt;
    }

    static boolean isAncested(int a, int b) {
        return in[b] <= in[a] && out[a] <= out[b];
    }

    static int rep(int x){
        if (x <= N) return x;
        return rep(parent[x]);
    }
}
//Last try(Success)
import java.io.*;
        import java.util.*;

class Scratch {
    static int idx = 0;
    static int cnt = 0;
    static int N;
    static int[] in;
    static int[] out;
    static int[] parent_old;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        if (K < S) {
            System.out.print(0);
            return;
        }
        int[] head = new int[N + S + 1];
        int[] to = new int[N + S + 5];
        int[] next = new int[N + S + 5];
        parent_old = new int[N+1];
        Arrays.fill(head, -1);
        int[] indeg = new int[N + S + 1];
        in = new int[N + S + 1];
        out = new int[N + S + 1];
        int root = 1;
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());
            for (int j = 0; j < C; j++) {
                int child = Integer.parseInt(st.nextToken());
                parent_old[child] = i;
            }
        }
        for (int i = 1; i < N + S + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());
            if (M < C) {
                System.out.print(0);
                return;
            }
            for (int j = 0; j < C; j++) {
                int child = Integer.parseInt(st.nextToken());
                addEdge(head, to, next, i, child);
                indeg[child]++;
                while (indeg[root] != 0) root++;
            }
        }
        euler(head, to, next, in, out, root);
        for (int i = 1; i < N + 1; i++) {
            if (parent_old[i] == 0) continue;
            if (!isAncested(parent_old[i], i)){
                System.out.print(0);
                return;
            }
        }
        System.out.print(1);
    }



    static void addEdge(int[] head, int[] to, int[] next, int u, int v) {
        to[idx] = v;
        next[idx] = head[u];
        head[u] = idx++;
    }

    static void euler(int[] head, int[] to, int[] next, int[] in, int[] out, int node) {
        in[node] = ++cnt;
        for (int i = head[node]; i != -1; i = next[i]) {
            int v = to[i];
            euler(head, to, next, in, out, v);
        }
        out[node] = cnt;
    }

    static boolean isAncested(int a, int b) {
        return in[a] <= in[b] && out[b] <= out[a];
    }
}
//Optimized
import java.io.*;
        import java.util.*;

class Scratch {
    static int idx = 0;
    static int cnt = 0;
    static int N;
    static int[] in;
    static int[] out;
    static int[] parent_old;
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        N = fs.nextInt();
        int M = fs.nextInt();
        int K = fs.nextInt();
        int S = fs.nextInt();
        if (K < S) {
            System.out.print(0);
            return;
        }
        int[] head = new int[N + S + 1];
        int[] to = new int[N + S + 5];
        int[] next = new int[N + S + 5];
        parent_old = new int[N+1];
        Arrays.fill(head, -1);
        int[] indeg = new int[N + S + 1];
        in = new int[N + S + 1];
        out = new int[N + S + 1];
        int root = 1;
        for (int i = 1; i < N + 1; i++) {
            int C = fs.nextInt();
            for (int j = 0; j < C; j++) {
                int child = fs.nextInt();
                parent_old[child] = i;
            }
        }
        for (int i = 1; i < N + S + 1; i++) {
            int C = fs.nextInt();
            if (M < C) {
                System.out.print(0);
                return;
            }
            for (int j = 0; j < C; j++) {
                int child = fs.nextInt();
                addEdge(head, to, next, i, child);
                indeg[child]++;
                while (indeg[root] != 0) root++;
            }
        }
        euler(head, to, next, in, out, root);
        for (int i = 1; i < N + 1; i++) {
            if (parent_old[i] == 0) continue;
            if (!isAncested(parent_old[i], i)){
                System.out.print(0);
                return;
            }
        }
        System.out.print(1);
    }
    static final class FastScanner {
        private final InputStream in;
        private final byte[] buf = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is){ this.in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buf);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buf[ptr++];
        }
        int nextInt() throws IOException {
            int c, s = 1, x = 0;
            do { c = read(); } while (c <= 32);
            if (c == '-') { s = -1; c = read(); }
            while (c > 32) { x = x * 10 + (c - '0'); c = read(); }
            return x * s;
        }
    }
    static void addEdge(int[] head, int[] to, int[] next, int u, int v) {
        to[idx] = v;
        next[idx] = head[u];
        head[u] = idx++;
    }

    static void euler(int[] head, int[] to, int[] next, int[] in, int[] out, int node) {
        in[node] = ++cnt;
        for (int i = head[node]; i != -1; i = next[i]) {
            int v = to[i];
            euler(head, to, next, in, out, v);
        }
        out[node] = cnt;
    }

    static boolean isAncested(int a, int b) {
        return in[a] <= in[b] && out[b] <= out[a];
    }
}
