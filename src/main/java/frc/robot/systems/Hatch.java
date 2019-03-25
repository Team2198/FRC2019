package frc.robot.systems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Constants;

public class Hatch extends ParadigmSystem {

    private Compressor compressor;
    private DoubleSolenoid gripper;
    private DoubleSolenoid hatchReleasePistons;

    public Hatch(XboxController controller) {
        super("Hatch Gripper", controller);
    }

    @Override
    public void update() {
        // Hatch Mechanism
        if (controller.getBButtonPressed()) {
            log("B pressed, grabbing");
            grab();
        } else if (controller.getAButtonPressed()) {
            log("A pressed, releasing");
            release();
        } else if (controller.getBumperPressed(Hand.kRight)){
            log("BackRight pressed, pushing");
            pushOut();
        } else if (controller.getBumperPressed(Hand.kLeft)){
            log("BackLeft pressed, pulling");
            pullIn();
        }

       //log("<Compressor> Pressure level: " + compressor.getCompressorCurrent() + "PSI");
    }

    public void grab(){
        gripper.set(DoubleSolenoid.Value.kReverse);
    }

    public void release(){
        gripper.set(DoubleSolenoid.Value.kForward);
    }

    public void pushOut(){
        hatchReleasePistons.set(DoubleSolenoid.Value.kForward);
    }

    public void pullIn(){
        hatchReleasePistons.set(DoubleSolenoid.Value.kReverse);
    }

    @Override
    public void enable() {
        compressor = new Compressor(Constants.PCM_PIN); // Initialize compressor
        compressor.setClosedLoopControl(true); // Start compressor              

        // Initialize release mechanism
        hatchReleasePistons = new DoubleSolenoid(Constants.PCM_PIN, Constants.SOLENOID_FORWARD[0], Constants.SOLENOID_REVERSE[0]);
        //hatchReleasePistons.set(DoubleSolenoid.Value.kReverse);

        // Initialize gripper mechanism
        gripper = new DoubleSolenoid(Constants.PCM_PIN, Constants.SOLENOID_FORWARD[1], Constants.SOLENOID_REVERSE[1]);
        super.enable();
    }

    @Override
    public void disable() {
        //hatchReleasePistons.set(DoubleSolenoid.Value.kReverse);
        gripper.set(DoubleSolenoid.Value.kForward);
        //hatchReleasePistons.set(DoubleSolenoid.Value.kOff);
        super.disable();
    }
}