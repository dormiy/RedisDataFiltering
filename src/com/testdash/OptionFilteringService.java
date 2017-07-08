package com.testdash;

import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class OptionFilteringService {


    private static int userKey;
    //private static ArrayList optionInput = new ArrayList ();
    private static RedisHandling redis;
    private String[] list = {"Factory", "Customer", "Product", "Package", "Device",  "MFGStep",  "Stage", "TestProgram", "ProgramRevision", "TestCode","LotType"};
    private boolean[] listOn = {false, false, false, false, false, false, false, false, false, false, false, false, false};
    private boolean[] selectNoneOn = {false, false, false, false, false, false, false, false, false, false, false, false, false};


    public boolean getListOn(int i) {
        // System.out.println("this list is: " + listOn[i]);
        return listOn[i];
    }


    public void userCreation() {
        Random rand = new Random();
        userKey = rand.nextInt(999999);
        System.out.println("user:" + userKey + " was created!");
    }

    public void userDel() {
        redis.commands.del("user" + Integer.toString(userKey) + ":Factory");
        redis.commands.del("user" + Integer.toString(userKey) + ":selection");
        System.out.println("user:" + userKey + " was deleted!");
    }


    public ArrayList getMenu(int index) {
        String nameMenu = list[index];
        ArrayList optionInput = new ArrayList();
        String currentSelection = "user" + Integer.toString(userKey) + ":selection";
        String selectSetTemp = "user" + Integer.toString(userKey) + "-temp";
        if (listOnInter()) {
            for (String s : redis.commands.smembers("option" + ":" + nameMenu)) {
                if (!s.isEmpty()) {
                    Long sinterResult = redis.commands.sinterstore(selectSetTemp, currentSelection, "ATE" + ":" + nameMenu + ":" + s);
                    if (sinterResult != 0) {
                        optionInput.add(s);
                    }
                }
            }
            redis.commands.del(selectSetTemp);
        } else {
            for (String x : redis.commands.smembers("option" + ":" + nameMenu)) {
                if (!x.isEmpty()) {
                    optionInput.add(x);
                }
            }
        }
        return optionInput;
    }

    public void selectionHandle(int i, ObservableList<String> input) {
        String selectSetColumn = "user" + Integer.toString(userKey) + ":" + list[i];
        redis.commands.del(selectSetColumn);
        if (input.isEmpty()) {
            //           System.out.println(" none entered");
            redis.commands.del(selectSetColumn);
            listOn[i] = false;
            selectNoneOn[i] = true;
        } else {
            for (String s : input) {
                String s1 = "ATE:" + list[i] + ":" + s;
                redis.commands.sunionstore(selectSetColumn, selectSetColumn, s1);
                listOn[i] = true;
                selectNoneOn[i] = false;
            }
        }
    }


    public void resultInter() {
        String currentSelection = "user" + Integer.toString(userKey) + ":selection";
        boolean firstTime = true;
        String nextLocation;
        redis.commands.del(currentSelection);
        for (int i = 0; i < list.length; i++) {
            if (listOn[i]) {
                if (firstTime) {
                    nextLocation = "user" + Integer.toString(userKey) + ":" + list[i];
                    redis.commands.sunionstore(currentSelection, currentSelection, nextLocation);
                    redis.commands.sinterstore(currentSelection, currentSelection, nextLocation);
                    firstTime = false;
                } else {
                    nextLocation = "user" + Integer.toString(userKey) + ":" + list[i];
                    redis.commands.sinterstore(currentSelection, currentSelection, nextLocation);
                }
            }
        }
    }

    public boolean listOnInter() {
        boolean result = false;
        for (int i = 0; i < listOn.length; i++) {
            if (listOn[i]) {
                result = true;
            }
        }
//        System.out.println("ListOn Intersection result is: " + result);
        return result;
    }


    public ArrayList<String> getSelection(){
        String currentSelection = "user" + Integer.toString(userKey) + ":selection";
        ArrayList<String> result = new ArrayList<> ();

        Set<String> selectionResult = redis.commands.smembers (currentSelection);
        for (String s:selectionResult){
            result.add(redis.commands.hget("lotDetailed:"+s, "lot_number"));
        }
        //for (String x: result){System.out.println(x);}
        return result;
    }





}
