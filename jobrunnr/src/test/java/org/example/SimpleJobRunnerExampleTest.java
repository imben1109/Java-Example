package org.example;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.jobs.JobId;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SimpleJobRunnerExampleTest {

    @Test
    public void canEnqueueingAndProcessing(){
        JobScheduler jobScheduler = JobRunr.configure()
                .useStorageProvider(new InMemoryStorageProvider())
                .useBackgroundJobServer()
                .useDashboard()
                .initialize()
                .getJobScheduler();

        JobId jobId = BackgroundJob.enqueue(() -> {
            System.out.println("Hello World");
        });
        System.out.print(jobId.asUUID());
        jobScheduler.shutdown();
    }
}
