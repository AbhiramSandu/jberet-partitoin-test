package com.test.jberet.partition;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

@Named
public class TestProcessor implements ItemProcessor {
    @Override
    public Object processItem(Object item) throws Exception {
        Integer intValue = (Integer) item;

        Thread.sleep((long) (Math.random() * 10000L));
        return intValue * 3;
    }
}
