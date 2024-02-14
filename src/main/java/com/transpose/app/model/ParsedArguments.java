package com.transpose.app.model;

public class ParsedArguments {

    private final String inputFile;
    private final int semitones;

    public ParsedArguments(String[] args) {
        this.inputFile = args[0];
        this.semitones = Integer.parseInt(args[1]);
    }

    public String getInputFile() {
        return inputFile;
    }

    public int getSemitones() {
        return semitones;
    }

}
