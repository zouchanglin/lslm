package edu.xpu.buckmoo.controller.admin;


import edu.xpu.buckmoo.dataobject.config.SystemConfig;
import edu.xpu.buckmoo.repository.config.SystemConfigRepository;
import edu.xpu.buckmoo.utils.JsonUtil;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/admin/system")
public class SystemConfigController {
    @Autowired
    private SystemConfigRepository systemConfigRepository;

    @GetMapping("/show")
    public String show(HttpSession httpSession){
        if(SessionOpen.openSession) {
            String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
            if (BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin"))
                return JsonUtil.toJson(ResultVOUtil.error(1, "登录信息已经过期"));
        }
        return JsonUtil.toJson(ResultVOUtil.success(systemConfigRepository.findAll()));
    }

    @PostMapping("/update")
    public String update(@RequestParam("configId") String configId,
                         @RequestParam("configValue") String configValue,
                         HttpSession httpSession){
        if(SessionOpen.openSession) {
            String BAIDU_ID_UX = (String) httpSession.getAttribute("BAIDU_ID_UX");
            if (BAIDU_ID_UX == null || !BAIDU_ID_UX.equals("Admin"))
                return JsonUtil.toJson(ResultVOUtil.error(1, "登录信息已经过期"));
        }

        Optional<SystemConfig> findConfigOpt = systemConfigRepository.findById(configId);
        if(findConfigOpt.isPresent()){
            BigDecimal bigDecimal = new BigDecimal(configValue);
            SystemConfig systemConfig = new SystemConfig();
            systemConfig.setParamsId(configId);
            systemConfig.setParamsValue(bigDecimal);
            systemConfig.setParamsDes(findConfigOpt.get().getParamsDes());

            SystemConfig save = systemConfigRepository.save(systemConfig);

            return JsonUtil.toJson(ResultVOUtil.success(save));
        }else{
            return JsonUtil.toJson(ResultVOUtil.error(1, "无此配置项"));
        }
    }
}
