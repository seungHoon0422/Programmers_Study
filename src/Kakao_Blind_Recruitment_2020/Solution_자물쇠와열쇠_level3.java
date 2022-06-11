package Kakao_Blind_Recruitment_2020;

import java.util.ArrayList;

/**
 * 2020 kakao blind recruit 자물쇠와 열쇠 level3
[배열 회전]
문제요약 :
 key와 board가 주어졌을 때 주어진 키를 회전, 이동하여 board의 잠금을 풀 수 있는지 판단
 key는 꼭 board안에서만 움직여야 하는게 아니라 board만 완전히 벗어나지 않으면 된다.
 0은 홈, 1은 돌기를 의미


<solution>
 일단 key를 회전하기 위해서는, 배열 회전 알고리즘 필요
 근데 이문제에서는 key에 해당하는 배열을 모두 회전하기 보다
 왼쪽 위의 좌표를 base좌표로 잡고, 돌기의 상대적이 위치를 저장시키는 방식으로 적용

 키의 돌기가 모두 홈에 들어가거나, board의 밖에 위치하는지 확인하고
 board의 모든 홈이 키의 돌기와 만나는지 체크하는 방식으로 true를 확인한다.

 </solution>


 0 1 2 3
 1 2 3 4
 1 2 3 4
 1 2 3 4

 2,1 -> 1,2
 3,0 -> 0,1

 */
class Solution_자물쇠와열쇠_level3 {
    private int N;
    private int M;

    public boolean solution(int[][] k, int[][] l) {
        N = l.length;
        M = k.length;
        boolean answer = true;

        Key key = new Key();
        key.baser = 0;
        key.basec = 0;
        key.size = k.length;


        // key 돌기위치 저장
        for(int i=0; i<M; i++){
            for(int j=0; j<M; j++){
                if(k[i][j] == 1) key.checkPoints.add(new int[]{i,j});
            }
        }


        ArrayList<int[]> lock = new ArrayList<>();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(l[i][j] == 0) lock.add(new int[]{i,j});
            }
        }

//        System.out.println("Locker");
//        for(int[] pos : lock) {
//            System.out.println(pos[0]+" "+pos[1]);
//        }
        // 최대 4회 회전
        for(int r=0; r<4; r++){

            for(int i=1-M; i<N; i++) {
                for(int j=1-M; j<N; j++){
                    key.baser = i;
                    key.basec = j;
                    key.calc();
                    int keyCount = lock.size();

                    // 돌기끼리 마주치는지 확인
                    for(int a=0; a<key.realPoints.size(); a++){
                        int[] dolgi = key.realPoints.get(a);

                        // 범위 밖에있는 key 돌기는 패스
                        if(dolgi[0]<0 || dolgi[0]>=N || dolgi[1]<0 || dolgi[1]>=N) continue;

                        if(l[dolgi[0]][dolgi[1]] == 1) break; // 돌기끼리 마주친 경우
                        else keyCount--;
                    }

                    // key의 모든 홈이 채워졌는지 확인
//                    System.out.println("key count : " + keyCount);
                    if(keyCount == 0) return true;
                }
            }

            key.rotate();
        }

        return false;
    }

    static class Key {
        int baser, basec, size;
        ArrayList<int[]> checkPoints = new ArrayList<>();
        ArrayList<int[]> realPoints = new ArrayList<>();


        public void calc() {
            this.realPoints = new ArrayList<>();
            for (int i = 0; i < checkPoints.size(); i++) {
                this.realPoints.add(new int[]{baser + checkPoints.get(i)[0],basec + checkPoints.get(i)[1]});
            }
        }

        // 시계방향 회전
        public void rotate() {

            for (int i = 0; i < checkPoints.size(); i++) {
                this.checkPoints.set(i, new int[]{this.checkPoints.get(i)[1], size-this.checkPoints.get(i)[0]-1});
            }
        }

        public void print() {
            System.out.println("r : " + this.baser + ", c : " + this.basec);

            for(int[] pos : this.realPoints) {
                System.out.println(pos[0] + " " + pos[1]);
            }
        }

    }
/*   1 2 3 4
     5 6 7 8
     9 1 2 3
     4 5 6 7

       0,1 => 2,3
 */
}