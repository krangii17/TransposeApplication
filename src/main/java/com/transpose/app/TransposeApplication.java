package com.transpose.app;

import com.transpose.app.dto.ParsedArgumentsDto;
import com.transpose.app.exception.NotValidRangeException;
import com.transpose.app.model.ParsedArguments;
import com.transpose.app.utlis.ResourceUtils;
import com.transpose.app.utlis.TransposeUtils;

import java.io.IOException;

public class TransposeApplication {

    private final static String WRITE_READ_ERROR_MESSAGE = "Can't read or write to destination file: ";

    public static void main(String[] args) {
        try {
            TransposeUtils.validateArgumentsSize(args);
            ParsedArgumentsDto dto = new ParsedArgumentsDto(new ParsedArguments(args));
            dto.populateParsedArgumentsJsonObjects();
            TransposeUtils.validateRange(dto.getNotesData().getTransposedNotes());
            ResourceUtils.saveNotesFromParsedArgumentsToJson(dto);
        } catch (IOException e) {
            System.out.println(WRITE_READ_ERROR_MESSAGE + e.getMessage());
        } catch (NotValidRangeException | IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
