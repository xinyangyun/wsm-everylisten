package com.example.wsmeverylisten.contorller;

import com.example.wsmeverylisten.entity.APIResponse;
import com.example.wsmeverylisten.entity.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@RestController
@Slf4j
public class TestController {

    static class OOMObject {
    }

    //计数器，作为上传任务的ID
    private  static AtomicInteger atomicInteger = new AtomicInteger(0);

    @GetMapping("/hello")
    public void hello() {
        System.out.println("123");

//        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
//        List<String> jvmArguments = runtimeMXBean.getInputArguments();
//
//        System.out.println("JVM 参数:");
//        for (String argument : jvmArguments) {
//            System.out.println(argument);
//        }

        List<OOMObject> list = new ArrayList<>();

//        while(true) {
////            TimeUnit.MILLISECONDS.sleep(1);
//            list.add(new OOMObject());
//        }

    }


    @GetMapping("server")
    public APIResponse<OrderInfo> server(@RequestParam("userId") Long userId) {

        APIResponse<OrderInfo> response = new APIResponse<>();

        if (userId == null) {

            //对于userId为空的情况，收单服务直接处理失败，给予相应的错误码和错误提示

            response.setSuccess(false);

            response.setCode(3001);

            response.setMessage("Illegal userId");

        } else if (userId == 1) {

            //对于userId=1的用户，模拟订单服务对于风险用户的情况

            response.setSuccess(false);

            //把订单服务返回的错误码转换为收单服务错误码

            response.setCode(3002);

            response.setMessage("Internal Error, order is cancelled");

            //同时日志记录内部错误

            log.warn("用户 {} 调用订单服务失败，原因是 Risk order detected", userId);

        } else {

            //其他用户，下单成功

            response.setSuccess(true);

            response.setCode(2000);

            response.setMessage("OK");

            response.setData(new OrderInfo("Created", 2L));

        }

        return response;

    }

    public static void main(String[] args) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();


        IntStream.rangeClosed(1,10).forEach(x->{
            int i = atomicInteger.incrementAndGet();
            System.out.println("xx"+i);
        });


    }

}
