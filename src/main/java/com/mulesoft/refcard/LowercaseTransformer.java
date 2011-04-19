package com.mulesoft.refcard;

import org.mule.api.annotations.ContainsTransformerMethods;
import org.mule.api.annotations.Transformer;

@ContainsTransformerMethods
public class LowercaseTransformer {

    @Transformer
    public String toLowercase(String string) {
        return string.toLowerCase();
    }
}
