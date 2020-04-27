package com.elevator.elevator.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

//排序类
public class SortDao {
    static int maxFloor = 0;
    static int minFloor = 100;

    //以correct为目标顺序排序
    public JSONArray sortArray(JSONArray personData) {
        JSONArray sortedJsonArray = new JSONArray();
        //System.out.print(personData.toString()+"\n");
        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < personData.length(); i++) {
            try {
                jsonValues.add(personData.getJSONObject(i));
                if (((maxFloor <= Integer.parseInt(personData.getJSONObject(i).getString("correct")))
                        && Integer.parseInt(personData.getJSONObject(i).getString("updown")) == 1)
                        || ((maxFloor <= Integer.parseInt(personData.getJSONObject(i).getString("now")))
                        && Integer.parseInt(personData.getJSONObject(i).getString("updown")) == 0)) {
                    maxFloor = Integer.parseInt(personData.getJSONObject(i).getString("correct"));
                }
                if (((minFloor >= Integer.parseInt(personData.getJSONObject(i).getString("correct")))
                        && Integer.parseInt(personData.getJSONObject(i).getString("updown")) == 1)
                        || ((minFloor >= Integer.parseInt(personData.getJSONObject(i).getString("now")))
                        && Integer.parseInt(personData.getJSONObject(i).getString("updown")) == 0)) {
                    minFloor = Integer.parseInt(personData.getJSONObject(i).getString("correct"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //System.out.print(jsonValues.toString()+"\n");
        jsonValues.sort(new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                try {
                    String value1 = o1.getString("now");
                    String value2 = o2.getString("now");
                    if (Integer.parseInt(value1) > Integer.parseInt(value2)) {
                        return 1;
                    } else if (Integer.parseInt(value1) == Integer.parseInt(value2)) {
                        return 0;
                    } else {
                        return -1;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        for (int i = 0; i < jsonValues.size(); i++) {
            sortedJsonArray.put(jsonValues.get(i));
        }
        return sortedJsonArray;
        //System.out.print(sortedJsonArray.toString()+"\n");
    }

    //以correct为目标逆序排序
    public JSONArray sortArraydesc(JSONArray personData) {
        JSONArray sortedJsonArray = new JSONArray();
        //System.out.print(personData.toString()+"\n");
        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < personData.length(); i++) {
            try {
                jsonValues.add(personData.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //System.out.print(jsonValues.toString()+"\n");
        jsonValues.sort(new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                try {
                    String value1 = o1.getString("now");
                    String value2 = o2.getString("now");
                    if (Integer.parseInt(value1) > Integer.parseInt(value2)) {
                        return -1;
                    } else if (Integer.parseInt(value1) == Integer.parseInt(value2)) {
                        return 0;
                    } else {
                        return 1;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        for (int i = 0; i < jsonValues.size(); i++) {
            sortedJsonArray.put(jsonValues.get(i));
        }
        return sortedJsonArray;
        //System.out.print(sortedJsonArray.toString()+"\n");
    }
//    public Map<String,Integer> getFloor(){
//        Map<String,Integer> map = new HashMap<>();
//        map.put("maxFloor",maxFloor);
//        map.put("minFloor",minFloor);
//        return map;
//    }
}
