package com.miaoqi.apollo.miaoqispringbootapollo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FieldController extends AbstractController {

    @GetMapping("/fieldController")
    public String testField() {
        return this.testField;
    }

}
