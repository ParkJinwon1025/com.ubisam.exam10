package com.ubisam.exam10.api.pencils;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.ubisam.exam10.domain.Pencil;

import io.u2ware.common.docs.MockMvcRestDocs;

@Component
public class PencilDocs extends MockMvcRestDocs {

    public Pencil newEntity(String... entity) {

        Pencil body = new Pencil();
        body.setPencilName(entity.length > 0 ? entity[0] : super.randomText("pencilName"));
        body.setPencilType(entity.length > 1 ? entity[1] : super.randomText("pencilType"));
        body.setPencilColor(entity.length > 2 ? entity[2] : super.randomText("pencilColor"));
        return body;
    }

    public Map<String, Object> updateEntity(Map<String, Object> body, String name) {
        body.put("pencilName", name);
        return body;
    }

}
