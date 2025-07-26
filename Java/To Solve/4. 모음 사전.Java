//2세트 (백트래킹 / 경우의 수)
//https://school.programmers.co.kr/learn/courses/30/lessons/84512

//Solved
class Solution {
    int order = 0;
    boolean found = false;
    public int solution(String word) {
        String[] alphabet = {"A", "E", "I", "O", "U"};
        dfs(word, "", alphabet, 0);
        return order;
    }

    public void dfs(String word, String current, String[] alphabet, int index){
        if (current.equals(word)) {
            found = true;
            return;
        }
        if (index > 4) {
            return;
        }
        for (int i = 0; i < 5; i++){
            order++;
            dfs(word, current + alphabet[i], alphabet, index+1);
            if (found) return;
        }
    }
}

//AI Recommend
public class Solution {
    int order = 0;

    public int solution(String word) {
        dfs(word, "", 0);
        return order;
    }

    public boolean dfs(String word, String current, int index) {
        if (current.equals(word)) return true;
        if (index == 5) return false;

        for (String ch : new String[]{"A", "E", "I", "O", "U"}) {
            order++;
            if (dfs(word, current + ch, index + 1)) return true;
        }
        return false;
    }
}
