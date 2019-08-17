package edu.xpu.buckmoo.repository.config;

import edu.xpu.buckmoo.dataobject.config.SystemConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SystemConfigRepositoryTest {
    @Autowired
    private SystemConfigRepository systemConfigRepository;
    @Test
    public void save(){
        SystemConfig systemConfig = new SystemConfig();
        systemConfig.setParamsId("other_param");
        systemConfig.setParamsValue(new BigDecimal(0.05f));
        systemConfig.setParamsDes("其他参数");

        SystemConfig save = systemConfigRepository.save(systemConfig);
        assertNotNull(save);
        log.info("[SystemConfigRepositoryTest] save={}", save);
    }
}
