package sample;

/**
 * Created by GREEN on 27.11.2016.
 */
public class TableText {
    private String process;
    private String time;
    private String playerName;

    TableText( String kPlayerName,String kProcess, String kTime) {
        this.process  = new String (kProcess);
        this.time = new String(kTime);
        this.playerName  = new String(kPlayerName);
    }

    public String getProcess() {
        return process;
    }
    public void setProcess(String kProcess) {
        process = kProcess;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String kTime) {
        time = kTime;
    }
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String kPlayerName) {
        playerName = kPlayerName;
    }

}
