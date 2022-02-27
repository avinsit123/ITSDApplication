package com.io.request.services;

public class SampleClass {

    private static final SampleClass sampleClass = new SampleClass();

    private SampleClass() {

    }

    public static SampleClass giveMeSampleClassInstance() {
        return sampleClass;
    }
}
