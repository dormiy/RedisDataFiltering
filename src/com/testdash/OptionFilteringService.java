package com.testdash;

import java.util.ArrayList;
import java.util.Random;


public class OptionFilteringService {


    static int userKey;
    static ArrayList optionInput = new ArrayList();
    static RedisHandling redis;


    public void userCreation(){
        Random rand = new Random();
        userKey = rand.nextInt(999999);
        System.out.println("user:"+userKey+" was created!");
    }


    public ArrayList getMenu(String nameMenu, boolean initialIndicator) {
        optionInput.clear();
        String selectSet = "user" + Integer.toString(userKey);
        String selectSetTemp = "user" + Integer.toString(userKey) + "-temp";
        redis.commands.del(selectSetTemp);


        if (initialIndicator) {
            int count = 0;
            for (String x : redis.commands.smembers("option" + ":" + nameMenu)) {
                optionInput.add(x);
            }
            // initialIndicator=false;
        } else {
            for (String s : redis.commands.smembers("option" + ":" + nameMenu)) {
                Long sinterResult = redis.commands.sinterstore(selectSetTemp, selectSet, "ATE" + ":" + nameMenu + ":" + s);
                if (sinterResult != 0) {
                    optionInput.add(s);
                }
            }
        }
        /*for (int i = 0; i < secondKeyIn.size(); i++) {
            System.out.println(i + ":" + secondKeyIn.get(i));
        }*/



        return optionInput;


    }

    public void selecionHandle(String nameMenu, ArrayList<String> result, boolean initialIndicator){
        String selectSet = "user-"+Integer.toString(userKey);
        String selectSetTemp = "user-"+Integer.toString(userKey)+"-temp";
        String selectSetColumn="user-"+Integer.toString(userKey)+nameMenu;
        redis.commands.del(selectSetTemp);

        //store second menu result into an temp union
        for(int i=0; i<result.size ();i++){
            String s ="ATE:"+nameMenu+":"+result.get(i);
            redis.commands.sunionstore(selectSetTemp, selectSetTemp,s);
            redis.commands.sunionstore(selectSetColumn, selectSetColumn,s);
        }

        //initiate the user selection union when 1st time running
        //for 2nd onwards running, sinter the current selection with available union;
        if(initialIndicator){
            redis.commands.sunionstore(selectSet,selectSetTemp);
            initialIndicator=false;
        }
        else{
            redis.commands.sinterstore(selectSet,selectSet,selectSetTemp);
        }
    }


    public ArrayList resultBuild(String result) {
        ArrayList<String> string = new ArrayList ();
        StringBuilder s = new StringBuilder ();

        for (int j = 0; j < result.length (); j++) {
            if (result.charAt (j) == 32 || result.charAt (j) == 91) {
            } else if (result.charAt (j) == 44 || result.charAt (j) == 93) {
                string.add (s.toString ());
                s.setLength (0);
            } else
                s.append (Character.toString (result.charAt (j)));
        }
        return string;
    }


}
