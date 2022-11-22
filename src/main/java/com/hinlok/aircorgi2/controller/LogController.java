package com.hinlok.aircorgi2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogController {

    @RequestMapping("/updateLogs")
    public String updateLogs() {
        return "update";
    }

}
