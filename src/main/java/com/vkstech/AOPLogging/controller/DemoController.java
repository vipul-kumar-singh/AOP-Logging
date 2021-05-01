package com.vkstech.AOPLogging.controller;

import com.vkstech.AOPLogging.dto.ResponseObject;
import com.vkstech.AOPLogging.models.DemoEntity;
import com.vkstech.AOPLogging.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("demo")
public class DemoController {

    private final DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @PostMapping("add")
    public ResponseObject addData(@RequestBody DemoEntity demoEntity) {
        demoEntity = demoService.addDemoEntity(demoEntity);
        return new ResponseObject("DemoEntity added successfully", demoEntity);
    }

    @GetMapping("view/{id}")
    public ResponseObject viewData(@PathVariable Integer id) {
        DemoEntity demoEntity = demoService.getDemoEntity(id);
        return new ResponseObject("DemoEntity fetched successfully", demoEntity);
    }

}
