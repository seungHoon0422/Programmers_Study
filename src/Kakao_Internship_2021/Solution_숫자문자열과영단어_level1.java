package Kakao_Internship_2021;

import java.util.HashMap;
import java.util.Map;

/**
 * [투포인터]
 * 글자를 읽어가면서 투포인터 활용
 * @author SeongHoon
 *
 */
class Solution_숫자문자열과영단어_level1 {
    Map<String, Integer> map;

    public int solution(String s) {
        int answer = 0;

        map = new HashMap<>();
        initMap();
        int start = 0;
        int end = 0;
        String substr = "";
        while(end < s.length()) {
            if(map.containsKey(substr)) {
                answer = answer*10 + map.get(substr);
                start = end;
                substr = "";
            } else {
                if('0'<= s.charAt(end) && s.charAt(end) <='9') {
                    answer = answer*10 + (s.charAt(end)-'0');
                    start++;
                    end++;
                } else {
                    substr = substr + s.charAt(end++);
                }

            }

        }
        if(map.containsKey(substr)) {
            answer = answer*10 + map.get(substr);
        }



        return answer;
    }
    private void initMap() {
        String[] numbers = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for(int i=0; i<numbers.length; i++) {
            map.put(numbers[i], i);
        }
    }
}