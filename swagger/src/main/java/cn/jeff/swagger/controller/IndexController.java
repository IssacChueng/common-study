package cn.jeff.swagger.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @ApiOperation(value = "indexs")
    @ApiImplicitParam(dataTypeClass = String.class, name = "data", paramType = "query", value = "dd")
    @GetMapping("/indexs")
    public String index(@RequestParam(name = "data") String data) {
        return "Hello World";
    }
}
