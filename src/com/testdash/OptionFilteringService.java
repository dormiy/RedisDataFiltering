package com.testdash;

import java.util.ArrayList;
import java.util.Random;


public class OptionFilteringService {


    private static int userKey;
    //private static ArrayList optionInput = new ArrayList ();
    private static RedisHandling redis;
    private String[] list = {"Factory", "Customer","Product","Package","Device","Partno","Stage","MFGStep","TestCode","TestProgram","ProgramRevision","LotType","Platform"};
    private boolean[] listOn ={false,false,false,false,false,false,false,false,false,false,false,false,false};

    public void userCreation() {
        Random rand = new Random ();
        userKey = rand.nextInt (999999);
        System.out.println ("user:" + userKey + " was created!");
    }

    public void userDel() {
        redis.commands.del ("user" + Integer.toString (userKey) + ":Factory");
        redis.commands.del ("user" + Integer.toString (userKey)+":selection");
        System.out.println ("user:" + userKey + " was deleted!");
        
    }


    public ArrayList getMenu(int index) {
        String nameMenu=list[index];

        ArrayList optionInput = new ArrayList ();
        //optionInput.clear ();
        String currentSelection = "user" + Integer.toString (userKey)+":selection";
        //String selectSet = "user" + Integer.toString (userKey);

        String selectSetTemp = "user" + Integer.toString (userKey) + "-temp";
        //redis.commands.del (selectSetTemp);

        if (listOnInter ()==false) {
            for (String x : redis.commands.smembers ("option" + ":" + nameMenu)) {
                //System.out.println("first time look for:" +x);
//                System.out.println("smembers from: " + "option" + ":" + nameMenu);
//                System.out.println("available choice are:" + x);
                optionInput.add (x);
            }
            // initialIndicator=false;
        } else {
            for (String s : redis.commands.smembers ("option" + ":" + nameMenu)) {

                Long sinterResult = redis.commands.sinterstore (selectSetTemp, currentSelection, "ATE" + ":" + nameMenu + ":" + s);
                if (sinterResult != 0) {
                    optionInput.add (s);
                }
            }
            redis.commands.del (selectSetTemp);
        }
        return optionInput;
    }

    public void selecionHandle(int i, String string) {
        ArrayList<String> result = new ArrayList<> ();
        result = resultBuild (string);
        String selectSetColumn = "user" + Integer.toString (userKey) + ":" + list[i];

        redis.commands.del (selectSetColumn);
        listOn[i]=true;

        for (String s : result) {
            String s1 = "ATE:" + list[i] + ":" + s;
            //System.out.println ("selection is: " + s);

            if (s.equals ("SelectNone")) {
                redis.commands.del (s1);
                listOn[i]=false;
            }
            else {
                redis.commands.sunionstore (selectSetColumn, selectSetColumn, s1);
                listOn[i]=true;
                //System.out.println ("abc was set to true");

            }
            //System.out.println ("select handler result: " + listOn[i]);
        }
    }

    public ArrayList resultBuild(String result) {
        ArrayList<String> string = new ArrayList ();
        StringBuilder s = new StringBuilder ();

        for (int j = 0; j < result.length (); j++) {
            if (result.charAt (j) == 32 || result.charAt (j) == 91) {
            } else if (result.charAt (j) == 44 || result.charAt (j) == 93) {
                if (s.toString ().equals ("null")) {
                } else {
                    string.add (s.toString ());
                    s.setLength (0);
                }
            } else
                s.append (Character.toString (result.charAt (j)));
        }
        return string;
    }

    public void resultInter(int index) {
        String currentSelection = "user" + Integer.toString (userKey)+":selection";

        boolean firstTime = true;
        String nextLocation;
        redis.commands.del (currentSelection);

        for (int i = 0; i < list.length; i++) {
            if (listOn[i] == true) {
                if (firstTime) {
                    nextLocation = "user" + Integer.toString (userKey)+ ":" + list[i];
                    redis.commands.sunionstore (currentSelection, currentSelection, nextLocation);
                    redis.commands.sinterstore (currentSelection, currentSelection, nextLocation);
                    firstTime = false;
                } else {
                    nextLocation = "user" + Integer.toString (userKey)+ ":" + list[i];
                    redis.commands.sinterstore (currentSelection, currentSelection, nextLocation);
                }
            }
        }
    }

    public boolean listOnInter(){
        boolean result = false;
        for(int i=0;i< listOn.length;i++){
            if(listOn[i]==true)
                result=true;

        }
        System.out.println("ListOn Intersection result is: " + result);

        return  result;
    }



}
