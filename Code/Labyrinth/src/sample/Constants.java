package sample;

/**
 * Created by GREEN on 14.11.2016.
 */
public class Constants {
    public static String blue = "file:Images/blue.png";
    public static String red = "file:Images/red.png";
    public static String bomb = "file:Images/bomb.png";
    public static String bombClicked = "file:Images/bombClicked.png";
    public static String leftButton = "file:Images/left.png";
    public static String leftButtonClicked = "file:Images/leftClicked.png";
    public static String rightButton = "file:Images/right.png";
    public static String rightButtonClicked = "file:Images/rightClicked.png";
    public static String upButton = "file:Images/up.png";
    public static String upButtonClicked = "file:Images/upClicked.png";
    public static String downButton = "file:Images/down.png";
    public static String downButtonClicked = "file:Images/downClicked.png";

    public static String player = "file:Images/playerL.png";
    public static String[] wallImagesUrl = {
            "file:Images/wallNone.png",
            "file:Images/wallDown.png",
            "file:Images/wallUp.png",
            "file:Images/wallUpDown.png",
            "file:Images/wallRight.png",
            "file:Images/wallDownRight.png",
            "file:Images/wallUpRight.png",
            "file:Images/wallDownUpRight.png",
            "file:Images/wallLeft.png",
            "file:Images/wallDownLeft.png",
            "file:Images/wallUpLeft.png",
            "file:Images/wallDownUpLeft.png",
            "file:Images/wallLeftRight.png",
            "file:Images/wallDownLeftRight.png",
            "file:Images/wallUpLeftRight.png"
    };

    public static String[] trapBonusImagesUrl = {
            "file:Images/hole1L.png",
            "file:Images/hole2L.png",
            "file:Images/hole3L.png",
            "file:Images/hole4L.png",
            "file:Images/door1L.png",
            "file:Images/door2L.png",
            "file:Images/door3L.png",
            "file:Images/door4L.png",
            "file:Images/trapL.png",
            "file:Images/crossbowL.png",
            "file:Images/crutchL.png",
            "file:Images/realTreasureL.png",
            "file:Images/falseTreasureL.png",
            "file:Images/falseTreasureL.png",
            "file:Images/falseTreasureL.png"
    };

    public int wallNoneI = 0;
    public int wallDownI = 1;
    public int wallUpI = 2;
    public int wallUpDownI = 3;
    public int wallRightI = 4;
    public int wallDownRightI = 5;
    public int wallUpRightI = 6;
    public int wallDownUpRightI = 7;
    public int wallLeftI = 8;
    public int wallDownLeftI = 9;
    public int wallUpLeftI = 10;
    public int wallDownUpLeftI = 11;
    public int wallLeftRightI = 12;
    public int wallDownLeftRightI = 13;
    public int wallUpLeftRightI = 14;

    public int hole1I = 0;
    public int hole2I = 1;
    public int hole3I = 2;
    public int hole4I = 3;
    public int door1I = 4;
    public int door2I = 5;
    public int door3I = 6;
    public int door4I = 7;
    public int trapI = 8;
    public int crossbowI = 9;
    public int crutchI = 10;
    public int realTreasureI = 11;
    public int falseTreasure1I = 12;
    public int falseTreasure2I = 13;
    public int falseTreasure3I = 14;

    public static int left = 0;
    public static int right = 1;
    public static int up = 2;
    public static int down = 3;

    public String exitRequest = "0";
    public String endCourseRequest = "1";
    public String timeOutRequest = "2";
    public String pauseRequest = "3";
    public String leftRequest = "4";
    public String rightRequest = "5";
    public String upRequest = "6";
    public String downRequest = "7";
    public String grenadeLeftRequest = "8";
    public String grenadeRightRequest = "9";
    public String grenadeUpRequest = "10";
    public String grenadeDownRequest = "11";
    public String pickTreasure = "12";
    public String onTreasure = "13";
    public String onCrossbow = "14";
    public String onCrutch = "15";
    public String onTrap = "16";

    public static int ready =0;
    public static int beginCoordinates = 1;
    public static int course = 2;
    public static int game =3;
    Constants(){}

}
