package com.ubisam.exam10.api.pencilCases;

import static io.u2ware.common.docs.MockMvcRestDocs.is2xx;
import static io.u2ware.common.docs.MockMvcRestDocs.post;
import static io.u2ware.common.docs.MockMvcRestDocs.put;
import static io.u2ware.common.docs.MockMvcRestDocs.print;
import static io.u2ware.common.docs.MockMvcRestDocs.delete;
import static io.u2ware.common.docs.MockMvcRestDocs.result;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.web.servlet.MockMvc;

import com.ubisam.exam10.domain.Pencil;
import com.ubisam.exam10.domain.PencilCase;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class PencilCaseTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PencilCaseDocs docs;

    @Autowired
    private PencilCaseRepository pencilCaseRepository;

    // CRUD - C
    @Test
    public void contextLoads() throws Exception {

        // Create
        mvc.perform(post("/api/pencilCases").content(docs::newEntity, "필통", "위치")).andExpect(is2xx())
                .andDo(result(docs::context, "entity1"));

        // Read
        String url = docs.context("entity1", "$._links.self.href");
        mvc.perform(post(url)).andExpect(is2xx());

        // Update
        Map<String, Object> body = docs.context("entity1", "$");
        mvc.perform(put(url).content(docs::updateEntity, body, "필통1234")).andExpect(is2xx());

        // Delete
        mvc.perform(delete(url)).andExpect(is2xx());

    }

    // Handler
    @Test
    public void contextLoads2() throws Exception {

        List<PencilCase> result;
        boolean hasResult;

        List<PencilCase> pencilCaseList = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            pencilCaseList.add(docs.newEntity("필통" + i, "위치" + i));
        }
        pencilCaseRepository.saveAll(pencilCaseList);

        // 1) 이름을 통한 필통 찾기
        JpaSpecificationBuilder<PencilCase> query = JpaSpecificationBuilder.of(PencilCase.class);
        query.where().and().eq("pencilCaseName", "필통30");
        result = pencilCaseRepository.findAll(query.build());
        hasResult = result.stream().anyMatch(u -> "필통30".equals(u.getPencilCaseName()));
        assertEquals(true, hasResult);

        // 2) 위치를 통한 필통 찾기
        JpaSpecificationBuilder<PencilCase> query1 = JpaSpecificationBuilder.of(PencilCase.class);
        query1.where().and().eq("pencilCaseLocation", "위치29");
        result = pencilCaseRepository.findAll(query1.build());
        hasResult = result.stream().anyMatch(u -> "위치29".equals(u.getPencilCaseLocation()));
        assertEquals(true, hasResult);
    }

    // Search
    @Test
    public void contextLoads3() throws Exception {

        List<PencilCase> pencilCaseList = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            pencilCaseList.add(docs.newEntity("필통" + i, "위치" + i));
        }
        pencilCaseRepository.saveAll(pencilCaseList);

        // 1. 이름으로 검색
        mvc.perform(post("/api/pencilCases/search").content(docs::setSearchName, "필통3")).andDo(print())
                .andExpect(is2xx());

        // 2. 위치로 검색
        mvc.perform(post("/api/pencilCases/search").content(docs::setSearchLocation, "위치2")).andDo(print())
                .andExpect(is2xx());

        // 3. 페이지네이션 : 5개씩 8페이지
        mvc.perform(post("/api/pencilCases/search").content(docs::setSearchName, "").param("size", "5")).andDo(print())
                .andExpect(is2xx());

        // 4. 정렬
        mvc.perform(post("/api/pencilCases/search").content(docs::setSearchLocation, "").param("sort",
                "pencilCaseLocation,desc")).andDo(print())
                .andExpect(is2xx());

    }

}
