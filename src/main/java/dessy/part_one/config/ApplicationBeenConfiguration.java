package dessy.part_one.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

@Configuration
public class ApplicationBeenConfiguration {

    @Bean
    public ModelMapper modelMapper(){
            return new ModelMapper();
}

}
