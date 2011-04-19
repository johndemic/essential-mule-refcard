package com.mulesoft.refcard;


import org.mule.api.transformer.Transformer;
import org.mule.transformer.AbstractTransformerTestCase;

public class UppercaseTransformerTestCase extends AbstractTransformerTestCase {

    @Override
    public Transformer getTransformer() throws Exception {
        return new UppercaseTransformer();
    }

    @Override
    public Transformer getRoundTripTransformer() throws Exception {
        return null;
    }

    @Override
    public Object getTestData() {
        return "foo";
    }

    @Override
    public Object getResultData() {
        return "FOO";
    }
}
