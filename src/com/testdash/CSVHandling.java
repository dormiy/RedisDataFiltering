package com.testdash;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class CSVHandling {

    private String sourceLocation;
    private String[] column;
    private String[] allColumn;
    private int[] columnNumber;
    private String redisServer;
    private String lOneTree;
    private String optionName;

    public CSVHandling(String sourceLocation, String[] columnName,int[] columnNumber, String redisServer, String lOneTree, String optionName, String[] allColumnName){
        this.sourceLocation=sourceLocation;
        this.column =columnName;
        this.columnNumber=columnNumber;
        this.redisServer=redisServer;
        this.lOneTree = lOneTree;
        this.optionName=optionName;
        this.allColumn=allColumnName;
    }

    public void dataInserting(){
        RedisHandling redis = new RedisHandling(redisServer);
        redis.redisConnect();

        try {
            CSVReader csvReader = new CSVReader(new FileReader(sourceLocation));
            String[] nextLine;
            int count = 1;
            int allColumnLength = allColumn.length;

            int columnCount = columnNumber.length;

            System.out.println("data insert starts");

            while ((nextLine = csvReader.readNext()) != null) {
                for (int i = 0; i < columnCount; i++) {
                    redis.commands.sadd(lOneTree + ":" + column[i] + ":" +nextLine[columnNumber[i]], Integer.toString(count));
                    redis.commands.sadd(optionName+":"+column[i],nextLine[columnNumber[i]]);

                    for(int j=0; j< allColumnLength; j++){
                        redis.commands.hset("lotDetailed:"+count, allColumn[j], nextLine[j]);
                    }
                }
                count++;

                if (count % 10 == 0) {
                    System.out.println("the script has completed inserting lines of: " + count);
                }
            }
            csvReader.close();
        }
        catch(IOException ex)
        {
            System.out.println("exception during data inserting");
        }
        redis.redisClose();
    }
}
