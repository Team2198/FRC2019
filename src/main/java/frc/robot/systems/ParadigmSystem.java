package frc.robot.systems;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.data.Logger;

public abstract class ParadigmSystem {

    private String name;
    private Logger logger;

    protected XboxController controller;

    public ParadigmSystem(String name, XboxController controller){
        this.name = name;
        this.logger = new Logger();
        this.controller = controller;
    }

    public abstract void update();

    public void enable(){
        log("Enabled!");
    }
    public void disable(){
        log("Disabled!");
    }

    protected void log(Object data){
        String logged = "<" + name + "> " + data;
        logger.log(logged); // Store logged data
        System.out.println(logged); // TODO: Output logged data ?
    }

    public String getName(){
        return name;
    }

    public Logger getLogger(){
        return logger;
    }
}