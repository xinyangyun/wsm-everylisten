package com.example.wsmeverylisten.contorller;


import com.example.wsmeverylisten.entity.APIResponse;
import com.wujiuye.flow.FlowHelper;
import com.wujiuye.flow.FlowType;
import com.wujiuye.flow.common.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class QpsDemoController {


        private FlowHelper flowHelper = new FlowHelper(FlowType.Hour);

        @GetMapping("/test")
        public APIResponse testApi() {
                try{
                        long startTime = TimeUtil.currentTimeMillis();
                        // 业务逻辑
                        //    ......
                        // 计算耗时
                        long rt = TimeUtil.currentTimeMillis() - startTime;
                        flowHelper.incrSuccess(rt);
                        return new APIResponse();
                }catch (Exception e){
                        flowHelper.incrException();
                        return new APIResponse();
                }
        }

}
