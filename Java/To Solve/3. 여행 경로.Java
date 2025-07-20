//2세트 (백트래킹 / 경우의 수)
//https://school.programmers.co.kr/learn/courses/30/lessons/43164

//Fitst Try
class Solution {
    public String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length+1];
        boolean[] tripped = new boolean[tickets.length];
        route(tickets, answer, tripped, 0, 0);
        return answer;
    }
    public void route(String[][] tickets, String[] answer, boolean[] tripped, int tickets_index, int answer_index){
        answer[answer_index] = tickets[tickets_index][0];
        if (answer_index+1 == tickets.length){
            answer[answer_index+1] = tickets[tickets_index][1];
            return;
        }
        int idx=tickets_index;
        String tmp=tickets[tickets_index][1];
        for (int i = 0; i< tickets.length; i++){
            if (tripped[i] == false && tickets[tickets_index][0].equals(tickets[i][0])){
                if (tmp.compareTo(tickets[i][1]) > 0){
                    idx = i;
                    tmp = tickets[i][1];
                }
            }
        }
        for (int i = 0; i< tickets.length; i++){
            if (tripped[i] == false && tickets[idx][1].equals(tickets[i][0])){
                tripped[idx] = true;
                tripped[i] = true;
                answer_index++;
                answer[answer_index] = tickets[idx][1];
                route(tickets, answer, tripped, i, answer_index);
            }
        }
        return;
    }
}

class Solution {
    boolean found = false;
    public String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length+1];
        boolean[] tripped = new boolean[tickets.length];
        java.util.Arrays.sort(tickets, (a, b) -> {
            if (a[0].equals(b[0])) return a[1].compareTo(b[1]);
            return a[0].compareTo(b[0]);
        });
        for (int i = 0; i<tickets.length; i++){
            if (tickets[i][0].equals("ICN")){
                answer[0] = tickets[i][0];
                answer[1] = tickets[i][1];
                tripped[i] = true;
                route(tickets, answer, tripped, 0, 2);
                tripped[i] = false;

                if (found) break;
            }
        }
        return answer;
    }
    public void route(String[][] tickets, String[] answer, boolean[] tripped, int tickets_index, int answer_index){
        if (found) return;
        if (answer_index == tickets.length + 1){
            found = true;
            return;
        }
        for (int i = 0; i< tickets.length; i++){
            if (!tripped[i] && tickets[tickets_index][1].equals(tickets[i][0])){
                tripped[i] = true;
                answer[answer_index] = tickets[i][1];
                route(tickets, answer, tripped, i, answer_index);
                tripped[i] = false;
            }
        }
        return;
    }
}

//왜 안 되는지 고민 중(found가 true임에도 계속 순회)
class Solution {
    boolean found = false;
    public String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length+1];
        boolean[] tripped = new boolean[tickets.length];
        java.util.Arrays.sort(tickets, (a, b) -> {
            if (a[0].equals(b[0])) return a[1].compareTo(b[1]);
            return a[0].compareTo(b[0]);
        });
        for (int i = 0; i<tickets.length; i++){
            if (tickets[i][0].equals("ICN")){
                answer[0] = tickets[i][0];
                answer[1] = tickets[i][1];
                tripped[i] = true;
                route(tickets, answer, tripped, i, 2);
                tripped[i] = false;

                if (found) break;
            }
        }
        return answer;
    }
    public void route(String[][] tickets, String[] answer, boolean[] tripped, int tickets_index, int answer_index){
        if (found) return;
        if (answer_index == tickets.length + 1){
            found = true;
            return;
        }
        for (int i = 0; i< tickets.length; i++){
            if (!tripped[i] && tickets[tickets_index][1].equals(tickets[i][0])){
                tripped[i] = true;
                answer[answer_index] = tickets[i][1];
                route(tickets, answer, tripped, i, answer_index+1);
                tripped[i] = false;
                if (found) return; //추가 후 정상 동작
            }
        }
    }
}
//AI Recommend
class Solution {
    String[] finalAnswer;
    boolean found = false;

    public String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];
        boolean[] visited = new boolean[tickets.length];

        // 티켓을 사전순으로 정렬
        java.util.Arrays.sort(tickets, (a, b) -> {
            if (a[0].equals(b[0])) return a[1].compareTo(b[1]);
            return a[0].compareTo(b[0]);
        });

        // 출발지가 "ICN"인 티켓부터 DFS 시작
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals("ICN")) {
                visited[i] = true;
                answer[0] = tickets[i][0];
                answer[1] = tickets[i][1];
                dfs(tickets, visited, answer, 2, tickets[i][1]);
                visited[i] = false;

                if (found) break; // 정답 하나만 찾고 바로 종료
            }
        }

        return finalAnswer;
    }

    public void dfs(String[][] tickets, boolean[] visited, String[] path, int depth, String current) {
        if (found) return;

        if (depth == tickets.length + 1) {
            // 모든 티켓 사용 완료, 정답 경로 저장
            finalAnswer = path.clone();
            found = true;
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(current)) {
                visited[i] = true;
                path[depth] = tickets[i][1];
                dfs(tickets, visited, path, depth + 1, tickets[i][1]);
                visited[i] = false;
            }
        }
    }
}
