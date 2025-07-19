//1세트 (재귀 입문 + DFS 기초)

//https://school.programmers.co.kr/learn/courses/30/lessons/43165

//int형으로 반환 시도 -> 방법 변경
//class Solution {
//    public int solution(int[] numbers, int target) {
//        int answer = 0;
//        answer = addsub
//        return answer;
//    }
//    public int add(int[] numbers, int index, int target, int count){
//        if (index == 0) return numbers[index];
//        else if (index + 1 == numbers.length){
//            if (addsub(numbers, index-1)+numbers[index] == target){
//                count++;
//            }
//        }
//        else {
//            return addsub(numbers, index-1)+numbers[index];
//        }
//    }
//    public int sub(int[] numbers, int index, int target, int count){
//        if (index == 0) return numbers[index];
//        else if (index + 1 == numbers.length){
//            if (addsub(numbers, index-1)+numbers[index] == target){
//                count++;
//            }
//        }
//        else {
//            return addsub(numbers, index-1)+numbers[index];
//        }
//    }
//}

//Second Try
class Solution {
    int answer = 0;
    int index = -1;
    int sum = 0;
    public int solution(int[] numbers, int target) {
        add(numbers, target);
        index--;
        sub(numbers, target);
        index--;
        return answer;
    }
    public void add(int[] numbers, int target){
        index++;
        sum += numbers[index];
        //System.out.println("(add)Index: "+index+", Sum: "+sum);
        if (index + 1 == numbers.length){
            //System.out.println("To the end Sum(add): "+sum);
            //System.out.print("[");
            //for (int i = 0; i<numbers.length; i++){
            //    System.out.print(pr[i]+",");
            //}
            //System.out.println("]"+", Sum: "+sum);
            if(sum == target){
                //System.out.println("Check!: "+sum);
                answer++;
            }
        }
        else {
            add(numbers, target);
            index--;
            //System.out.println("(After add)Index: "+index+", Sum: "+sum);
            sub(numbers, target);
            index--;
            //System.out.println("(After sub)Index: "+index+", Sum: "+sum);
        }
        sum -= numbers[index];
        return;
    }
    public void sub(int[] numbers, int target){
        index++;
        sum -= numbers[index];
        //System.out.println("(sub)Index: "+index+", Sum: "+sum);
        if (index + 1 == numbers.length){
            //System.out.println("To the end Sum(sub): "+sum);
            //System.out.print("[");
            //for (int i = 0; i<numbers.length; i++){
            //    System.out.print(pr[i]+",");
            //}
            //System.out.println("]"+", Sum: "+sum);
            if(sum == target){
                answer++;
            }
        }
        else {
            add(numbers, target);
            index--;
            //System.out.println("(After add)Index: "+index+", Sum: "+sum);
            sub(numbers, target);
            index--;
            //System.out.println("(After sub)Index: "+index+", Sum: "+sum);
        }
        sum += numbers[index];
        return;
    }
}

//AI Recommend
class Solution {
    int answer = 0;

    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        return answer;
    }

    private void dfs(int idx, int sum, int[] numbers, int target) {
        if (idx == numbers.length) {
            if (sum == target) answer++;
            return;
        }

        dfs(idx + 1, sum + numbers[idx], numbers, target);
        dfs(idx + 1, sum - numbers[idx], numbers, target);
    }
}
