package com.transpose.app.dto;

import com.transpose.app.model.ParsedArguments;
import com.transpose.app.service.TransposeService;
import com.transpose.app.utlis.ResourceUtils;
import org.json.JSONArray;

import java.io.IOException;

public class ParsedArgumentsDto {

    private final ParsedArguments parsedArguments;
    private NotesData notesData;

    public ParsedArgumentsDto(ParsedArguments parsedArguments) {
        this.parsedArguments = parsedArguments;
        notesData = new NotesData();
    }

    public void populateParsedArgumentsJsonObjects() throws IOException {
        notesData.setInputNotes(ResourceUtils.loadNotesFromParsedArguments(parsedArguments));
        notesData.setTransposedNotes(TransposeService.transposeNotesWithSemitones(this));
    }

    public ParsedArguments getParsedArguments() {
        return parsedArguments;
    }

    public NotesData getNotesData() {
        return notesData;
    }

    public class NotesData {
        private JSONArray inputNotes;
        private JSONArray transposedNotes;

        public NotesData() {
            inputNotes = new JSONArray();
            transposedNotes = new JSONArray();
        }

        public JSONArray getInputNotes() {
            return inputNotes;
        }

        public void setInputNotes(JSONArray inputNotes) {
            this.inputNotes = inputNotes;
        }

        public JSONArray getTransposedNotes() {
            return transposedNotes;
        }

        public void setTransposedNotes(JSONArray transposedNotes) {
            this.transposedNotes = transposedNotes;
        }

        @Override
        public String toString() {
            return transposedNotes.toString();
        }
    }

}
