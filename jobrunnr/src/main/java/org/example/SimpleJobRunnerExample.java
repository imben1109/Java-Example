package org.example;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.jobs.JobId;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.storage.InMemoryStorageProvider;

public class SimpleJobRunnerExample {
    private static void doSoManyThing(){
        System.out.println("Hello World");
        System.out.println("Hello World");
        System.out.println("Hello World");
        System.out.println("Hello World");

    }

    public static void main(String[] args) {
        System.out.println("Number of Active Thread: " + Thread.activeCount());
        JobScheduler jobScheduler = JobRunr.configure()
                .useStorageProvider(new InMemoryStorageProvider())
                .useBackgroundJobServer()
                .useDashboard()
                .initialize()
                .getJobScheduler();

        JobId jobId = BackgroundJob.enqueue(() -> {
            doSoManyThing();
        });

        System.out.println(jobId.asUUID());
        jobScheduler.shutdown();
    }
}