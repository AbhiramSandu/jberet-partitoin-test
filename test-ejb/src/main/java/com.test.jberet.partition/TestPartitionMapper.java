package com.test.jberet.partition;

import javax.batch.api.partition.PartitionMapper;
import javax.batch.api.partition.PartitionPlan;
import javax.batch.api.partition.PartitionPlanImpl;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Properties;

@Named
public class TestPartitionMapper implements PartitionMapper {

    @Inject
    private JobContext jobContext;

    @Override
    public PartitionPlan mapPartitions() throws Exception {
        Properties parameters = BatchRuntime.getJobOperator().getParameters(jobContext.getExecutionId());
        int start = (int) parameters.get(TestConstants.ARG_START);
        int end = (int) parameters.get(TestConstants.ARG_END);
        int partLength = (int) parameters.get(TestConstants.ARG_PART_LENGTH);

        int partCount = (end - start) / partLength;
        Properties[] partitionProperties = new Properties[partCount];
        for (int i = 0; i < partCount; i++) {
            int partStart = start + i * partLength;
            int partEnd = partStart + partLength;
            if (i == partCount - 1) {
                partEnd = end;
            }
            Properties partProperties = new Properties();
            partProperties.setProperty(TestConstants.PARAM_PARTITION_START, "" + partStart);
            partProperties.setProperty(TestConstants.PARAM_PARTITION_END, "" + partEnd);
            partitionProperties[i] = partProperties;
        }
        PartitionPlanImpl partitionPlan = new PartitionPlanImpl();
        partitionPlan.setPartitions(partCount);
        partitionPlan.setPartitionProperties(partitionProperties);

        return partitionPlan;
    }
}
