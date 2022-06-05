package Kakao_Internship_2019;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

    class Solution_튜플_level2 {

        private StringTokenizer st;
        ArrayList<ArrayList<Integer>> datas;
        // input : {{2},{2,1},{2,1,3},{2,1,3,4}}
        public int[] solution(String s) {
            ArrayList<Integer> answer = new ArrayList<>();
            s = s.substring(1,s.length()-1);
            ArrayList<Integer> leftBracket = new ArrayList<>();
            ArrayList<Integer> rightBracket = new ArrayList<>();
            datas = new ArrayList<>();

            for(int i=0; i<s.length(); i++){
                if(s.charAt(i) == '{') leftBracket.add(i);
                if(s.charAt(i) == '}') rightBracket.add(i);
            }

            for(int i=0; i<leftBracket.size(); i++){
                String set = s.substring(leftBracket.get(i)+1, rightBracket.get(i));
                StringTokenizer st = new StringTokenizer(set, ",");
                ArrayList<Integer> list = new ArrayList<>();
                while(st.hasMoreElements()){
                    list.add(Integer.parseInt(st.nextToken()));
                }
                datas.add(list);
            }
            Collections.sort(datas, (a,b) -> a.size()-b.size());

            int index = 0;
            for(ArrayList<Integer> list : datas){
                for(int value : list) {
                    if(!answer.contains(value)) {
                        answer.add(value);
                        break;
                    }
                }
            }
            int[] result = new int[answer.size()];
            for(int i = 0; i < answer.size(); i++)
                result[i] = answer.get(i);



            return result;
        }
    }






