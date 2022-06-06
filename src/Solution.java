import java.util.*;

class Solution {
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