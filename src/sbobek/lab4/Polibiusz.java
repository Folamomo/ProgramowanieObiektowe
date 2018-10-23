package sbobek.lab4;

public class Polibiusz implements Algorithm {
    private char[][] szachownica = new char[5][5];

    public Polibiusz() {
        char c = 'a';
        for (int i=0; i<25; ++i){
            szachownica[i/5][i%5] = c++;
            if (c == 'j'){
                ++c;
            }
        }


    }

    @Override
    public String crypt(String in) {
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < in.length(); i++) {
            char test = in.charAt(i);
            char lowerTest = Character.toLowerCase(test);
            if (lowerTest == 'j') lowerTest = 'i';
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if(szachownica[j][k] == lowerTest){
                        out.append(j + 1);
                        out.append(k + 1);
                    }
                }
            }
        }
        return out.toString();
    }

    @Override
    public String decrypt(String in) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < in.length(); i = i+2) {
            out.append(szachownica[Character.getNumericValue(in.charAt(i)) - 1][Character.getNumericValue(in.charAt(i + 1)) - 1]);
        }
        return out.toString();
    }
}