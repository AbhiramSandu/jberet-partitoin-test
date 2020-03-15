package com.test.war;

import com.test.jberet.partition.TestConstants;

import javax.batch.operations.JobOperator;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.Properties;

@Path("/")
@ApplicationScoped
public class TestResource {

    @Inject
    private JobOperator jobOperator;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public long startTestBatch(@FormParam("start") int start, @FormParam("end") int end, @FormParam("part-length") int partLength) {
        Properties properties = new Properties();
        properties.put(TestConstants.ARG_START, start);
        properties.put(TestConstants.ARG_END, end);
        properties.put(TestConstants.ARG_PART_LENGTH, partLength);
        long executionId = jobOperator.start("testBatch", properties);
        return executionId;
    }
}
