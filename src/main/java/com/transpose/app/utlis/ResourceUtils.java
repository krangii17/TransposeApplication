package com.transpose.app.utlis;

import com.transpose.app.dto.ParsedArgumentsDto;
import com.transpose.app.model.ParsedArguments;
import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceUtils {

    protected static final String RELATIVE_PATH = "src/main/resources/output.json";

    public static JSONArray loadNotesFromParsedArguments(ParsedArguments parsedArguments) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(parsedArguments.getInputFile())));
        return new JSONArray(content);
    }

    public static void saveNotesFromParsedArgumentsToJson(ParsedArgumentsDto dto) throws IOException {
        String jsonString = dto.getNotesData().toString();
        Files.write(Paths.get(RELATIVE_PATH), jsonString.getBytes());
    }
}
