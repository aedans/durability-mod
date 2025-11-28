package io.github.aedans;

import com.google.gson.JsonParser;
import dev.architectury.platform.Platform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public final class DurabilityMod {
  public static final String MOD_ID = "durability";
  public static final Logger LOGGER = Logger.getLogger(MOD_ID);
  public static int multiply = 1;
  public static int divide = 1;
  public static boolean loaded = false;

  public static void loadConfig() {
    if (loaded) {
      return;
    }


    Path path = Platform.getConfigFolder().resolve("durability.json");
    File file = path.toFile();
    if (!file.exists()) {
      try {
        Files.writeString(path, """
            {
              "multiply": 1,
              "divide": 1
            }
            """);
      } catch (IOException e) {
        LOGGER.severe("Error reading durability mod configuration: " + e.getMessage());
      }
    }

    try {
      var config = JsonParser.parseReader(new FileReader(file));
      multiply = config.getAsJsonObject().get("multiply").getAsInt();
      divide = config.getAsJsonObject().get("divide").getAsInt();
      loaded = true;
    } catch (FileNotFoundException e) {
      LOGGER.severe("Error reading durability mod configuration: " + e.getMessage());
    }
  }
}
