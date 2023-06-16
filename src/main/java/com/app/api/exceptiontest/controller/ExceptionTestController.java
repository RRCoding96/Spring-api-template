package com.app.api.exceptiontest.controller;

import com.app.api.exceptiontest.dto.BindExceptionTestDto;
import com.app.api.exceptiontest.dto.TestEnum;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/exception")
public class ExceptionTestController {

    @GetMapping("/bind-exception-test")
    public String bindExceptionTest(@Valid BindExceptionTestDto bindExceptionTestDto) {
        return "ok";
    }

    @GetMapping("/type-exception-test")
    public String typeMismatchException(TestEnum testEnum) {
        return "ok";
    }

    @GetMapping("/business-exception-test")
    public String businessExceptionTest(String isError) {
        if("true".equals(isError)) {
            throw new BusinessException(ErrorCode.TEST);
        }
        return "ok";
    }

    @GetMapping("/exception-test")
    public String exceptionTest(String isError) {
        if("true".equals(isError)) {
            throw new IllegalArgumentException("예외 테스트");
        }

        return "ok";
    }

}
