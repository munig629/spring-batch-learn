package com.example.demo;

import java.util.Collection;
import java.util.Properties;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.converter.DefaultJobParametersConverter;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import lombok.RequiredArgsConstructor;
 
@Component
@EnableBatchProcessing
@RequiredArgsConstructor
@ConditionalOnProperty(value = { "batch" }, havingValue = "my-batch")   // CommandLineRunnerのユニットテストを実行する際はコメントアウト
public class BatchJobLauncher implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    
    private final Collection<Job> jobs;
    
    private DefaultJobParametersConverter converter = new DefaultJobParametersConverter();

    @Override
    public void run(String... args) throws Exception {
        System.out.println("run my-batch");
        Properties properties = StringUtils.splitArrayElementsIntoProperties(args, "=");
        JobParameters jobParameters = this.converter.getJobParameters(properties);
        
        // パラメータから実行するジョブ名を取得
        System.out.println("jobParameters:" + jobParameters.toString());
        // String jobName = "sample02Job";
        String jobName = jobParameters.getString("jobName");
        
        try {
            // ジョブ実行
            for (Job job : jobs) {
                if (StringUtils.hasText(jobName)) {
                    if (!jobName.equals(job.getName())) {
                        // 実行対象のジョブでない場合はスキップ
                        continue;
    				}
                }
                JobExecution jobExecution = jobLauncher.run(job, jobParameters);    
                // 終了ステータス
                System.out.println("my-batch status: " + jobExecution.getExitStatus().getExitCode());
            }
        } catch (Exception ex) {
            System.out.println("my-batch status: FAILED");
            System.out.println(ex.toString());
            System.exit(10);
        }
    }
}
