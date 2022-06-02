package Kakao_Internship_2021;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [BFS]
 *
 * 대개실의개수 5개
 * 응시자들 끼리는 맨해튼 거리 2이하로 배치
 * 단 사이에 파티션이 있는 경우에는 가능
 *
 * 거리두기를 잘하고 있으면 return 1, 아니면 return 0
 *
 * p : 응시자가 앉아있는 자리
 * o : 빈테이블
 * x : partition
 *
 * <solution>
 * 응시자의 좌표를 모두 담은 리스트 생성
 * 응시자마다 BFS탐색을 진행해서 맨해튼 거리 2안에 다른 응시자가 존재하는지 확인
 * @author SeongHoon
 *
 */

class Solution_거리두기확인하기 {

    static final int SIZE = 5;
    private int[] dr = {1,-1,0,0};
    private int[] dc = {0,0,1,-1};

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for(int i=0;i <SIZE; i++) {
            char[][] space = new char[SIZE][SIZE];

            // 확인할 강의실 char[][]로 변환
            for(int j=0; j<SIZE; j++) {
                space[j] = places[i][j].toCharArray();
            }
            answer[i] = checkSpace(space);
        }

        return answer;
    }

    private int checkSpace(char[][] space) {

        ArrayList<Node> list = new ArrayList<Node>();

        for(int i=0; i<SIZE; i++) {
            for(int j=0; j<SIZE; j++) {
                if(space[i][j] == 'P')
                    list.add(new Node(i,j));
            }
        }

        for(int index = 0; index<list.size(); index++) {
            Node node = list.get(index);

            Queue<Node> q = new LinkedList<Node>();
            q.offer(node);
            boolean[][] visited = new boolean[5][5];
            visited[node.r][node.c] = true;

            while(!q.isEmpty()){

                Node front = q.poll();
                for(int i=0; i<4; i++) {
                    int nr = front.r + dr[i];
                    int nc = front.c + dc[i];

                    if((Math.abs(node.r-nr) + Math.abs(node.c - nc)) >2) break;

                    if(0<=nr && nr<5 && 0<=nc && nc<5) {
                        if(!visited[nr][nc] && space[nr][nc] != 'X') {
                            if(space[nr][nc] == 'P') return 0;
                            else {
                                visited[nr][nc] = true;
                                q.offer(new Node(nr,nc));
                            }
                        }
                    }
                }
            }
        }


        return 1;
    }

    static class Node{
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }
}





