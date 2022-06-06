package Kakao_Blind_Recruitment_2018;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
   문제요약 :
 두 문자열의 자카드 유사도를 측정
 문자열의 자카드 유사도는 2개씩 다중집합을 생성해서 비교
 영문을 제외한 character가 포함되어있으면 제외

 <solution>
 각 문자열별로 다중집합을 map으로 생성
 대소문자 구분이 없으므로 lower case로 변환해서 작업
 문자열을 띄어낸 후 다중 집합을 생성하고 유효성 검사 진행
 자카드 유사도를 측정할 때 공집합 끼리의 연산은 1로 일관

 </solution>

 */
class Solution_뉴스클리어링_level2 {
    private HashMap<String, Integer> map1;
    private HashMap<String, Integer> map2;

    public int solution(String str1, String str2) {
        int answer = 0;
        map1 = new HashMap<String, Integer>();
        map2 = new HashMap<String, Integer>();
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        makeSet(str1, str2);
        double result = jacquard();


        return (int) Math.floor(result * 65536);
    }

    private double jacquard() {
        Set<String> keyset1 = map1.keySet();
        Set<String> keyset2 = map2.keySet();

        Set<String> and = new HashSet<>();
        Set<String> or = new HashSet<>();

        for(String s : keyset1){
            if(keyset2.contains(s)) and.add(s);
        }

        for(String s : keyset1) or.add(s);
        for(String s : keyset2) or.add(s);

        int andCount = 0;
        int orCount = 0;

        for(String s : and) {
            andCount += Math.min(map1.get(s), map2.get(s));
        }

        for(String s : or) {
            if(map1.containsKey(s) && map2.containsKey(s))
                orCount += Math.max(map1.get(s), map2.get(s));
            else if(map1.containsKey(s)) orCount += map1.get(s);
            else orCount += map2.get(s);

        }
        if(orCount == 0) return 1.0;
        else return (andCount / (double) orCount);
    }


    private void makeSet(String str1, String str2) {
        for(int i=0; i<str1.length()-1; i++){
            String sub = str1.substring(i,i+2);
            if(validation(sub)){
                if(map1.containsKey(sub)) map1.put(sub, map1.get(sub)+1);
                else map1.put(sub, 1);
            }
        }

        for(int i=0; i<str2.length()-1; i++){
            String sub = str2.substring(i,i+2);
            System.out.println(sub);
            if(validation(sub)){
                if(map2.containsKey(sub)) map2.put(sub, map2.get(sub)+1);
                else map2.put(sub, 1);
            }
        }
    }

    private boolean validation(String sub) {
        if('a' <= sub.charAt(0) &&  sub.charAt(0) <= 'z'
            && 'a' <= sub.charAt(1) && sub.charAt(1) <= 'z') return true;
        return false;
    }
}