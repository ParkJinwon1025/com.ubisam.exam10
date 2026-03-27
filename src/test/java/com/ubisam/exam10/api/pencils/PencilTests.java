package com.ubisam.exam10.api.pencils;

import static io.u2ware.common.docs.MockMvcRestDocs.is2xx;
import static io.u2ware.common.docs.MockMvcRestDocs.post;
import static io.u2ware.common.docs.MockMvcRestDocs.get;
import static io.u2ware.common.docs.MockMvcRestDocs.put;
import static io.u2ware.common.docs.MockMvcRestDocs.delete;
import static io.u2ware.common.docs.MockMvcRestDocs.print;
import static io.u2ware.common.docs.MockMvcRestDocs.result;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.web.servlet.MockMvc;

import com.ubisam.exam10.domain.Pencil;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class PencilTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PencilDocs docs;

    @Autowired
    private PencilRepository pencilRepository;

    // CRUD
    @Test
    public void contextLoads() throws Exception {

        // CRUD - C
        mvc.perform(post("/api/pencils").content(docs::newEntity, "이름", "색연필")).andDo(print()).andExpect(is2xx())
                .andDo(result(docs::context, "entity"));

        // CRUD - R
        String url = docs.context("entity", "$._links.self.href");
        mvc.perform(get(url)).andExpect(is2xx());

        // CRUD - U
        Map<String, Object> body = docs.context("entity", "$");
        mvc.perform(put(url).content(docs::updateEntity, body, "이름1234")).andExpect(is2xx());

        // CRUD - D
        mvc.perform(delete(url)).andExpect(is2xx());

    }

    // Handler
    @Test
    public void contextLoads2() throws Exception {

        Specification<Pencil> spec;
        List<Pencil> result;
        boolean hasResult;

        List<Pencil> pencilList = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            pencilList.add(docs.newEntity("연필" + i, "연필타입" + i, "색깔" + i));
        }
        pencilRepository.saveAll(pencilList);

        // 1. 이름을 통한 연필 찾기
        JpaSpecificationBuilder<Pencil> query = JpaSpecificationBuilder.of(Pencil.class);
        spec = query.where().and().eq("pencilName", "연필26").build();
        result = pencilRepository.findAll(spec);
        hasResult = result.stream().anyMatch(p -> "연필26".equals(p.getPencilName()));
        assertEquals(true, hasResult);

        // 2. 타입을 통한 연필 찾기
        JpaSpecificationBuilder<Pencil> query1 = JpaSpecificationBuilder.of(Pencil.class);
        spec = query1.where().and().eq("pencilType", "연필타입11").build();
        result = pencilRepository.findAll(spec);
        hasResult = result.stream().anyMatch(p -> "연필타입11".equals(p.getPencilType()));
        assertEquals(true, hasResult);

        // 3. 색깔을 통한 연필 찾기
        JpaSpecificationBuilder<Pencil> query2 = JpaSpecificationBuilder.of(Pencil.class);
        spec = query2.where().and().eq("pencilColor", "색깔3").build();
        result = pencilRepository.findAll(spec);
        hasResult = result.stream().anyMatch(p -> "색깔3".equals(p.getPencilColor()));
        assertEquals(true, hasResult);

    }

    // Search
    @Test
    public void contextLoads3() throws Exception {

        List<Pencil> pencilList = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            pencilList.add(docs.newEntity("연필" + i, "연필타입" + i, "색깔" + i));
        }
        pencilRepository.saveAll(pencilList);

        // 1) 이름으로 검색
        mvc.perform(get("/api/pencils/search/findByPencilName").param("pencilName", "연필5")).andExpect(is2xx());

        // 2) 타입으로 검색
        mvc.perform(get("/api/pencils/search/findByPencilType").param("pencilType", "연필타입15")).andExpect(is2xx());

        // 3) 색깔로 검색
        mvc.perform(get("/api/pencils/search/findByPencilColor").param("pencilType", "색깔33")).andExpect(is2xx());

        // 4) 페이지네이션 - 10개씩 4페이지
        mvc.perform(get("/api/pencils").param("size", "10")).andExpect(is2xx()).andDo(print());

        // 5) 정렬
        mvc.perform(get("/api/pencils").param("sort", "pencilType,desc")).andDo(print()).andExpect(is2xx());

    }
}
