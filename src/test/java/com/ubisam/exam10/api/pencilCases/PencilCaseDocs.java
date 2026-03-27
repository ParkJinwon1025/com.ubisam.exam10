package com.ubisam.exam10.api.pencilCases;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam10.domain.PencilCase;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class PencilCaseDocs extends MockMvcRestDocs {

    public PencilCase newEntity(String... entity) {
        PencilCase body = new PencilCase();
        body.setPencilCaseName(entity.length > 0 ? entity[0] : super.randomText("pencilCaseName"));
        body.setPencilCaseLocation(entity.length > 1 ? entity[1] : super.randomText("pencilCaseLocation"));

        return body;
    }

    public Map<String, Object> updateEntity(Map<String, Object> entity, String body) {
        entity.put("pencilCaseName", body);
        return entity;
    }

    public Map<String, Object> setSearchName(String search) {
        Map<String, Object> entity = new HashMap<>();
        entity.put("searchName", search);
        return entity;
    }

    public Map<String, Object> setSearchLocation(String search) {
        Map<String, Object> entity = new HashMap<>();
        entity.put("searchLocation", search);
        return entity;
    }

}
