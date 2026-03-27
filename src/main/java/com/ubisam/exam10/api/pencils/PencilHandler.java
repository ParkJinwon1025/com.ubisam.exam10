package com.ubisam.exam10.api.pencils;

import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.ubisam.exam10.domain.Pencil;

@Component
@RepositoryEventHandler
public class PencilHandler {

    @HandleBeforeCreate
    public void HandleBeforeCreate(Pencil p) throws Exception {

        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleBeforeCreate] " + p);

    }

    @HandleAfterCreate
    public void afterCreate(Pencil p) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleAfterCreate]" + p);

    }

    @HandleBeforeSave
    public void beforeSave(Pencil p) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandlebeforeSave]" + p);

    }

    @HandleAfterSave
    public void afterSave(Pencil p) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleAfterSave]" + p);

    }

    @HandleBeforeDelete
    public void beforeDelete(Pencil p) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandlebeforeDelete]" + p);

    }

    @HandleAfterDelete
    public void afterDelete(Pencil p) throws Exception {
        // 로그 코드 작성 (Auditing)
        // 테스트에는 sysout으로 작성하나 실제는 log 사용
        System.out.println("[HandleAfterSave]" + p);

    }

}
