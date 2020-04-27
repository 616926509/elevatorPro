package com.elevator.elevator.controller;

import com.elevator.elevator.service.SolutionService;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class makeAnswer {
    //使SolutionService完成自动装配
    @Autowired
    SolutionService solutionService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    //前台发送请求makeAnswer
    @RequestMapping(value = "/makeAnswer", method = RequestMethod.POST)
    public String makeAnswer(HttpServletRequest request, HttpServletResponse response) {
        //接收前台数据
        String personData = request.getParameter("personData");
        JSONArray returnAnswerArray = new JSONArray();
        try {
            //得到计算结果
            returnAnswerArray = SolutionService.getSolution(new JSONArray(personData));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return returnAnswerArray.toString();
    }
}
