package Kakao_Blind_Recruitment_2018;

import java.util.ArrayList;
import java.util.Collections;
/**

문제 요약 :
 LRU스케줄링 구현,
 cache의 크기가 주어지고, 입력되는 값들이 주어졌을 때
 hit는 1, miss는 5로 게산
 총 걸린시간 ?

 <solution>
 cache를 구현할 때 사용할 효율적인 자료구조로
 pq, dq, map다 고려해봤지만 size가 최대 30이므로
 miss되고 cache가 꽉 찼을 때 sort를 진행하는 방식으로 처리

 </solution>


 */
class Solution_캐시 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        ArrayList<City> cache = new ArrayList<>();

        if(cacheSize == 0) return cities.length * 5;
        int time = 0;
        for(String city : cities) {
            city = city.toLowerCase();
            boolean hit = false;
            for(int i=0; i<cache.size(); i++){
                if(cache.get(i).name.equals(city)) {
                    cache.set(i, new City(city, time++));
                    hit = true;
                    break;
                }
            }
            if(!hit) {
                if(cache.size() == cacheSize) {
                    Collections.sort(cache);
                    cache.remove(0);
                }
                cache.add(new City(city, time));
                time += 5;
            }
        }
        return time;
    }

    static class City implements Comparable<City>{
        String name;
        int time;

        public City(String name, int time) {
            this.name = name;
            this.time = time;
        }

        public int compareTo(City o) {
            return this.time - o.time;
        }
    }
}