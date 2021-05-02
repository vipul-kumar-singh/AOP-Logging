package com.vkstech.AOPLogging.service;

import com.vkstech.AOPLogging.dto.DemoContext;
import com.vkstech.AOPLogging.models.DemoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DemoService {

    @Resource(name = "demoContext")
    DemoContext demoContext;

    private static final List<DemoEntity> demoEntities = new ArrayList<>();

    public DemoEntity addDemoEntity(DemoEntity demoEntity) {
        demoEntities.add(demoEntity);
        return demoEntity;
    }

    public DemoEntity getDemoEntity(Integer id) {
        log.info("{}", demoContext.getName());
        return demoEntities
                .stream()
                .filter(demoEntity -> demoEntity.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceAccessException("DemoEntity does not exist."));
    }

    public List<DemoEntity> getDemoEntityList() {
        return demoEntities;
    }
}
