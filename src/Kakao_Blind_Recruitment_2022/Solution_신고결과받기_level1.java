package Kakao_Blind_Recruitment_2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class Solution_신고결과받기_level1 {

    private HashMap<String, Integer> count;
    private HashMap<String, HashSet<String>> callers;
    private int N;

    /*
     * 각 유저는 여러명의 유저를 신고 가능
     * 신고 횟수에 제한 x, 동일 유저에 대한 신고 횟수는 1회
     * K번이상 신고된 유저는 게시판 이용이 정지
     *
     * 4:55
     */
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer= new int[id_list.length];
        N = id_list.length;

        callers = new HashMap<String, HashSet<String>>();
        count = new HashMap<>();

        // 유저마다 중복되지 않게 저장하기 위해서 set을 사용해서 신고한 사용자 목록 관리
        for(int i=0; i<N; i++) {
            callers.put(id_list[i], new HashSet<>());
            count.put(id_list[i], 0);

        }


        // 1. report 배열을 분석해서, 유저마다 신고한 사람 리스트를 작성, set으로 구현
        for(int i=0; i<report.length; i++) {

            String rep = report[i];
            String[] split = rep.split(" ");
            String caller = split[0];
            String callee = split[1];

            // 신고 당한 사람 유저 집에 신고자 add
            callers.get(caller).add(callee);
        }

        for(int i=0; i<id_list.length; i++) {
            HashSet<String> hashSet = callers.get(id_list[i]);
            for (String callee : hashSet) {
                count.put(callee, count.get(callee)+1);
            }


        }

        for(int i=0; i<N; i++) {

            for (String name : callers.get(id_list[i])) {
                if(count.get(name)>=k) answer[i]++;
            }

        }

        // 2. 신고한 사람 리스트를 돌면서 각 유저가 신고를 몇번 받았나 카운트




        return answer;

    }
} // end of solution