package samirbutron.utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class TestConfigUtil {

  private static final ISettingsFile testConfig = new JsonSettingsFile("testconfig.json");
  private static final ISettingsFile config = new JsonSettingsFile("config.json");
  public static final String url = config.getValue("/url").toString();
  public static final String postsEndPoint = config.getValue("/postsEndPoint").toString();
  public static final String usersEndPoint = config.getValue("/usersEndPoint").toString();
  public static final int validPostGetId = (int) testConfig.getValue("/validPostGetId");
  public static final int validPostGetUId = (int) testConfig.getValue("/validPostGetUId");
  public static final int invalidPostId = (int) testConfig.getValue("/invalidPostGetId");
  public static final int postUserId = (int) testConfig.getValue("/postUserId");
  public static final int getUserId = (int) testConfig.getValue("/getUserId");
  public static final String userString = testConfig.getValue("/testUser").toString();
  public static final int generatedStringLength = (int) testConfig.getValue("/generatedStringLength");
}
