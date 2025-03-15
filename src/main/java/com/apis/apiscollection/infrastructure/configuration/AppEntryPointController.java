package com.apis.apiscollection.infrastructure.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppEntryPointController {

    @GetMapping("/api")
    public String redirectToSwagger() {
        return "redirect:/swagger-ui.html";
    }
}
