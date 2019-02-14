package frc.robot.data;

import java.util.ArrayList;

public class Logger {

    private ArrayList<Object> logs;

    public Logger(){
        logs = new ArrayList<>();
    }

    public void log(Object data){
        logs.add(data);
    }

    public ArrayList<Object> getData(){
        return logs;
    }
}