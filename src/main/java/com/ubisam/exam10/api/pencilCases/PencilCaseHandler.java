package com.ubisam.exam10.api.pencilCases;

import java.io.Serializable;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.ubisam.exam10.domain.PencilCase;

import io.u2ware.common.data.jpa.repository.query.JpaSpecificationBuilder;
import io.u2ware.common.data.rest.core.annotation.HandleAfterRead;
import io.u2ware.common.data.rest.core.annotation.HandleBeforeRead;

@Component
@RepositoryEventHandler
public class PencilCaseHandler {

    @HandleBeforeRead
    public void handleBeforeRead(PencilCase e, Specification<PencilCase> spec) throws Exception {

        JpaSpecificationBuilder<PencilCase> query = JpaSpecificationBuilder.of(PencilCase.class);
        query.where()
                .and().eq("pencilCaseName", e.getSearchName())
                .and().eq("pencilCaseLocation", e.getSearchLocation())
                .build(spec);
    }

    @HandleAfterRead
    public void afterRead(PencilCase e, Serializable r) throws Exception {

        System.out.println("[HandleAfterRead]" + e);
        System.out.println("[HandleAfterRead]" + r);

    }

    // Create, Delete, Save만 JPA가 지원해줌.
    @HandleBeforeCreate
    public void beforeCreate(PencilCase e) throws Exception {

        System.out.println("[HandleBeforeCreate]" + e);

    }

    @HandleAfterCreate
    public void afterCreate(PencilCase e) throws Exception {

        System.out.println("[HandleAfterCreate]" + e);

    }

    @HandleBeforeSave
    public void beforeSave(PencilCase e) throws Exception {

        System.out.println("[HandlebeforeSave]" + e);

    }

    @HandleAfterSave
    public void afterSave(PencilCase e) throws Exception {

        System.out.println("[HandleAfterSave]" + e);

    }

    @HandleBeforeDelete
    public void beforeDelete(PencilCase e) throws Exception {

        System.out.println("[HandlebeforeDelete]" + e);

    }

    @HandleAfterDelete
    public void afterDelete(PencilCase e) throws Exception {

        System.out.println("[HandleAfterDelete]" + e);

    }

}
