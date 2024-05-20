package pro.anuj.skunkworks.cyral.jdbc.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class TokenLoader {
  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static Token getAccessToken() throws SQLException, IOException {
    final String cyralHome = System.getenv("HOME") + "/.cyral";
    if (!new File(cyralHome).exists()) {
      throw new SQLException("Cyral home directory not found");
    }
    final File[] files =
        Path.of(cyralHome).toFile().listFiles((dir, name) -> name.endsWith(".tokens"));
    if (files == null || files.length != 1) {
      throw new SQLException("No or multiple token files found in the Cyral home directory");
    }
    final Map<UUID, Token> tokens = objectMapper.readValue(files[0], new TypeReference<>() {});
    return tokens.values().stream()
        .filter(token -> token.getValidUntil().after(new Date()))
        .findFirst()
        .orElseThrow(() -> new SQLException("No valid token found"));
  }
}
