package sbobek.lab1;

public class PESEL {
    public static boolean check (String toChceck){
        if (toChceck.length()!=11) return false;
        char [] digitChars = new char [11];
        toChceck.getChars(0, 11,digitChars,0);
        int [] digits = new int [11];
        for (int i=0; i<11; ++i){
            digits[i]=digitChars[i]-'0';
        }


        if (digits[2]*10+digits[3]>42) return false;

        if (digits[4]*10+digits[5]>31) return false;

        int checksum = 9*digits[0]+7*digits[1]+3*digits[2]+digits[3]+9*digits[4]+7*digits[5]+3*digits[6]+digits[7]+9*digits[8]+7*digits[9];

        return checksum%10==digits[10];
    }

}
