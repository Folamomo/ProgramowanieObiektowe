package crypto;

public class Polibiusz implements Algorithm {
    private char[][] szachownica = new char[5][5];

    public Polibiusz() {
        szachownica[0][0] = 'a';
        szachownica[0][1] = 'b';
        szachownica[0][2] = 'c';
        szachownica[0][3] = 'd';
        szachownica[0][4] = 'e';
        szachownica[1][0] = 'f';
        szachownica[1][1] = 'g';
        szachownica[1][2] = 'h';
        szachownica[1][3] = 'i'; // i/j
        szachownica[1][4] = 'k';
        szachownica[2][0] = 'l';
        szachownica[2][1] = 'm';
        szachownica[2][2] = 'n';
        szachownica[2][3] = 'o';
        szachownica[2][4] = 'p';
        szachownica[3][0] = 'q';
        szachownica[3][1] = 'r';
        szachownica[3][2] = 's';
        szachownica[3][3] = 't';
        szachownica[3][4] = 'u';
        szachownica[4][0] = 'v';
        szachownica[4][1] = 'w';
        szachownica[4][2] = 'x';
        szachownica[4][3] = 'y';
        szachownica[4][4] = 'z';
    }

    @Override
    public String crypt(String in) {
        String out = "";

        for (int i = 0; i < in.length(); i++) {
            char test = in.charAt(i);
            char lowerTest = Character.toLowerCase(test);
            if (lowerTest == 'j') lowerTest = 'i';
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if(szachownica[j][k] == lowerTest){
                        out += j+1;
                        out += k+1;
                    }
                }
            }
        }
        return out;
    }

    @Override
    public String decrypt(String in) {
               String out = "";
        for (int i = 0; i < in.length(); i = i+2) {
            out += szachownica[Character.getNumericValue(in.charAt(i))-1][Character.getNumericValue(in.charAt(i+1))-1];
        }
        return out;
    }
}
