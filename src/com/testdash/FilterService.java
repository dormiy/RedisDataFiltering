package com.testdash;

import java.util.ArrayList;

import java.util.Scanner;
import java.util.Random;
import java.lang.String;
/**
 * Created by wuping on 6/22/2017.
 */
public class FilterService {

    private String[] column;
    private int[] columnNumber;
    private String redisServer;
    private String lOneTree;
    private String optionName;
    static RedisHandling redis;
    static Scanner input;
    static int userKey;
    static String masterKeyIn;
    static ArrayList secondKeyIn = new ArrayList();
    static boolean initialIndicator=true;

    public FilterService(String[] column, int[] columnNumber, String redisServer, String lOneTree, String optionName){
        this.column=column;
        this.columnNumber=columnNumber;
        this.redisServer=redisServer;
        this.lOneTree=lOneTree;
        this.optionName=optionName;
    }

    public void filterService(){

        redis= new RedisHandling(redisServer);
        redis.redisConnect();


        userCreation();

        for(int i=0;i<10;i++) {
            printMasterMenu();
            getMasterMenue();
            printSecondMenu();
            getSecondMenu();
            secondResultHandling();
        }

        redis.redisClose();

    }

    public void userCreation(){

        Random rand = new Random();
        userKey = rand.nextInt(999999);
        System.out.println("user:"+userKey+" was created!");

    }

    public void printMasterMenu(){
        int indicator=0;

        System.out.println("========Master Menu========");
        for(String s:column){
            System.out.println(indicator+":"+s);
            indicator++;
        }
        System.out.println("999:Exit");
        System.out.println("========Enter Your Choice========");
    }

    public void getMasterMenue(){
        input = new Scanner(System.in);
        int keyIn=input.nextInt();
        masterKeyIn=column[keyIn];
    }

    public void printSecondMenu(){
        secondKeyIn.clear();
        String selectSet = "user"+Integer.toString(userKey);
        String selectSetTemp = "user"+Integer.toString(userKey)+"-temp";
        redis.commands.del(selectSetTemp);


        System.out.println("========Secondary Menu========");
        if(initialIndicator){
            int count=0;
            for (String x:redis.commands.smembers(optionName + ":" + masterKeyIn)) {
                secondKeyIn.add(x);
            }
           // initialIndicator=false;
        }
        else{
            for (String s : redis.commands.smembers(optionName + ":" + masterKeyIn)) {
                Long sinterResult = redis.commands.sinterstore(selectSetTemp, selectSet, lOneTree + ":" + masterKeyIn + ":" + s);
                if (sinterResult != 0) {
                    secondKeyIn.add(s);
                }
            }
        }
        for(int i=0;i<secondKeyIn.size();i++) {
            System.out.println(i + ":" + secondKeyIn.get(i));
        }
        System.out.println("========Enter Your Choice========");
    }

    public void getSecondMenu(){
        input = new Scanner(System.in);
        String keyIn=input.next();

        String[] keyInString = new String [keyIn.length()];
        StringBuilder s = new StringBuilder();

        int i=0;

        for (int j = 0; j < keyIn.length(); j++) {
            if(keyIn.charAt(j)<58 && keyIn.charAt(j)>47) {
                s.append(Character.toString(keyIn.charAt(j)));
            }
            else {
                keyInString[i]=s.toString();
                i++;
                s.setLength(0);
            }
        }
        int inputKey[] = new int[i];
        for (int j=0; j<i; j++){
            inputKey[j]=Integer.parseInt(keyInString[j]);
        }

        ArrayList temp = new ArrayList();
        for(i=0;i<secondKeyIn.size();i++){
           temp.add(secondKeyIn.get(i));
        }
        secondKeyIn.clear();
        for(i=0;i< inputKey.length;i++){
            secondKeyIn.add(temp.get(inputKey[i]));
        }
    }


    public void secondResultHandling(){
        String selectSet = "user"+Integer.toString(userKey);
        String selectSetTemp = "user"+Integer.toString(userKey)+"-temp";
        String selectSetColumn="user"+Integer.toString(userKey)+masterKeyIn;
        redis.commands.del(selectSetTemp);

        //store second menu result into an temp union
        for(int i=0; i<secondKeyIn.size();i++){
            redis.commands.sunionstore(selectSetTemp, selectSetTemp,lOneTree+":"+masterKeyIn+":"+secondKeyIn.get(i));
            redis.commands.sunionstore(selectSetColumn, selectSetColumn,lOneTree+":"+masterKeyIn+":"+secondKeyIn.get(i));
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
}
