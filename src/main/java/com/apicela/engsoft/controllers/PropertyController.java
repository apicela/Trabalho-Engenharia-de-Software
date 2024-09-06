package com.apicela.engsoft.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@Tag(name = "1. Property ", description = "CRUD")
@RequestMapping("/property")
@Log4j2
@CrossOrigin("*")
public class PropertyController {
}
