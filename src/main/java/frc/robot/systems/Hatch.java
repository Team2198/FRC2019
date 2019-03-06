package frc.robot.systems;

import edu.wpi.first.wpilibj.*;
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
        if (controller.getBButtonPressed()) { // Left Bumper is pressed
            grab();
        } else if (controller.getAButtonPressed()) { // If either bumper is released
            release();
        }

        //log("<Compressor> Pressure level: " + compressor.getCompressorCurrent() + "PSI");
    }

    public void grab(){
        gripper.set(DoubleSolenoid.Value.kForward);
        //gripper.set(DoubleSolenoid.Value.kOff);
    }

    public void release(){
        gripper.set(DoubleSolenoid.Value.kReverse);
        //gripper.set(DoubleSolenoid.Value.kOff);
    }

    @Override
    public void enable() {
        compressor = new Compressor(Constants.PDP_PIN); // Initialize compressor
        compressor.setClosedLoopControl(true); // Start compressor
        // Initialize release mechanism
        //hatchReleasePistons = new DoubleSolenoid(Constants.PDP_PIN, Constants.SOLENOID_FORWARD[0], Constants.SOLENOID_REVERSE[0]);
        //hatchReleasePistons.set(DoubleSolenoid.Value.kForward);

        // Initialize gripper mechanism
        gripper = new DoubleSolenoid(Constants.SOLENOID_FORWARD[1], Constants.SOLENOID_REVERSE[1]);
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