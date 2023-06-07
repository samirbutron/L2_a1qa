package samirbutron.utils;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomUtils {
        private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static final String NUMERALS = "0123456789";
        private static final String CYRILLIC_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        private static final Random random = new Random();

        public static String generatePassword(String email) {
            StringBuilder passwordBuilder = new StringBuilder();
            // Add at least 1 capital letter
            char randomCapitalLetter = CAPITAL_LETTERS.charAt(random.nextInt(CAPITAL_LETTERS.length()));
            passwordBuilder.append(randomCapitalLetter);
            // Add at least 1 numeral
            char randomNumeral = NUMERALS.charAt(random.nextInt(NUMERALS.length()));
            passwordBuilder.append(randomNumeral);
            // Add at least 1 letter from the email
            char randomEmailLetter = email.charAt(random.nextInt(email.length()));
            passwordBuilder.append(randomEmailLetter);
            // Add at least 1 cyrillic character
            char randomCyrillicChar = CYRILLIC_CHARACTERS.charAt(random.nextInt(CYRILLIC_CHARACTERS.length()));
            passwordBuilder.append(randomCyrillicChar);
            // Add remaining characters to meet the length requirement
            int remainingLength = 10 - passwordBuilder.length();
            for (int i = 0; i < remainingLength; i++) {
                char randomChar = generateRandomChar();
                passwordBuilder.append(randomChar);
            }

            return passwordBuilder.toString();
        }

        public static String generateString() {
            StringBuilder emailPrefixBuilder = new StringBuilder();

            int length = random.nextInt(5) + 5; // Random length between 5 and 9
            for (int i = 0; i < length; i++) {
                char randomChar = generateRandomChar();
                emailPrefixBuilder.append(randomChar);
            }

            return emailPrefixBuilder.toString();
        }

        private static char generateRandomChar() {
            String characters = CAPITAL_LETTERS + NUMERALS + CYRILLIC_CHARACTERS;
            return characters.charAt(random.nextInt(characters.length()));
        }

        public static int generateRandomNumber(int bound) {
            return random.nextInt(bound);
        }
        public static Set<Integer> generateThreeRandomNumbers(int bound){
            Set<Integer> numberSet = new HashSet<>();

            while (numberSet.size() < 3){
                int randomNumber = random.nextInt(bound);
                numberSet.add(randomNumber);
            }
            return numberSet;
        }
}
