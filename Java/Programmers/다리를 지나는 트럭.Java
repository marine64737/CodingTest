//https://school.programmers.co.kr/learn/courses/30/lessons/42583

//First Try

//import java.util.*;
//class Solution {
//    public int solution(int bridge_length, int weight, int[] truck_weights) {
//        int answer = 0;
//        int bridgeWeight = 0;
//        Queue<Integer> bridge = new LinkedList<>();
//        ArrayList<Integer> truckArray = new ArrayList<>();
//        for (int truck : truck_weights){
//            truckArray.add(truck);
//        }
//        showList(truckArray);
//        do{
//            if (bridge.size() + 1 < bridge_length){
//                if (bridgeWeight + truckArray.get(0) <= weight){
//                    int top = truckArray.get(0);
//                    bridge.add(top);
//                    bridgeWeight += top;
//                    truckArray.remove(0);
//                    System.out.println("truckArray.size(): "+truckArray.size());
//                    System.out.println("bridge.size(): "+bridge.size());
//                    showList(truckArray);
//                }
//                else {
//                    bridge.add(0);
//                    System.out.println("bridge.size(): "+bridge.size());
//                    showList(truckArray);
//                }
//            }
//            else {
//                bridgeWeight -= bridge.poll();
//                System.out.println("bridge.size(): "+bridge.size());
//                showList(truckArray);
//            }
//
//            answer++;
//        }
//        while (!bridge.isEmpty());
//
//        return answer;
//    }
//
//    public void showList(ArrayList<Integer> list){
//        System.out.print("{");
//        for (int i=0; i<list.size(); i++){
//            System.out.print(list.get(i));
//            System.out.print(",");
//        }
//        System.out.println("}");
//    }
//}

//Second Try
import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int bridgeWeight = 0;
        Queue<Integer> bridge = new LinkedList<>();
        ArrayList<Integer> truckArray = new ArrayList<>();
        for (int truck : truck_weights){
            truckArray.add(truck);
        }
        int top = truckArray.get(0);
        bridge.add(top);
        bridgeWeight += top;
        truckArray.remove(0);
        answer++;
        while (bridgeWeight > 0) {
            if (bridge.peek() >= 0 && bridge.size() == bridge_length){
                bridgeWeight -= bridge.peek();
                bridge.poll();
            }
            if (!truckArray.isEmpty() && bridgeWeight + truckArray.get(0) <= weight){
                top = truckArray.get(0);
                bridge.add(top);
                bridgeWeight += top;
                truckArray.remove(0);
            }
            else {
                bridge.add(0);
            }
            answer++;
        }

        return answer;
    }
}