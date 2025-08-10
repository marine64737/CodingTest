//4세트 (백트래킹 심화)
//https://school.programmers.co.kr/learn/courses/30/lessons/12952

//First try
class Solution {
    int answer = 0;
    public int solution(int n) {
        boolean[][] chess = new boolean[n][n];
        for (int i=0; i<n; i++){
            dfs(chess, 0, n, new int[]{0,i});
        }
        return answer;
    }

    public void dfs(boolean[][] chess, int num, int n, int[] index){
        num++;
        if (num == n) {
            answer++;
            return;
        }

        boolean[][] chess_copy = new boolean[n][n];
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                chess_copy[i][j] = chess[i][j];
            }
        }

        for (int i=-n; i<n; i++){
            if (i >= 0 && i < n){
                chess_copy[index[0]][i] = true;
                chess_copy[i][index[1]] = true;
            }
            if (index[0]+i >= 0 && index[0]+i < n){
                if (index[1]+i >= 0 && index[1]+i < n)
                    chess_copy[index[0]+i][index[1]+i]=true;
                if (index[1]-i >= 0 && index[1]-i < n)
                    chess_copy[index[0]+i][index[1]-i]=true;
            }
        }

        for (int i=0; i<n; i++){
            if (!chess_copy[index[0]+1][i]) dfs(chess_copy, num, n, new int[]{index[0]+1, i});
        }
    }
}

//Second try
class Solution {
    int answer = 0;
    public int solution(int n) {
        boolean[] col = new boolean[n];
        boolean[] diag1 = new boolean[2*n-1];
        boolean[] diag2 = new boolean[2*n-1];
        dfs(0, n, col, diag1, diag2);
        return answer;
    }

    public void dfs(int r, int n, boolean[] col, boolean[] diag1, boolean[] diag2){
        if (r == n) {
            answer++;
            return;
        }


        for (int c=0; c<n; c++){
            if (col[c] || diag1[r+c] || diag2[r-c+n-1]) continue;
            else {
                boolean[] col_copy = col.clone();
                boolean[] diag1_copy = diag1.clone();
                boolean[] diag2_copy = diag2.clone();
                col_copy[c] = true;
                diag1_copy[r+c] = true;
                diag2_copy[r-c+n-1] =true;
                dfs(r+1, n, col_copy, diag1_copy, diag2_copy);
            }
        }
    }
}