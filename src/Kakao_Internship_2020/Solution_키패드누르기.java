package Kakao_Internship_2020;

/**
 *  [구현]
 *  왼손은 * (3,0), 오른손은 #(3,2)에서 시작
 *
 */
class Solution_키패드누르기 {
    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();

        Position left = new Position(3,0);
        Position right = new Position(3,2);

        Position[] key = new Position[10];
        key[0] = new Position(3,1);
        for(int i=1; i<10; i++)  key[i] = new Position((i-1)/3, (i-1)%3);

        for(int number : numbers) {
            Position position = key[number];

            if(number %3 == 1) {
                //무조건 왼손
                left.r = position.r;
                left.c = position.c;
                answer.append("L");

            } else if( number % 3 == 0 && number != 0) {
                // 무조건 오른손
                right.r = position.r;
                right.c = position.c;
                answer.append("R");

            } else {
                // 가운데
                int ld = getDistance(left, position);
                int rd = getDistance(right, position);

                if(ld < rd) {
                    left.r = position.r;
                    left.c = position.c;
                    answer.append("L");
                } else if(rd < ld){
                    right.r = position.r;
                    right.c = position.c;
                    answer.append("R");
                } else {
                    if("right".equals(hand)) {
                        // 오른손 잡이인 경우
                        right.r = position.r;
                        right.c = position.c;
                        answer.append("R");
                    } else{
                        // 왼손잡이인 경우
                        left.r = position.r;
                        left.c = position.c;
                        answer.append("L");
                    }
                }
            }

        }


        return answer.toString();
    }

    private void movePosition(Position hand, Position position) {
        hand.r = position.r;
        hand.c = position.c;
    }

    public int getDistance(Position pos1, Position pos2){
        return Math.abs(pos1.r - pos2.r) + Math.abs(pos1.c - pos2.c);
    }

    static class Position{
        int r, c;

        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}