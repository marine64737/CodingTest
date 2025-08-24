//https://school.programmers.co.kr/learn/courses/30/lessons/1844
class Solution {
    boolean[][] visited;
    int answer=-1;
    public int solution(int[][] maps) {
        visited= new boolean[maps.length][maps[0].length];
        bfs(1, 0, 0, maps);
        return answer;
    }
    public void bfs(int depth, int x, int y, int[][] maps){
        visited[x][y] = true;
        if (x==maps.length-1 && y==maps[0].length-1) {
            if (answer == -1) answer = depth;
            else answer = Math.min(answer, depth);
            visited[x][y] = false;
            return;
        }
        int[][] move = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i=0; i<4; i++){
            if (x+move[i][0] >=0 && x+move[i][0] < maps.length && y+move[i][1] >= 0 && y+move[i][1] <maps[0].length
                    && visited[x+move[i][0]][y+move[i][1]] == false && maps[x+move[i][0]][y+move[i][1]] == 1)
                bfs(depth+1, x+move[i][0], y+move[i][1], maps);
        }
        visited[x][y] = false;
    }
}