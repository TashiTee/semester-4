package sample;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.function.UnaryOperator;


public class PopulationRank {
    List rank;
    List boyNum;
    List girlNum;
    List boyName;
    List girlName;

    public PopulationRank() {
        rank = new ArrayList();
        boyNum = new ArrayList();
        girlNum = new ArrayList();
        boyName = new ArrayList();
        girlName = new ArrayList();
    }

    public void initialize() {
        rank.clear();
        boyNum.clear();
        girlNum.clear();
        boyName.clear();
        girlName.clear();
    }

    public int findName(String gender, String name) {
        int rankNum = -1;
        List list = new ArrayList();
        UnaryOperator<String> uo = (x)->x.toUpperCase();

        if(gender.equals("M")) {
            boyName.replaceAll(uo);
            if(boyName.contains(name.toUpperCase())) {
                rankNum = boyName.indexOf(name.toUpperCase()) + 1;
            }
        }else {
            girlName.replaceAll(uo);
            if(girlName.contains(name.toUpperCase())) {
                rankNum = girlName.indexOf(name.toUpperCase()) + 1;
            }
        }

        return rankNum;
    }

    public void getFileInfo(String fileName) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("babynamesranking" + fileName + ".txt"));
            String line = reader.readLine();
            int count = 0;

            while(line != null) {
                StringTokenizer token;

                try {
                    if(line != null) {
                        token = new StringTokenizer(line);

                        rank.add(token.nextToken());
                        boyName.add(token.nextToken());
                        boyNum.add(token.nextToken());
                        girlName.add(token.nextToken());
                        girlNum.add(token.nextToken());

                    }

                }catch(Exception e) {
                    System.out.println("Exception: " + e);
                }

                line = reader.readLine();
            }
            reader.close();
            count++;


        }catch(IOException e) {
            System.out.println("Exception: " + e);
        }
    }
}
