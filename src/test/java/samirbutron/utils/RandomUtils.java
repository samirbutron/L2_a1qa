package samirbutron.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtils {

  public static List<Integer> generateRandomRepeatingDigitList(int bound) {
    List<Integer> numbers = new ArrayList<>();
    Random random = new Random();
    String regex = "(\\d)\\1";
    while (numbers.size() < 10) {
      int number = random.nextInt(bound);
      String numberString = String.valueOf(number);

      if (numberString.matches(regex)) {
        numbers.add(number);
      }
    }
    return numbers;
  }
}
