package com.vkstech.AOPLogging.service;

import com.vkstech.AOPLogging.models.DemoEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoService {

    private static final List<DemoEntity> demoEntities = new ArrayList<>();

    public DemoEntity addDemoEntity(DemoEntity demoEntity) {
        demoEntities.add(demoEntity);
        return demoEntity;
    }

    public DemoEntity getDemoEntity(Integer id) {
        return demoEntities
                .stream()
                .filter(demoEntity -> demoEntity.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceAccessException("DemoEntity does not exist."));
    }
}
