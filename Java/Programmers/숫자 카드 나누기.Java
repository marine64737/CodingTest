//https://school.programmers.co.kr/learn/courses/30/lessons/135807
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int count = 0;
        int minA = 100000000;
        int minB = 100000000;
        int c = 0;
        int y = 0;
        for (int num : arrayA){
            if (num < minA) minA = num;
        }
        for (int num : arrayB){
            if (num < minB) minB = num;
        }
        for (int i = 2; i <= minA; i++){
            for (int j=0; j<arrayA.length; j++){
                if (arrayA[j] % i == 0 && arrayB[j] % i != 0) count ++;
                else break;
            }
            if (count == arrayA.length){
                c = i;
            }
            count = 0;
        }
        for (int i = 2; i <= minB; i++){
            for (int j=0; j<arrayA.length; j++){
                if (arrayB[j] % i == 0 && arrayA[j] % i != 0) count ++;
                else break;
            }
            if (count == arrayA.length){
                y = i;
            }
            count = 0;
        }
        if(c != 0 || y != 0){
            return (c > y) ? c : y;
        }
        return 0;
    }
}
//Retry
import java.lang.*;
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];
        for (int num : arrayA){
            gcdA = gcd(gcdA, num);
        }
        for (int num : arrayB){
            gcdB = gcd(gcdB, num);
        }
        int answer = 0;
        if (!isDivided(arrayA, gcdB)) answer = gcdB;
        if (!isDivided(arrayB, gcdA)) answer = Math.max(answer, gcdA);

        return answer;
    }

    private int gcd(int a, int b){
        if (b==0) return a;
        return gcd(b, a%b);
    }

    private boolean isDivided(int[] arr, int b){
        for (int a : arr){
            if (a % b == 0) return true;
        }
        return false;
    }
}

//AI Recommend
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];

        // 두 배열의 전체 GCD 구하기
        for (int i = 1; i < arrayA.length; i++) {
            gcdA = gcd(gcdA, arrayA[i]);
            gcdB = gcd(gcdB, arrayB[i]);
        }

        // 조건에 맞는지 체크
        int answer = 0;
        if (canDivideAll(gcdA, arrayB)) answer = gcdA;
        if (canDivideAll(gcdB, arrayA)) answer = Math.max(answer, gcdB);

        return answer;
    }

    // GCD 구하기
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // 나누면 안 되는 배열을 모두 못 나눠야 유효함
    private boolean canDivideAll(int divisor, int[] array) {
        for (int num : array) {
            if (num % divisor == 0) return false;
        }
        return true;
    }
}
