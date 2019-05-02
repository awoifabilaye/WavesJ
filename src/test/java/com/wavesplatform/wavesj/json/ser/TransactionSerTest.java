package com.wavesplatform.wavesj.json.ser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wavesplatform.wavesj.Transaction;
import com.wavesplatform.wavesj.json.WavesJsonMapper;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public abstract class TransactionSerTest {
    ObjectMapper mapper = new WavesJsonMapper((byte) 'T');

    protected <T extends Transaction> T serializationRoadtripTest(T tx, Class<T> txClass) throws IOException {
        StringWriter sw = new StringWriter();
        mapper.writeValue(sw, tx);
        T deserialized = mapper.readValue(sw.toString(), txClass);
        assertEquals(deserialized, tx);
        assertEquals(deserialized.getId(), tx.getId());
        assertEquals(0, deserialized.getHeight());
        return deserialized;
    }
}
