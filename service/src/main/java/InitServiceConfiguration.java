import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("cn.mrmj.mapper")
public class InitServiceConfiguration {

        private static final Logger logger = Logger.getLogger(InitServiceConfiguration.class);

        public InitServiceConfiguration() {
            logger.info("com.InitConfiguration.<init>() init enservice");
        }
}
