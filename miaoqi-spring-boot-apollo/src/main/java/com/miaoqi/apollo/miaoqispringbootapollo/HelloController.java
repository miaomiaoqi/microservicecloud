package com.miaoqi.apollo.miaoqispringbootapollo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController extends AbstractController {

    private static Logger logger = (Logger) LoggerFactory.getLogger(HelloController.class);

    @Value("${server.port}")
    String port;

    // @Value("${childField:childField}")
    public String testField;

    @Value("#{'${list:1}'.split(',')}")
    public List<String> list;

    // @Value("${x.map:'{}'}")
    // public Map map;

    @GetMapping("/hi")
    public String hi() {

        logger.debug("debug log...");
        logger.info("info log...");
        logger.warn("warn log...");

        return "hi, i am from port: " + this.port;
    }

    @GetMapping("/testfield")
    public String testField() {
        System.out.println("list: " + this.list);
        System.out.println(this.list.get(0));
        // System.out.println("map: " + this.map);
        return this.testField;
    }

    // @Value("${x.list:2,3,4}")
    // public void setList(List<String> list) {
    //     System.out.println("asf,pamf");
    //     this.list = list;
    // }

    // @Value("#{'${list}'.split(',')}")
    // private List<String> list;
    //
    // @Value("#{${maps}}")
    // private Map<String,String> maps;
    //
    // @Value("#{${redirectUrl}}")
    // private Map<String,String> redirectUrl;
    //
    // list: topic1,topic2,topic3
    // maps: "{key1: 'value1', key2: 'value2'}"
    // redirectUrl: "{sso_client_id: '${id}',sso_client_secret: '${secret}',redirect_url: '${client.main.url.default}'}"

}