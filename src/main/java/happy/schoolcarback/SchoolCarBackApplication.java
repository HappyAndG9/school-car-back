package happy.schoolcarback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("happy.schoolcarback.mapper")
public class SchoolCarBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolCarBackApplication.class, args);
    }

}
