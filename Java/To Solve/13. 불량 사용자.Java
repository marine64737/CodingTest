//5세트 (그래프 DFS/재귀 확장)
//https://school.programmers.co.kr/learn/courses/30/lessons/64064

class Solution {
    int answer;
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        boolean[] visited = new boolean[user_id.length];
        for (int i=0; i<user_id; i++){
            for (int j=0; j<banned_id; j++){
                if (!visited[i]) dfs(user_id[i], banned_id[j], 0);
            }
        }
        return answer;
    }
    dfs(String user_id, String banned_id, int index){
        if (index == user_id.length - 1) answer++;
        else {
            if (banned_id[index] != '*' && user_id[index] != banned_id[index]) return;
            else dfs(user_id, banned_id, index+1);
        }
    }
}

class Solution {
    int answer;
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        boolean[] visited = new boolean[user_id.length];
        for (int i=0; i<user_id.length; i++){
            for (int j=0; j<banned_id.length; j++){
                if (!visited[i]) if (!dfs(visited, user_id[i], banned_id[j], 0)) visited[i] = true;
            }
        }
        return answer;
    }
    public boolean dfs(boolean[] visited, String user_id, String banned_id, int depth){
        if (banned_id.charAt(depth) != '*' && user_id.charAt(depth) != banned_id.charAt(depth)) return false;
        else {
            if (depth == user_id.length() - 1) {
                answer++;
                return true;
            }
            else if (dfs(visited, user_id, banned_id, depth+1)) return true;
        }
        return false;
    }
}