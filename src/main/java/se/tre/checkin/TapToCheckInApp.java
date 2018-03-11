package se.tre.checkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"se.tre.checkin"})
public class TapToCheckInApp
{
    public static void main(String[] args) {
        SpringApplication.run(TapToCheckInApp.class, args);
    }
}
