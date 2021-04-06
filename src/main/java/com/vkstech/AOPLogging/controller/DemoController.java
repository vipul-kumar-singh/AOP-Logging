package com.vkstech.AOPLogging.controller;

import com.vkstech.AOPLogging.dto.ResponseObject;
import com.vkstech.AOPLogging.models.DemoEntity;
import com.vkstech.AOPLogging.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseObject> addData(@RequestBody DemoEntity demoEntity){
        demoEntity = demoService.addDemoEntity(demoEntity);
        return ResponseEntity.ok(new ResponseObject("DemoEntity added successfully", demoEntity));
    }

    @GetMapping("view/{id}")
    public ResponseEntity<ResponseObject> viewData(@PathVariable Integer id){
        DemoEntity demoEntity = demoService.getDemoEntity(id);
        return ResponseEntity.ok(new ResponseObject("DemoEntity fetched successfully", demoEntity));
    }

}
