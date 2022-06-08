package Kakao_Blind_Recruitment_2022;

import java.util.ArrayList;
import java.util.List;

class Solution_양과늑대_level3 {
    private int[] info;
    private int n;
    private int answer;
    private ArrayList<Integer>[] child;

    public int solution(int[] in, int[][] e) {
        answer = 0;
        n = in.length;
        info = new int[n];
        info = in.clone();
        child = new ArrayList[n];
        for(int[] edge : e) {
            int p = edge[0];
            int ch = edge[1];
            if(child[p] == null) child[p] = new ArrayList<>();
            child[p].add(ch);
        }


        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        travel(0, 0,0, list);

        return answer;
    }

    private void travel(int index, int sheep, int wolf, List<Integer> nextPos) {

        if(info[index]==0) sheep++;
        else wolf++;

        if(wolf >= sheep) return;
        answer = Math.max(answer, sheep);

        ArrayList<Integer> next = new ArrayList<>(nextPos);
        next.remove(Integer.valueOf(index));
        if(child[index] != null){
            next.addAll(child[index]);
        }

        for(int i : next) {
            travel(i, sheep, wolf, next);
        }
    }
}