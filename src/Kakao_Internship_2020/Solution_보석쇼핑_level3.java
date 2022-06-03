package Kakao_Internship_2020;

import java.util.HashMap;

/**
 * [투포인터, hash map]
 *
 * start, end포인터를 사용
 * 모든 보석이 담겨있지 않으면 end 증가
 * 모든 보석이 담긴 경우 start를 당기면서
 * 최소 구간길이를 갱신시키며 보석을 하나씩 지운다
 *
 * !! 주의
 * end가 끝까지 도달했어도 start를 최대한 땡겨서
 * 보석이 모두 차있는 경우 구간을 더 줄일 수 있는지 확인해야한다.
 */

class Solution_보석쇼핑_level3 {
    private HashMap<String, Integer> map;
    private HashMap<String, Integer> mine;
    private int size;

    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int min_length = Integer.MAX_VALUE;

        map = new HashMap<String, Integer>();
        mine = new HashMap<String, Integer>();

        // 보석 종류 확인
        for(String gem : gems) {
            if(map.containsKey(gem)) map.put(gem, map.get(gem)+1);
            else map.put(gem, 1);
        }
        size = map.keySet().size();
        int start =0, end =0;


        // 스타트 포인터도 항상 끝까지 떙겨야한다.
        while(end < gems.length || (start < gems.length && mine.keySet().size() == size)){

            //모든 보석을 포함하지 않는 경우
            if(mine.keySet().size() <size){
                if(mine.containsKey(gems[end])) mine.put(gems[end], mine.get(gems[end++])+1);
                else mine.put(gems[end++], 1);
            } else {
                // 모든 보석이 포함되어 있는 경우

                if(end-start < min_length) {
                    // 더 짧은 구간을 발견
                    min_length = end-start;
                    answer[0] = start+1;
                    answer[1] = end;
                }
                // start 포인터 이동
                if(mine.get(gems[start])>1) mine.put(gems[start], mine.get(gems[start++])-1);
                else mine.remove(gems[start++]);

            }

        }




        return answer;
    }
}