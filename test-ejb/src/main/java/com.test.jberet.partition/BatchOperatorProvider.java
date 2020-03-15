package com.test.jberet.partition;

import javax.annotation.PostConstruct;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Singleton
@Startup
public class BatchOperatorProvider {

    private JobOperator jobOperator;

    @PostConstruct
    public void init() {
        jobOperator = BatchRuntime.getJobOperator();
    }

    @Produces
    @Dependent
    public JobOperator getOperator() {
        return jobOperator;
    }


}
