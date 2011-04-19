package com.mulesoft.refcard;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;


public class UppercaseTransformer extends AbstractTransformer {

    @Override
    protected Object doTransform(Object o, String s) throws TransformerException {
        String payload = (String) o;
        return payload.toUpperCase();
    }
}
