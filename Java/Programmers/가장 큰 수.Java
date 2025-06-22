public static String solution(int[] numbers) {
    String answer = "";
    String[] nums = Arrays.stream(numbers)
            .mapToObj(String::valueOf)
            .toArray(String[]::new);

    Arrays.sort(nums, (a, b) -> (b+a).compareTo(a+b));

    for (int i=0; i< nums.length; i++){
        answer += nums[i];
    }

    return answer;
}