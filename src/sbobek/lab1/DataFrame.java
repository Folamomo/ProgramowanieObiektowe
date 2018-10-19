package sbobek.lab1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DataFrame {

    ArrayList<ArrayList> data = new ArrayList<ArrayList>();
    String[] names;
    String[] types;

    public DataFrame(String[] nazwy, String[] typy){
        System.arraycopy(nazwy, 0, names, 0,nazwy.length);
        System.arraycopy(typy, 0, types, 0, typy.length);
        for (int i =0; i<nazwy.length; ++i){
            data.add(new ArrayList());
        }
    }

    public DataFrame(String path, String[] typy){
        System.arraycopy(typy, 0, types, 0, typy.length);
        FileInputStream fstream = new FileInputStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        strLine = br.readLine();
        names = strLine.split(" ");

        for (int i =0; i< names.length; ++i){
            data.add(new ArrayList());
        }

        while ((strLine = br.readLine()) != null)   {

        }

//Close the input stream
        br.close();
    }
    public DataFrame(String path, String[] typy, boolean header){
        System.arraycopy(typy, 0, types, 0, typy.length);

    }
    public DataFrame(String path, String[] typy, boolean header, String[] headers){
        System.arraycopy(typy, 0, types, 0, typy.length);

    }

    int size(){
        return data.get(0).size();
    }
    ArrayList get(String colanme){
        for (int i=0; i<names.length; ++i){
            if (colanme.equals(names[i])) {
                return data.get(i);
            }
        }
        return new ArrayList();
    }
    DataFrame get (String[] cols, boolean copy){
        String [] nowe_typy = new String[cols.length];
        for (int i=0; i<cols.length; ++i){
            for (int j=0; j<names.length; ++j){
                if (cols[i].equals(names[j])){
                    nowe_typy[i]=types[j];
                }
            }
        }

        DataFrame result = new DataFrame(cols, nowe_typy);

        for (int i=0; i<cols.length; ++i){
            for (int j=0; j<names.length; ++j){
                if (cols[i].equals(names[j])){
                    if (copy){
                        result.data.get(j).addAll(data.get(i));
                    } else {
                        result.data.set(j, data.get(i));
                    }
                }
            }
        }
        return result;
    }
    DataFrame iloc (int i){
        DataFrame result = new DataFrame(names, types);
        for (int j=0; j<names.length; ++j){
            result.data.get(j).add(data.get(j).get(i));
        }
        return result;
    }
    DataFrame iloc (int from, int to){
        DataFrame result = new DataFrame(names, types);
        for (int j=0; j<names.length; ++j){
            result.data.get(j).addAll(data.get(j).subList(from, to));
        }
        return result;
    }
}
