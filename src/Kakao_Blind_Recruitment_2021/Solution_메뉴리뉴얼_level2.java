package Kakao_Blind_Recruitment_2021;

import java.util.*;

/**
문제요약 :
손님별 단품으로 시킨 메뉴 리스트 중에서,
course에 포함된 개수만큼 세트메뉴로 만들려고 한다.
세트메뉴는 최소 2명이상 주문한 메뉴에 포함되어있어야 한다.

<solution>
 각 손님이 시킨 단품 리스트를 조합을 돌려서 map에 모두 저장하자.
 모두 저장한 후 세트메뉴의 개수를 카운트해서
 course의 길이에 해당하는 세트메뉴를 리턴
 리스트에 담아 문자열 정렬 실행
 </solution>



 */

class Solution_메뉴리뉴얼_level2 {
    private HashMap<String, Integer> map;

    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        map = new HashMap<String, Integer>();
        for(String order : orders) {
            char[] arr = order.toCharArray();
            Arrays.sort(arr);
            StringBuilder sb = new StringBuilder();
            for(char ch : arr) sb.append(ch);
            order = sb.toString();
            combination(0, 0, order.length(), "", order);
        }

        Map<Integer, Boolean> counts = new HashMap<>();
        for(int num : course)
            counts.put(num, true);

        Map<Integer, ArrayList<String>> ans = new HashMap<>();
        for (int i = 0; i < course.length; i++) {
            ans.put(course[i], new ArrayList<>());
        }

        for(String key : map.keySet()) {

            int count = map.get(key);
            if(ans.containsKey(key.length()) && count >= 2) {

                // 코스에 메뉴가 없는 경우
                if(ans.get(key.length()).size() == 0) {
                    ans.get(key.length()).add(key);
                } else {
                    // 코스에 이미 메뉴가 있는 경우
                    if(map.get(ans.get(key.length()).get(0)) == count) {
                        ans.get(key.length()).add(key);
                    } else if(count > map.get(ans.get(key.length()).get(0))){
                        ArrayList<String> list = new ArrayList<>();
                        list.add(key);
                        ans.put(key.length(), list);
                    }
                }
            }

        }

        ArrayList<String> result = new ArrayList<>();
        for(ArrayList<String> value : ans.values()) {
            for(String menu : value)
                result.add(menu);
        }
        Collections.sort(result);
        answer = new String[result.size()];
        for(int i=0; i<result.size(); i++)
            answer[i] = result.get(i);
        return answer;
    }

    private void combination(int index, int start, int length, String menu, String order) {
        if(index == length) {
            if(map.containsKey(menu)) map.put(menu, map.get(menu) + 1);
            else map.put(menu, 1);
            return;
        }

        String original = menu;
        for(int i=start; i<length; i++){
            combination(index+1, i+1, length, menu+order.charAt(i), order);
            combination(index+1, i+1, length, menu, order);
        }
    }
}