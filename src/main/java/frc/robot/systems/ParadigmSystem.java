package frc.robot.systems;

import edu.wpi.first.wpilibj.XboxController;

public abstract class ParadigmSystem {

    private String name;

    protected XboxController controller;

    public ParadigmSystem(String name, XboxController controller){
        this.name = name;
        this.controller = controller;
    }

    public abstract void update();

    public void enable(){
        log("Enabled!");
    }
    public void disable(){
        log("Disabled!");
    }
    public void restart(){
        disable();
        enable();
    }

    protected void log(Object data){
        String logged = "<" + name + "> " + data;
        System.out.println(logged);
    }

    public String getName(){
        return name;
    }
}