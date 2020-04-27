package com.elevator.elevator.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//实现电梯运行结果
public class CalculationDao {
    //全部楼层电梯上升情况
    public Map<String, JSONArray> up(JSONArray personData, int MaxPerson, int MaxWeight) {
        //存储全部楼层电梯总信息和personData
        Map<String, JSONArray> map = new HashMap<String, JSONArray>();
        //存储电梯总信息
        JSONArray elevator1 = new JSONArray();
        int sumWeight = 0;
        int personMount = 0;
        //存储电梯总临时信息
        JSONArray eachInfor = new JSONArray();
        for (int i = 0; i <= 21; i++) {
            //获取电梯每层信息作为返回数据
            JSONArray eachInforReturn = new JSONArray();
            //对personData进行已存储数据的删除
            for (int j = 0; j < eachInfor.length(); j++) {
                try {
                    //删除personData里已经接到电梯中的乘客信息
                    if (Integer.parseInt(eachInfor.getJSONObject(j).getString("correct")) <= i) {
                        sumWeight -= Integer.parseInt(eachInfor.getJSONObject(j).getString("weight"));
                        for (int k = 0; k < personData.length(); k++) {
                            if (personData.getJSONObject(k).getString("correct").equals(eachInfor.getJSONObject(j).getString("correct")) &&
                                    personData.getJSONObject(k).getString("updown").equals(eachInfor.getJSONObject(j).getString("updown")) &&
                                    personData.getJSONObject(k).getString("now").equals(eachInfor.getJSONObject(j).getString("now")) &&
                                    personData.getJSONObject(k).getString("weight").equals(eachInfor.getJSONObject(j).getString("weight"))
                            ) {
                                personData.remove(k);
                                k--;
                            }
                        }
                        eachInfor.remove(j);
                        personMount--;
                        j--;
                    }//end if
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }//end for
            //System.out.printf(i+"\n");
            //判断当前电梯的超载情况，未超载接乘客
            for (int j = 0; j < personData.length(); j++) {
                try {
                    //获取单个乘客的信息
                    JSONObject searchPassenger = personData.getJSONObject(j);
                    if (Integer.parseInt(searchPassenger.getString("now")) > i || sumWeight >= MaxWeight || personMount >= MaxPerson)
                        break;
                    if (Integer.parseInt(searchPassenger.getString("now")) == i && Integer.parseInt(searchPassenger.getString("updown")) == 1) {
                        sumWeight += Integer.parseInt(searchPassenger.getString("weight"));
                        personMount++;
                        eachInfor.put(searchPassenger);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }//end for
            //将临时信息存储进返回每层数据中
            for (int j = 0; j < eachInfor.length(); j++) {
                try {
                    eachInforReturn.put(eachInfor.getJSONObject(j));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }//end for
            //将信息存储进总数据中
            elevator1.put(eachInforReturn);
        }//end for i
        map.put("elevator", elevator1);
        map.put("personData", personData);
        //System.out.println(personData);
        return map;
    }

    //全部楼层电梯下降情况
    public Map<String, JSONArray> down(JSONArray personData, int MaxPerson, int MaxWeight) {
        //存储全部楼层电梯总信息和personData
        Map<String, JSONArray> map = new HashMap<String, JSONArray>();
        //存储电梯总信息
        JSONArray elevator1 = new JSONArray();
        int start = 0;
        //获取下降时的最大考虑高度
        try {
            if (personData.length() > 0)
                start = Integer.parseInt(personData.getJSONObject(0).getString("now"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int sumWeight = 0;
        int personMount = 0;
        //存储电梯总临时信息
        JSONArray eachInfor = new JSONArray();
        for (int i = start; i >= 0; i--) {
            //获取电梯每层信息作为返回数据
            JSONArray eachInforReturn = new JSONArray();
            //对personData进行已存储数据的删除
            for (int j = 0; j < eachInfor.length(); j++) {
                try {
                    //删除personData里已经接到电梯中的乘客信息
                    if (Integer.parseInt(eachInfor.getJSONObject(j).getString("correct")) >= i) {
                        sumWeight -= Integer.parseInt(eachInfor.getJSONObject(j).getString("weight"));
                        for (int k = 0; k < personData.length(); k++) {
                            if (personData.getJSONObject(k).getString("correct").equals(eachInfor.getJSONObject(j).getString("correct")) &&
                                    personData.getJSONObject(k).getString("updown").equals(eachInfor.getJSONObject(j).getString("updown")) &&
                                    personData.getJSONObject(k).getString("now").equals(eachInfor.getJSONObject(j).getString("now")) &&
                                    personData.getJSONObject(k).getString("weight").equals(eachInfor.getJSONObject(j).getString("weight"))
                            ) {
                                personData.remove(k);
                                k--;
                            }
                        }
                        eachInfor.remove(j);
                        personMount--;
                        j--;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }//end for
            //System.out.printf(i+"\n");
            //判断当前电梯的超载情况，未超载接乘客
            for (int j = 0; j < personData.length(); j++) {
                try {
                    JSONObject searchPassenger = personData.getJSONObject(j);
                    if (Integer.parseInt(searchPassenger.getString("now")) < i || sumWeight >= MaxWeight || personMount >= MaxPerson)
                        break;
                    if (Integer.parseInt(searchPassenger.getString("now")) == i && Integer.parseInt(searchPassenger.getString("updown")) == 0) {
                        sumWeight += Integer.parseInt(searchPassenger.getString("weight"));
                        personMount++;
                        eachInfor.put(searchPassenger);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }//end for
            //将临时信息存储进返回每层数据中
            for (int j = 0; j < eachInfor.length(); j++) {
                try {
                    eachInforReturn.put(eachInfor.getJSONObject(j));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }//end for
            //将信息存储进总数据中
            elevator1.put(eachInforReturn);
        }//end for i
        //System.out.println(personData);
        map.put("elevator", elevator1);
        map.put("personData", personData);
        return map;
    }

    //单双层电梯上升情况
    public Map<String, JSONArray> upSingleAndDouble(JSONArray personData, int MaxPerson, int MaxWeight, int SingleOrDouble) {
        Map<String, JSONArray> map = new HashMap<String, JSONArray>();
        JSONArray elevator1 = new JSONArray();
        int sumWeight = 0;
        int personMount = 0;
        JSONArray eachInfor = new JSONArray();
        for (int i = 0; i <= 21; i++) {
            JSONArray eachInforReturn = new JSONArray();
            for (int j = 0; j < eachInfor.length(); j++) {
                try {
                    if (Integer.parseInt(eachInfor.getJSONObject(j).getString("correct")) <= i) {
                        sumWeight -= Integer.parseInt(eachInfor.getJSONObject(j).getString("weight"));
                        for (int k = 0; k < personData.length(); k++) {
                            if (personData.getJSONObject(k).getString("correct").equals(eachInfor.getJSONObject(j).getString("correct")) &&
                                    personData.getJSONObject(k).getString("updown").equals(eachInfor.getJSONObject(j).getString("updown")) &&
                                    personData.getJSONObject(k).getString("now").equals(eachInfor.getJSONObject(j).getString("now")) &&
                                    personData.getJSONObject(k).getString("weight").equals(eachInfor.getJSONObject(j).getString("weight"))
                            ) {
                                personData.remove(k);
                                k--;
                            }
                        }//end for
                        eachInfor.remove(j);
                        personMount--;
                        j--;
                    }//end if
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }//end for
            //System.out.printf(i+"\n");
            //SingleOrDouble判断单层还是双层电梯
            for (int j = 0; j < personData.length(); j++) {
                try {
                    JSONObject searchPassenger = personData.getJSONObject(j);
                    if (Integer.parseInt(searchPassenger.getString("now")) > i || sumWeight >= MaxWeight || personMount >= MaxPerson)
                        break;
                    if ((Integer.parseInt(searchPassenger.getString("now")) % 2 == SingleOrDouble
                            && Integer.parseInt(searchPassenger.getString("correct")) % 2 == SingleOrDouble)
                            && Integer.parseInt(searchPassenger.getString("now")) == i
                            && Integer.parseInt(searchPassenger.getString("updown")) == 1) {
                        sumWeight += Integer.parseInt(searchPassenger.getString("weight"));
                        personMount++;
                        eachInfor.put(searchPassenger);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }//end for
            for (int j = 0; j < eachInfor.length(); j++) {
                try {
                    eachInforReturn.put(eachInfor.getJSONObject(j));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }//end for
            elevator1.put(eachInforReturn);
        }//end for i
        map.put("elevator", elevator1);
        map.put("personData", personData);
        //System.out.println(personData);
        return map;
    }

    //单双层电梯下降情况
    public Map<String, JSONArray> downSingleAndDouble(JSONArray personData, int MaxPerson, int MaxWeight, int SingleOrDouble) {
        Map<String, JSONArray> map = new HashMap<String, JSONArray>();
        JSONArray elevator1 = new JSONArray();
        int start = 0;
        try {
            if (personData.length() > 0)
                start = Integer.parseInt(personData.getJSONObject(0).getString("now"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int sumWeight = 0;
        int personMount = 0;
        int Remainder = 2;
        JSONArray eachInfor = new JSONArray();
        for (int i = start; i >= 0; i--) {
            JSONArray eachInforReturn = new JSONArray();
            for (int j = 0; j < eachInfor.length(); j++) {
                try {
                    if (Integer.parseInt(eachInfor.getJSONObject(j).getString("correct")) >= i) {
                        sumWeight -= Integer.parseInt(eachInfor.getJSONObject(j).getString("weight"));
                        for (int k = 0; k < personData.length(); k++) {
                            if (personData.getJSONObject(k).getString("correct").equals(eachInfor.getJSONObject(j).getString("correct")) &&
                                    personData.getJSONObject(k).getString("updown").equals(eachInfor.getJSONObject(j).getString("updown"))) {
                                personData.remove(k);
                                k--;
                            }
                        }//end for
                        eachInfor.remove(j);
                        personMount--;
                        j--;
                    }//end if
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }//end for
            //System.out.printf(i+"\n");
            for (int j = 0; j < personData.length(); j++) {
                try {
                    JSONObject searchPassenger = personData.getJSONObject(j);
                    if (Integer.parseInt(searchPassenger.getString("now")) < i || sumWeight >= MaxWeight || personMount >= MaxPerson)
                        break;
                    if ((Integer.parseInt(searchPassenger.getString("now")) % 2 == SingleOrDouble
                            && Integer.parseInt(searchPassenger.getString("correct")) % 2 == SingleOrDouble)
                            && Integer.parseInt(searchPassenger.getString("now")) == i
                            && Integer.parseInt(searchPassenger.getString("updown")) == 0) {
                        sumWeight += Integer.parseInt(searchPassenger.getString("weight"));
                        personMount++;
                        eachInfor.put(searchPassenger);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }//end for
            for (int j = 0; j < eachInfor.length(); j++) {
                try {
                    eachInforReturn.put(eachInfor.getJSONObject(j));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            elevator1.put(eachInforReturn);
        }
        //System.out.println(personData);
        map.put("elevator", elevator1);
        map.put("personData", personData);
        return map;
    }
}
