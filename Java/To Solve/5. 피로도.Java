//3세트 (완전탐색 + 순열/조합)
//https://school.programmers.co.kr/learn/courses/30/lessons/87946
class Solution {
    int answer = -1;
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new  boolean[dungeons.length];
        for (int i = 0; i < dungeons.length; i++){
            dfs(dungeons, visited, k, i, 1);
        }
        return answer;
    }
    public void dfs(int[][] dungeons, boolean[] visited, int k, int index, int depth){
        if (k < dungeons[index][0]) return;
        if (visited[index]) return;
        else {
            for (int i = 0; i < dungeons.length; i++){
                answer = Math.max(answer, depth);
                visited[index] = true;
                dfs(dungeons, visited, k - dungeons[index][1], i, depth+1);
                visited[index] = false;
            }
        }
    }
}

//AI Recommend
class Solution {
    int answer = 0;

    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        dfs(k, dungeons, visited, 0);
        return answer;
    }

    void dfs(int k, int[][] dungeons, boolean[] visited, int depth) {
        answer = Math.max(answer, depth);

        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                dfs(k - dungeons[i][1], dungeons, visited, depth + 1);
                visited[i] = false;
            }
        }
    }
}
