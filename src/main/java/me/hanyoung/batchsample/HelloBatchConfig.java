package me.hanyoung.batchsample;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HelloBatchConfig {

    JobBuilderFactory jobBuilderFactory;
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("HelloJob")
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("HelloStep")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("=================== Tasklet called....");

                    for (int i = 0; i < 100; i++) {
                        log.info("Hello Step Log {}", i);
                    }

                    return RepeatStatus.FINISHED;
                }).build();
    }

}
