package dingshuangwu.graduation.graduationo2o;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author dingshuangwu
 * @date 2019-12-24
 */
@SpringBootApplication
@MapperScan(basePackages = {"dingshuangwu.graduation.graduationo2o.mapper"})
@EnableTransactionManagement

public class GraduationO2oApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationO2oApplication.class, args);
    }

}
