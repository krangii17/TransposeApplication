package com.transpose.app.utlis;

import com.transpose.app.exception.NotValidRangeException;
import org.json.JSONArray;

public class TransposeUtils {

    private static final int MIN_NOTES_NUMBER = 1;
    private static final int MAX_NOTES_NUMBER = 12;
    private static final int NUMBER_OF_ARGS = 2;
    private static final int FIRST_OCTAVE_NOTE = -3;
    private static final int LAST_OCTAVE_NOTE = 5;
    private static final int TENTH_NOTE = 10;

    private static final String ILLEGAL_ARGUMENT_ERROR_MESSAGE = "Usage: java TransposeApplication <input_file> <semitones> <output_file>";
    private static final String OUT_OF_RANGE_TRANSPOSITION = "Error: Transposition are out of range";

    public static void validateArgumentsSize(String[] args) {
        if (args.length != NUMBER_OF_ARGS) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_ERROR_MESSAGE);
        }
    }

    public static void validateRange(JSONArray notes) {
        for (Object noteObj : notes) {
            JSONArray note = (JSONArray) noteObj;
            int octave = note.getInt(0);
            int noteNumber = note.getInt(1);
            if (isOctaveNoteIsOutOfRange(octave, noteNumber)) {
                throw new NotValidRangeException(OUT_OF_RANGE_TRANSPOSITION);
            }
        }
    }

    private static boolean isOctaveNoteIsOutOfRange(int octave, int noteNumber) {
        return (octave < FIRST_OCTAVE_NOTE || octave > LAST_OCTAVE_NOTE ||
                    noteNumber < MIN_NOTES_NUMBER || noteNumber > MAX_NOTES_NUMBER ||
                    (octave == FIRST_OCTAVE_NOTE && noteNumber < TENTH_NOTE) ||
                    (octave == LAST_OCTAVE_NOTE && noteNumber > MIN_NOTES_NUMBER));
    }

}
