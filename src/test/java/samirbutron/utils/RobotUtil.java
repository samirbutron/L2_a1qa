package samirbutron.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class RobotUtil {

  private static final Robot robot;

  static {
    try {
      robot = new Robot();
    } catch (AWTException e) {
      throw new RuntimeException(e);
    }
  }

  public static void robotType(String path) {
    for (char c : path.toCharArray()) {
      if (c == '\\') {
        robot.keyPress(KeyEvent.VK_BACK_SLASH);
        robot.keyRelease(KeyEvent.VK_ENTER);
      } else if (c == ':') {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_SEMICOLON);
        robot.keyRelease(KeyEvent.VK_SEMICOLON);
        robot.keyRelease(KeyEvent.VK_SHIFT);
      } else if (c == '_') {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_MINUS);
        robot.keyRelease(KeyEvent.VK_MINUS);
        robot.keyRelease(KeyEvent.VK_SHIFT);
      } else {
        int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
      }
    }
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
  }

  public static void delay(int ms) {
    robot.delay(ms);
  }
}
