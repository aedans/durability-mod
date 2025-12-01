package io.github.aedans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
  public static DurabilityConfig config;

  public static DurabilityConfig getConfig() {
    if (config != null) {
      return config;
    } else {
      config = new DurabilityConfig(new DurabilityConfig.Modifier(1, 1), new DurabilityConfig.Modifier(1, 1));
    }

    Path path = Platform.getConfigFolder().resolve("durability.json");
    File file = path.toFile();
    if (!file.exists()) {
      try {
        var string = new GsonBuilder().setPrettyPrinting().create().toJson(config);
        Files.writeString(path, string);
      } catch (IOException e) {
        LOGGER.severe("Error reading durability mod configuration: " + e.getMessage());
      }
    }

    try {
      config = new Gson().fromJson(new FileReader(file), DurabilityConfig.class);
    } catch (FileNotFoundException e) {
      LOGGER.severe("Error reading durability mod configuration: " + e.getMessage());
    }

    return config;
  }
}
