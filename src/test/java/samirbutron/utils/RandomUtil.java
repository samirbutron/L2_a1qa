package samirbutron.utils;

import java.util.Random;

public class RandomUtil {

  private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String NUMERALS = "0123456789";
  private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
  private static final Random random = new Random();

  public static String generateString() {
    StringBuilder emailPrefixBuilder = new StringBuilder();

    int length = TestConfigUtil.generatedStringLength; // Random length between 15 and 29
    for (int i = 0; i < length; i++) {
      char randomChar = generateRandomChar();
      emailPrefixBuilder.append(randomChar);
    }
    return emailPrefixBuilder.toString();
  }

  private static char generateRandomChar() {
    String characters = CAPITAL_LETTERS + NUMERALS + LETTERS;
    return characters.charAt(random.nextInt(characters.length()));
  }
}
