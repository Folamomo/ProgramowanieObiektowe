package zadania;

import java.util.ArrayList;

public class durszlak {
    public static void main(String[] args){
        int max = 4294967296;
        boolean[] isPrime = new boolean [max];
        for (int i=3; i<max; ++i){
            isPrime[i]=true;
        }
        isPrime[2]=true;
        for(int i=3; i<Math.sqrt(max)+1; i+=2){
            if (isPrime[i]){
                for(int j=3*i; j < max; j+=2*i){
                    isPrime[j]=false;
                }
            }
        }
        ArrayList pierwsze = new ArrayList();
        pierwsze.add(2);
        for (int i=3; i<max; i+=2) {
            if (isPrime[i]) {
                pierwsze.add(i);
            }
        }
        return pierwsze;
    }
}

