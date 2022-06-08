package Kakao_Blind_Recruitment_2022;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

class Solution_주차요금계산_level2 {
    private int baseTime;
    private int baseFee;
    private int perTIme;
    private int perFee;
    private int perTime;
    private HashMap<String, int[]> map;


    public int[] solution(int[] fees, String[] records) {

        int[] answer = {};

        baseTime = fees[0];
        baseFee = fees[1];
        perTime = fees[2];
        perFee = fees[3];

        map = new HashMap<String, int[]>();
        for(String record : records) {
            StringTokenizer st = new StringTokenizer(record, " ");
            String time = st.nextToken();
            String number = st.nextToken();
            String action = st.nextToken();

            st = new StringTokenizer(time, ":");
            int hour = Integer.parseInt(st.nextToken());
            int min = Integer.parseInt(st.nextToken());

            if("IN".equals(action)) {
                // 입차
                if(!map.containsKey(number)) {
                    map.put(number, new int[]{hour, min, 0,0});
                } else {
                    map.put(number, new int[]{hour, min, map.get(number)[2],0});
                }
            } else {
                // 출차
                int inhour = map.get(number)[0];
                int inmin = map.get(number)[1];
                int t = (hour-inhour)*60 + (min-inmin) + map.get(number)[2];
                map.put(number, new int[]{inhour, inmin, t,1});
            }
        }

        ArrayList<Car> list = new ArrayList<>();
        for(String num : map.keySet()) {
            int[] data = map.get(num);
            // 출차 기록이 없는 경우
            if(data[3] == 0) {
                data[2] += (23-data[0]) * 60 + (59-data[1]);
            }

            int runningTime = data[2] - baseTime;
            int pay = baseFee;
            if(runningTime > 0){
                pay += perFee * (runningTime / perTime);
                runningTime = runningTime%perTime;
            }
            if(runningTime > 0) pay += perFee;
            list.add(new Car(Integer.parseInt(num), pay));
        }

        Collections.sort(list);
        answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i).fee;
        }

        return answer;
    }

    static class Car implements Comparable<Car>{
        int number, fee;

        public Car(int number, int fee) {
            this.number = number;
            this.fee = fee;
        }

        @Override
        public int compareTo(Car o) {
            return this.number - o.number;
        }
    }
}