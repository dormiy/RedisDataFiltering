package com.testdash;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;


public class OptionFilteringService {


    static int userKey;
    static ArrayList secondKeyIn = new ArrayList();
    static RedisHandling redis;
    static boolean initialIndicator=true;


    public void userCreation(){
        Random rand = new Random();
        userKey = rand.nextInt(999999);
        System.out.println("user:"+userKey+" was created!");
    }


    public ArrayList getMenu(String nameMenu) {
        secondKeyIn.clear();
        String selectSet = "user" + Integer.toString(userKey);
        String selectSetTemp = "user" + Integer.toString(userKey) + "-temp";
        redis.commands.del(selectSetTemp);


        if (initialIndicator) {
            int count = 0;
            for (String x : redis.commands.smembers("option" + ":" + nameMenu)) {
                secondKeyIn.add(x);
            }
            // initialIndicator=false;
        } else {
            for (String s : redis.commands.smembers("option" + ":" + nameMenu)) {
                Long sinterResult = redis.commands.sinterstore(selectSetTemp, selectSet, "ATE" + ":" + nameMenu + ":" + s);
                if (sinterResult != 0) {
                    secondKeyIn.add(s);
                }
            }
        }
        /*for (int i = 0; i < secondKeyIn.size(); i++) {
            System.out.println(i + ":" + secondKeyIn.get(i));
        }*/

        return secondKeyIn;


    }
}
