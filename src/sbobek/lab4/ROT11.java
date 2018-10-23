package sbobek.lab4;

public class ROT11 implements Algorithm {
    int ascii;
    @Override
    public String crypt(String in) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            ascii = in.charAt(i);
            ascii += 11;
            if(ascii > 122){
                ascii = ascii - 26;
            }
            else if( ascii > 90 && ascii < 108){
                ascii = ascii - 26;
            }
            out.append((char) ascii);
        }
        return out.toString();
    }
    //97 - 122
//65 - 90
    @Override
    public String decrypt(String in) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            ascii = in.charAt(i);
            ascii -= 11;
            if(ascii < 97 && ascii > 86){
                ascii = ascii + 26;
            }
            else if( ascii < 65){
                ascii = ascii + 26;
            }
            out.append((char) ascii);
        }
        return out.toString();
    }
}
