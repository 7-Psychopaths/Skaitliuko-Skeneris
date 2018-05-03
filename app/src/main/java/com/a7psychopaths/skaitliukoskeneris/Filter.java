package com.a7psychopaths.skaitliukoskeneris;

public class Filter {
    static String dataType="";
    static String date="";

    static String getDataType(){
        return dataType;
    }

    static void setDataType(String type){
        dataType = type;
    }

    static String getDate(){
        return date;
    }

    static String getUrl(){
//        if(getDataType().equals("Date")) {
//            return "http://milvada.lt/procesas/getfilter.php?type=" + getDate();
//        } else {
//            return "http://milvada.lt/procesas/gettypefilter.php?type=" + getDataType();
//        }

        if(getDataType().equals("")){
            return "http://milvada.lt/procesas/getdujos.php";
        } else {
            return "http://milvada.lt/procesas/gettypefilter.php?type=" + getDataType();
        }
    }
}