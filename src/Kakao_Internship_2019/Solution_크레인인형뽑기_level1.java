package Kakao_Internship_2019;

import java.util.Stack;

/**
 * [구현, 스택]
 *
 * <solution>
 *     바구니는 top을 확인해서 같은모양이면 pop
 *     다른모양이면 push
 *
 *     크레인으로 인형을 꺼낼때에는 stack에 들어있는지 확인하고,
 *     들어있으면 pop()으로 꺼낸다.
 *
 * </solution>
 */
class Solution_크레인인형뽑기_level1 {

    static int size;
    static Stack<Integer>[] map;
    static Stack<Integer> basket;
    static int answer;
    
    public int solution(int[][] board, int[] moves) {
        answer = 0;
        size = board.length;
        map = new Stack[size];
        basket = new Stack<>();

        for(int i=0; i<size; i++) map[i] = new Stack<>();

        for(int i=size-1; i>=0; i--){
            for(int j=0; j<size; j++){
                if(board[i][j] != 0) map[j].push(board[i][j]);
            }
        }

        for(int index : moves) {
            index = index -1;
            getFace(index);
        }

        return answer;
    }

    private void getFace(int index) {
        
        if(map[index].isEmpty()) return;
        pushBaket(map[index].pop());
    }

    private void pushBaket(Integer face) {
        if(!basket.isEmpty() && basket.peek() == face) {
            basket.pop();
            answer += 2;
        } else basket.push(face);

    }
}




