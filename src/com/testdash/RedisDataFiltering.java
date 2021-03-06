package com.testdash;



public class RedisDataFiltering {
    public static void main(String[] args){

        //String csvLocation = "C:/Users/wuping/WuPing/==Technical==/Code/temp/ate_all.csv";


        //String csvLocation = "/home/ping9981/Downloads/ate_all.csv";
        String csvLocation = "/Users/user/IdeaProjects/temp/ate_s.csv";

        String redisServer = "redis://TDnI123!@aurora:6379";
        //String redisServer="redis://localhost:6379";
        String[] csvColumn={"Factory","Customer","Product","Package","Device","MFGStep","Stage","TestProgram", "ProgramRevision","TestCode","LotType","Year","Month","Day","Hour", "Minute", "Second"};
        int[] csvColumnNumber ={5,6,7,8,9,11,12,14,15,16,87,93,94,95,96,97,98};

        String[] csvAllColumn = {
                "mother_lot_key"
                ,"camstar_lot_id_pk"
                ,"is_insert_error"
                ,"is_cal_error"
                ,"factory"
                ,"customer"
                ,"product"
                ,"package"
                ,"device"
                ,"lot_number"
                ,"mfg_step"
                ,"stage"
                ,"stage_group"
                ,"test_program"
                ,"program_version"
                ,"test_code"
                ,"lot_end_time"
                ,"lot_start_time"
                ,"tester_type"
                ,"platform"
                ,"year"
                ,"quarter"
                ,"month"
                ,"work_week"
                ,"day"
                ,"date"
                ,"unit_result"
                ,"yield_result"
                ,"source_type_no"
                ,"source_type"
                ,"system_time"
                ,"updated_time"
                ,"is_mother_matched"
                ,"is_cal_flag"
                ,"trigger_reload"
                ,"cal_total_touch_down_cnt"
                ,"cal_total_index_cnt"
                ,"cal_total_excessive_cnt"
                ,"cal_total_pause_cnt"
                ,"cal_total_tested_units"
                ,"cal_total_tested_fail_units"
                ,"cal_total_tested_pass_units"
                ,"cal_first_insertion_total_units"
                ,"cal_first_insertion_pass_units"
                ,"cal_last_insertion_pass_units"
                ,"cal_gross_test_time"
                ,"cal_total_index_time"
                ,"cal_total_net_test_time"
                ,"cal_total_fail_net_test_time"
                ,"cal_total_pass_net_test_time"
                ,"cal_total_actual_test_time"
                ,"cal_total_fail_actual_test_time"
                ,"cal_total_pass_actual_test_time"
                ,"cal_total_touch_down_test_time"
                ,"cal_total_tray_level_test_time"
                ,"cal_avg_touch_down_test_time"
                ,"cal_avg_tray_level_test_time"
                ,"cal_avg_index_time"
                ,"cal_avg_net_test_time"
                ,"cal_avg_fail_net_test_time"
                ,"cal_avg_pass_net_test_time"
                ,"cal_avg_actual_test_time"
                ,"cal_avg_fail_actual_test_time"
                ,"cal_avg_pass_actual_test_time"
                ,"cal_avg_excessive_time"
                ,"cal_avg_pause_time"
                ,"cal_excessive_time_pct"
                ,"cal_pause_time_pct"
                ,"cal_index_time_pct"
                ,"cal_net_test_time_pct"
                ,"cal_por_test_time"
                ,"cal_ftr"
                ,"cal_fy"
                ,"cal_ftrd"
                ,"cal_ir"
                ,"cal_kdf_ftr"
                ,"cal_kdf_fy"
                ,"cal_kdf_ftrd"
                ,"cal_kdf_ir"
                ,"cal_yms_ftrd"
                ,"cal_uph"
                ,"cal_uph_good"
                ,"cal_sort_out"
                ,"cal_stage_group_yield"
                ,"kdf_cnt"
                ,"cam_pline"
                ,"cam_type"
                ,"cam_insert"
                ,"cam_device"
                ,"cam_package"
                ,"cam_lot"
                ,"cam_date"
                ,"cyear"
                ,"cmonth"
                ,"cday"
                ,"chour"
                ,"cminute"
                ,"csecond"
                ,"cam_oper"
                ,"cam_temp"
                ,"cam_mother"
                ,"cam_datecode"
                ,"cam_tester"
                ,"cam_tcode"
                ,"cam_tprog"
                ,"cam_trev"
                ,"cam_qtyin"
                ,"cam_qtyout"
                ,"cam_yield"
                ,"cam_rej1"
                ,"cam_rej2"
                ,"cam_rej3"
                ,"cam_rej4"
                ,"cam_rej5"
                ,"cam_rej6"
                ,"cam_rej7"
                ,"cam_rej8"
                ,"cam_rej9"
                ,"cam_rej10"
                ,"cam_rej11"
                ,"cam_rej12"
                ,"cam_rej13"
                ,"cam_rej14"
                ,"cam_rej15"
                ,"cam_rej16"
                ,"cam_rejoth"
                ,"cam_b1"
                ,"cam_b2"
                ,"cam_b3"
                ,"cam_b4"
                ,"cam_b5"
                ,"cam_b6"
                ,"cam_b7"
                ,"cam_b8"
                ,"cam_b9"
                ,"cam_b10"
                ,"cam_b11"
                ,"cam_b12"
                ,"cam_b13"
                ,"cam_b14"
                ,"cam_b15"
                ,"cam_b16"
                ,"cam_speed"
                ,"cam_handler"
                ,"cam_wafer"
                ,"cam_dib"
                ,"cam_av"
                ,"cam_tfi"
                ,"cam_part"
                ,"cam_venc"
                ,"cam_partname"
                ,"cam_opn"
                ,"cam_owner"
                ,"cam_sample"
                ,"cam_mtype"
                ,"cam_frbin"
                ,"cam_frstep"
                ,"cam_tostep"
                ,"cam_rc1"
                ,"cam_rc2"
                ,"cam_rc3"
                ,"cam_rc4"
                ,"cam_rc5"
                ,"cam_rc6"
                ,"cam_rc7"
                ,"cam_rc8"
                ,"cam_rc9"
                ,"cam_rc10"
                ,"cam_rc11"
                ,"cam_rc12"
                ,"cam_rc13"
                ,"cam_rc14"
                ,"cam_rc15"
                ,"cam_rc16"
                ,"cam_fablotnumber"
                ,"cam_assembly_part"
                ,"cam_trackin"
                ,"cam_aspen"
                ,"cam_aspen_rev"
                ,"cam_nr1"
                ,"cam_nr2"
                ,"cam_nr3"
                ,"cam_nr4"
                ,"cam_nr5"
                ,"cam_nr6"
                ,"cam_nr7"
                ,"cam_nr8"
                ,"cam_nr9"
                ,"cam_nr10"
                ,"cam_nr11"
                ,"cam_nr12"
                ,"cam_nr13"
                ,"cam_nr14"
                ,"cam_nr15"
                ,"cam_nr16"
                ,"cam_vm1"
                ,"cam_vm2"
                ,"cam_vm3"
                ,"cam_vm4"
                ,"cam_vm5"
                ,"cam_vm6"
                ,"cam_test_phase"
                ,"cam_reclaimcount"
                ,"is_hold_lot"
                ,"recal_upd"
                ,"hold_lot_id_pk"
        };



        String lOneName = "ATE";
        String optionLOne= "option";

        CSVHandling csvInsert = new CSVHandling(csvLocation,csvColumn,csvColumnNumber,redisServer,lOneName,optionLOne,csvAllColumn);
        csvInsert.dataInserting();

        //FilterService filterService = new FilterService(csvColumn,csvColumnNumber,redisServer,lOneName,optionLOne);
        //filterService.filterService();
    }


}
