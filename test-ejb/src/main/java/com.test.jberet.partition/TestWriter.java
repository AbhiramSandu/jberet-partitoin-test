package com.test.jberet.partition;

import javax.batch.api.chunk.ItemWriter;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named
public class TestWriter implements ItemWriter {

    private final Logger LOG = Logger.getLogger(TestWriter.class.getName());

    @Override
    public void open(Serializable checkpoint) throws Exception {
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void writeItems(List<Object> items) throws Exception {
        items.forEach(i -> LOG.info("Writing " + i));
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
}
