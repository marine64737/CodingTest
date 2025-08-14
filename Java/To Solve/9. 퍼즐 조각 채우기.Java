//4세트 (백트래킹 심화)
//https://school.programmers.co.kr/learn/courses/30/lessons/84021

//First try(중간에 포기)
import java.util.*;
class Solution {
    boolean[][] visited;
    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;
        List<List<Integer>> puzzle = new ArrayList<>();
        visited = new boolean[game_board.length][game_board.length];
        for (int i=0; i<table.length; i++){
            for (int j=0; j<table.length; j++){
                puzzle.add(new ArrayList<>());
                if (!visited[i][j]&&table[i][j] == 1) part(table, puzzle, 0, new int[]{i, j});
                System.out.println(puzzle);
            }
        }
        return answer;
    }
    public void part(int[][] table, List<List<Integer>> puzzle, int puzzle_index, int[] index){
        visited[index[0]][index[1]] = true;
        puzzle.get(puzzle_index).add(new int[]{index[0], index[1]});
        if (index[0]-1 >= 0)
            if (!visited[index[0]-1][index[1]] && table[index[0]-1][index[1]]==1) part(table, puzzle, new int[]{i-1, j});
        if (index[0]+1 < game_board.length)
            if (!visited[index[0]+1][index[1]] && table[index[0]+1][index[1]]==1) part(table, puzzle, new int[]{i+1, j});
        if (index[1]-1 >= 0)
            if (!visited[index[0]][index[1]-1] && table[index[0]][index[1]-1]==1) part(table, puzzle, new int[]{i, j-1});
        if (index[1]+1 < game_board.length)
            if (!visited[index[0]][index[1]+1] && table[index[0]][index[1]+1]==1) part(table, puzzle, new int[]{i, j+1});
    }
}

//Ai Recommend(나중에 다시 풀기)
import java.util.*;

class Solution {
    int n;
    int[][] DIR = {{1,0},{-1,0},{0,1},{0,-1}};

    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;

        // 1) 빈칸(0) 덩어리, 블록(1) 덩어리 추출
        List<List<int[]>> holes  = extract(game_board, 0);
        List<List<int[]>> blocks = extract(table, 1);

        // 2) 블록들을 멀티셋(Map<key,count>)으로
        Map<String, Integer> bag = new HashMap<>();
        for (List<int[]> b : blocks) {
            String k = canon(b);
            bag.merge(k, 1, Integer::sum);
        }

        // 3) 빈칸과 매칭
        int answer = 0;
        for (List<int[]> h : holes) {
            String k = canon(h);
            Integer c = bag.get(k);
            if (c != null && c > 0) {
                bag.put(k, c - 1);
                answer += h.size(); // 채워진 칸 수
            }
        }
        return answer;
    }

    // target(0 또는 1)인 연결요소를 전부 추출 → 각 요소는 (r,c) 좌표 리스트 (정규화 포함)
    List<List<int[]>> extract(int[][] board, int target) {
        boolean[][] vis = new boolean[n][n];
        List<List<int[]>> res = new ArrayList<>();
        for (int r = 0; r < n; r++) for (int c = 0; c < n; c++) {
            if (vis[r][c] || board[r][c] != target) continue;
            ArrayDeque<int[]> q = new ArrayDeque<>();
            List<int[]> shape = new ArrayList<>();
            q.add(new int[]{r, c}); vis[r][c] = true;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                shape.add(cur);
                for (int[] d : DIR) {
                    int nr = cur[0] + d[0], nc = cur[1] + d[1];
                    if (0 <= nr && nr < n && 0 <= nc && nc < n &&
                            !vis[nr][nc] && board[nr][nc] == target) {
                        vis[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
            res.add(normalize(shape)); // 좌상단(0,0) 기준으로 평행이동 + 정렬
        }
        return res;
    }

    // 좌상단으로 이동 + (r,c) 사전순 정렬
    List<int[]> normalize(List<int[]> shape) {
        int minR = Integer.MAX_VALUE, minC = Integer.MAX_VALUE;
        for (int[] p : shape) { minR = Math.min(minR, p[0]); minC = Math.min(minC, p[1]); }
        List<int[]> out = new ArrayList<>(shape.size());
        for (int[] p : shape) out.add(new int[]{p[0] - minR, p[1] - minC});
        out.sort((a,b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);
        return out;
    }

    // 90도 회전: 바운딩 박스 높이 H 기준 (r,c)->(c, H-1-r) 후 normalize
    List<int[]> rotate90(List<int[]> shape) {
        int H = 0, W = 0;
        for (int[] p : shape) { H = Math.max(H, p[0] + 1); W = Math.max(W, p[1] + 1); }
        List<int[]> rot = new ArrayList<>(shape.size());
        for (int[] p : shape) rot.add(new int[]{ p[1], H - 1 - p[0] });
        return normalize(rot);
    }

    // 0/90/180/270 중 가장 작은 문자열 키(대표키)
    String canon(List<int[]> shape) {
        List<int[]> cur = normalize(shape);
        String best = key(cur);
        for (int i = 0; i < 3; i++) {
            cur = rotate90(cur);
            String k = key(cur);
            if (k.compareTo(best) < 0) best = k;
        }
        return best;
    }

    // 좌표 리스트 → "r_c|r_c|..." 문자열 키
    String key(List<int[]> s) {
        StringBuilder sb = new StringBuilder();
        for (int[] p : s) sb.append(p[0]).append('_').append(p[1]).append('|');
        return sb.toString();
    }
}
