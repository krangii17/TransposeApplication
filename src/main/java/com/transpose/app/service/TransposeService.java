package com.transpose.app.service;

import com.transpose.app.dto.ParsedArgumentsDto;
import org.json.JSONArray;

import static java.lang.Math.abs;

public class TransposeService {

    private static final int MIN_NOTES_NUMBER = 1;
    private static final int MAX_NOTES_NUMBER = 12;

    public static JSONArray transposeNotesWithSemitones(ParsedArgumentsDto dto) {
        JSONArray transposedNotes = new JSONArray();

        for (Object noteObj : dto.getNotesData().getInputNotes()) {
            JSONArray note = (JSONArray) noteObj;
            int newOctave = getOctaveNumber(note.getInt(0), note.getInt(1),
                    dto.getParsedArguments().getSemitones());
            int newNoteNumber = getNoteNumber(note.getInt(1),
                    dto.getParsedArguments().getSemitones());

            transposedNotes.put(new JSONArray().put(newOctave).put(newNoteNumber));
        }

        return transposedNotes;
    }

    private static int getOctaveNumber(int oldOctaveNumber, int oldNoteNumber, int semitones) {
        int calcResult = oldNoteNumber + semitones;
        if (calcResult > MAX_NOTES_NUMBER) {
            return oldOctaveNumber + calcResult / MAX_NOTES_NUMBER;
        } else if (calcResult < MIN_NOTES_NUMBER) {
            return oldOctaveNumber + -MIN_NOTES_NUMBER + (calcResult / MAX_NOTES_NUMBER);
        } else return oldOctaveNumber;
    }

    private static int getNoteNumber(int oldNoteNumber, int semitones) {
        int calcResult = oldNoteNumber + semitones;
        if (calcResult > MAX_NOTES_NUMBER) {
            return calcResult / MAX_NOTES_NUMBER;
        } else if (calcResult < MIN_NOTES_NUMBER) {
            return MAX_NOTES_NUMBER - abs((calcResult % MAX_NOTES_NUMBER));
        } else return calcResult;
    }

}
