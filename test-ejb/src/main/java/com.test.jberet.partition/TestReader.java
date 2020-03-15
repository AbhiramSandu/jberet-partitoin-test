package com.test.jberet.partition;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.ItemReader;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;

@Named
public class TestReader implements ItemReader {

    @Inject
    @BatchProperty(name = TestConstants.PARAM_PARTITION_START)
    private String partitionStartString;

    @Inject
    @BatchProperty(name = TestConstants.PARAM_PARTITION_END)
    private String partitionEndString;

    int curIndex;

    public void open(Serializable checkpoint) throws Exception {
        TestState checkpointState = (TestState) checkpoint;
        Optional.ofNullable(checkpointState)
                .ifPresentOrElse(this::initFromCheckpoint, this::initFromParams);
    }

    public void close() throws Exception {

    }

    public Object readItem() throws Exception {
        int max = Integer.parseInt(partitionEndString);
        this.curIndex++;
        if (this.curIndex < max) {
            return this.curIndex;
        } else {
            return null;
        }
    }

    public Serializable checkpointInfo() throws Exception {
        TestState testState = new TestState();
        testState.setCurIndex(this.curIndex);
        return testState;
    }


    private void initFromParams() {
        this.curIndex = Integer.parseInt(partitionStartString);
    }

    private void initFromCheckpoint(TestState testState) {
        this.curIndex = testState.getCurIndex();
    }

}
