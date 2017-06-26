package com.testdash;

import java.util.logging.Filter;

/**
 * Created by wuping on 6/21/2017.
 */
public class RedisDataFiltering {
    public static void main(String[] args){

        String csvLocation = "C:/Users/wuping/WuPing/==Technical==/Code/temp/ate_mother_lot_full.csv";
        String redisServer = "redis://TDnI123!@aurora:6379";
        String[] csvColumn={"Factory","Customer","Product","Package","Device","MFGStep","Stage","TestProgram", "ProgramRevision","TestCode","LotType"};
        int[] csvColumnNumber ={5,6,7,8,9,11,12,14,15,16,87};
        String lOneName = "ATE";
        String optionLOne= "option";

        //CSVHandling csvInsert = new CSVHandling(csvLocation,csvColumn,csvColumnNumber,redisServer,lOneName,optionLOne);
        //csvInsert.dataInserting();

        FilterService filterService = new FilterService(csvColumn,csvColumnNumber,redisServer,lOneName,optionLOne);
        filterService.filterService();
    }


}
