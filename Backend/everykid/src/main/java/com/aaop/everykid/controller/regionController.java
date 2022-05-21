package com.aaop.everykid.controller;

import com.aaop.everykid.entity.Board;
import com.aaop.everykid.entity.regionCode;
import com.aaop.everykid.service.BoardService;
import com.aaop.everykid.service.regionCodeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/region")
public class regionController {
    @Autowired
    regionCodeService regionCodeService;

//    @RequestMapping(value="/find/{siDoName}/{siGunGuName}", produces="application/json;charset=UTF-8")
//    public String getCode(@PathVariable("siDoName") String siDoName, @PathVariable("siGunGuName") String siGunGuName) {
//        regionCode regionCode = regionCodeService.getRegionCode(siDoName, siGunGuName);
//
//        Gson gson = new GsonBuilder().create();
//        String jsonString = gson.toJson(regionCode);
//        System.out.println(jsonString);
//
//        return jsonString;
//    }

    @RequestMapping(value="/findAll", produces="application/json;charset=UTF-8")
    public String getAll() {
        List<regionCode> regionCodes = regionCodeService.getAll();

        Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(regionCodes);
        System.out.println(jsonString);

        return jsonString;
    }

    @RequestMapping(value="/getKey", produces="application/json;charset=UTF-8")
    public String getKey() {
        String key = "2f515461a4414e8aaec604237e74bc73";

        Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(key);
        System.out.println(jsonString);

        return jsonString;
    }
}
