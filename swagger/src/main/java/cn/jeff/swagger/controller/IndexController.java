package cn.jeff.swagger.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class IndexController {

    @ApiOperation(value = "indexs")
    @ApiImplicitParam(dataTypeClass = String.class, name = "data", paramType = "query", value = "dd")
    @GetMapping("/indexs")
    public String index(@RequestParam(name = "data") String data) {
        return "Hello World";
    }

    @PostMapping(value = "/callBack", produces = {MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8"})
    @ApiOperation(value = "indexs")
    @ResponseBody
    public String callBack(@RequestBody Map<String, String> data) {
        System.out.println(data);
        return "{}";
    }
}
