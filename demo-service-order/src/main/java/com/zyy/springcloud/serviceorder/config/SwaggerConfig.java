package com.zyy.springcloud.serviceorder.config;

import com.zyy.springcloud.api.model.enums.ResultCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .globalResponseMessage(RequestMethod.POST, getDefaultResponseMessages())
                .globalResponseMessage(RequestMethod.GET, getDefaultResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, getDefaultResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, getDefaultResponseMessages())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zyy.springcloud.serviceorder.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("订单中心API文档")
                .description("简单优雅的restful风格")
                .version("1.0")
                .build();
    }

    private List<ResponseMessage> getDefaultResponseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        for (ResultCode resultCode : ResultCode.values()) {
            responseMessages.add(new ResponseMessageBuilder()
                    .code(resultCode.getCode())
                    .message(resultCode.getDesc())
                    .responseModel(new ModelRef(""))
                    .build());
        }
        return responseMessages;
    }
}
