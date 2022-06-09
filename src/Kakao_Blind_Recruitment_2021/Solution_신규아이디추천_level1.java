package Kakao_Blind_Recruitment_2021;

class Solution_신규아이디추천_level1 {
    public String solution(String id) {
        String answer = "";

        id = id.toLowerCase();
        for(int i=0; i<id.length(); i++){
            char c = id.charAt(i);
            if('a'<=c && c<='z') continue;
            else if('0'<=c && c<='9') continue;
            else if(c=='-' || c=='_' || c=='.') continue;
            id = id.replace(c, 'A');
        }
        id = id.replaceAll("A", "");
        while(id.contains(".."))
            id = id.replace("..", ".");
        if(id.length() > 0 && id.charAt(0) == '.') id = id.substring(1);
        if(id.length() > 0 && id.charAt(id.length()-1) == '.') id = id.substring(0, id.length()-1);
        if(id.length()  == 0 ) id = "a";
        if(id.length()>=16) id = id.substring(0, 15);
        if(id.charAt(id.length()-1) == '.') id = id.substring(0, id.length()-1);
        char lastch = id.charAt(id.length()-1);
        while(id.length() < 3) {
            id += lastch;
        }
        return id;
    }
}