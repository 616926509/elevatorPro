package com.elevator.elevator.service;

import com.elevator.elevator.dao.CalculationDao;
import com.elevator.elevator.dao.SortDao;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

//电梯service层
@Service
public class SolutionService {
    public static JSONArray getSolution(JSONArray getData) {
        //总数据和电梯数据定义
        JSONArray personData = new JSONArray();
        JSONArray elevator1 = new JSONArray();
        JSONArray elevator2 = new JSONArray();
        JSONArray elevator3 = new JSONArray();
        JSONArray elevator4 = new JSONArray();
        //存储最大最小层对象
        JSONObject returnObj = new JSONObject();
        //总返回数据
        JSONArray returnData = new JSONArray();
        //排序类
        SortDao sortDao = new SortDao();
        //计算类
        CalculationDao calculationDao = new CalculationDao();
        //获取计算类的返回数据
        Map<String, JSONArray> map = new HashMap<String, JSONArray>();
        //获取层数计算返回数据
        Map<String, Integer> getFloor = new HashMap<>();
        //将乘客数据传值给personData
        personData = getData;
        //personData顺序排序
        personData = sortDao.sortArray(personData);
        //获取单层电梯计算结果
        map = calculationDao.upSingleAndDouble(personData, 10, 800, 1);
        elevator1 = map.get("elevator");
        personData = map.get("personData");
        returnData.put(elevator1);
        getFloor = getMaxMinFloor(elevator1, 1);
        try {
            returnObj.put("upMaxFloor1", getFloor.get("maxFloor"));
            returnObj.put("upMinFloor1", getFloor.get("minFloor"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //personData逆序排序
        personData = sortDao.sortArraydesc(personData);
        map = calculationDao.downSingleAndDouble(personData, 10, 800, 1);
        elevator1 = map.get("elevator");
        personData = map.get("personData");
        returnData.put(elevator1);
        getFloor = getMaxMinFloor(elevator1, 0);
        try {
            returnObj.put("downMaxFloor1", getFloor.get("maxFloor"));
            returnObj.put("downMinFloor1", getFloor.get("minFloor"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //获取双层电梯计算结果
        personData = sortDao.sortArray(personData);
        map = calculationDao.upSingleAndDouble(personData, 10, 800, 0);
        elevator2 = map.get("elevator");
        personData = map.get("personData");
        returnData.put(elevator2);
        getFloor = getMaxMinFloor(elevator2, 1);
        try {
            returnObj.put("upMaxFloor2", getFloor.get("maxFloor"));
            returnObj.put("upMinFloor2", getFloor.get("minFloor"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        personData = sortDao.sortArraydesc(personData);
        map = calculationDao.downSingleAndDouble(personData, 10, 800, 0);
        elevator2 = map.get("elevator");
        personData = map.get("personData");
        returnData.put(elevator2);
        getFloor = getMaxMinFloor(elevator2, 0);
        try {
            returnObj.put("downMaxFloor2", getFloor.get("maxFloor"));
            returnObj.put("downMinFloor2", getFloor.get("minFloor"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (personData.length() > 0) {
            //获取全部层数电梯/10人计算结果
            personData = sortDao.sortArray(personData);
            map = calculationDao.up(personData, 10, 800);
            elevator3 = map.get("elevator");
            personData = map.get("personData");
            returnData.put(elevator3);
            getFloor = getMaxMinFloor(elevator3, 1);
            try {
                returnObj.put("upMaxFloor3", getFloor.get("maxFloor"));
                returnObj.put("upMinFloor3", getFloor.get("minFloor"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            personData = sortDao.sortArraydesc(personData);
            map = calculationDao.down(personData, 10, 800);
            elevator3 = map.get("elevator");
            personData = map.get("personData");
            returnData.put(elevator3);
            getFloor = getMaxMinFloor(elevator3, 0);
            try {
                returnObj.put("downMaxFloor3", getFloor.get("maxFloor"));
                returnObj.put("downMinFloor3", getFloor.get("minFloor"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //获取全部层数电梯/20人计算结果
            personData = sortDao.sortArray(personData);
            map = calculationDao.up(personData, 20, 2000);
            elevator4 = map.get("elevator");
            personData = map.get("personData");
            returnData.put(elevator4);
            getFloor = getMaxMinFloor(elevator4, 1);
            try {
                returnObj.put("upMaxFloor4", getFloor.get("maxFloor"));
                returnObj.put("upMinFloor4", getFloor.get("minFloor"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            personData = sortDao.sortArraydesc(personData);
            map = calculationDao.down(personData, 20, 2000);
            elevator4 = map.get("elevator");
            personData = map.get("personData");
            returnData.put(elevator4);
            getFloor = getMaxMinFloor(elevator4, 0);
            try {
                returnObj.put("downMaxFloor4", getFloor.get("maxFloor"));
                returnObj.put("downMinFloor4", getFloor.get("minFloor"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        returnData.put(returnObj);
        return returnData;
    }

    //获得上升时最大和最小到达楼层和下降时最大和最小到达楼层
    //elevator为遍历的电梯信息，upOrDown为1时上升，为0时下降
    public static Map<String, Integer> getMaxMinFloor(JSONArray elevator, Integer upOrDown) {
        Map<String, Integer> map = new HashMap<>();
        int maxFloor = 0;
        int minFloor = 100;
        String str1 = "";
        String str2 = "";
        if (upOrDown == 1) {
            str1 = "correct";
            str2 = "now";
        } else {
            str1 = "now";
            str2 = "correct";
        }
        for (int i = 0; i < elevator.length(); i++) {
            //获取每层乘客信息
            JSONArray getMaxMin = new JSONArray();
            try {
                getMaxMin = elevator.getJSONArray(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < getMaxMin.length(); j++) {
                try {
                    if (maxFloor <= Integer.parseInt(getMaxMin.getJSONObject(j).getString(str1))) {
                        maxFloor = Integer.parseInt(getMaxMin.getJSONObject(j).getString(str1));
                    }
                    if (minFloor >= Integer.parseInt(getMaxMin.getJSONObject(j).getString(str2))) {
                        minFloor = Integer.parseInt(getMaxMin.getJSONObject(j).getString(str2));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }//end for j
        }//end for i
        map.put("maxFloor", maxFloor);
        map.put("minFloor", minFloor);
        return map;
    }

    //用于生成随机乘客信息测试
    public static void printData(JSONArray elevator, int num) {
        if (num == 1) {
            for (int i = 0; i < elevator.length(); i++) {
                try {
                    System.out.println(i + elevator.getJSONArray(i).toString() + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            for (int i = 0; i < elevator.length(); i++) {
                try {
                    System.out.println(elevator.length() - i + elevator.getJSONArray(i).toString() + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//    public static void getData() {
//        for (int i = 0; i < 100; i++) {
//            JSONObject jsonObject = new JSONObject();
//            Integer num = (int) (Math.random() * 20);
//            Integer num2 = (int) (Math.random() * 20);
//            Integer num3 = (int) (Math.random() * 100);
//            try {
//                jsonObject.put("now", num);
//                jsonObject.put("correct", num2);
//                if (num > num2) {
//                    jsonObject.put("updown", 0);
//                } else {
//                    jsonObject.put("updown", 1);
//                }
//                jsonObject.put("weight", num3);
//                personData.put(jsonObject);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
