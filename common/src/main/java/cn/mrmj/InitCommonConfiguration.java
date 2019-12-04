package cn.mrmj;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * create by: mrmj
 * description: common层初始化类
 * create time: 2019/12/4 18:00
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("cn.mrmj")
public class InitCommonConfiguration {

        private static final Logger logger = Logger.getLogger(InitCommonConfiguration.class);

        public InitCommonConfiguration() {
            logger.info("com.InitCommonConfiguration.<init>() init enservice");
        }


}
