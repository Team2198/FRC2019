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
        // TODO: Controller Change?
        // Hatch Mechanism
        if (controller.getBumperPressed(GenericHID.Hand.kLeft)) { // Left Bumper is pressed
            // hatch is extended
            gripper.set(DoubleSolenoid.Value.kForward);
        } else if (controller.getBumperReleased(GenericHID.Hand.kLeft)) { // If either bumper is released
            // Turn off solenoids
            gripper.set(DoubleSolenoid.Value.kReverse);
            gripper.set(DoubleSolenoid.Value.kOff);
        }

        log("<Compressor> Pressure level: " + compressor.getCompressorCurrent() + "PSI");
    }

    @Override
    public void enable() {
        compressor = new Compressor(Constants.COMPRESSOR_PIN); // Initialize compressor
        compressor.setClosedLoopControl(true); // Start compressor
        // Initialize release mechanism
        hatchReleasePistons = new DoubleSolenoid(Constants.SOLENOIDS_PIN, Constants.SOLENOID_FORWARD[0], Constants.SOLENOID_REVERSE[0]);
        hatchReleasePistons.set(DoubleSolenoid.Value.kForward);

        // Initialize gripper mechanism
        gripper = new DoubleSolenoid(Constants.SOLENOIDS_PIN, Constants.SOLENOID_FORWARD[1], Constants.SOLENOID_REVERSE[1]);
        super.enable();
    }

    @Override
    public void disable() {
        hatchReleasePistons.set(DoubleSolenoid.Value.kReverse);
        gripper.set(DoubleSolenoid.Value.kReverse);
        hatchReleasePistons.set(DoubleSolenoid.Value.kOff);
        gripper.set(DoubleSolenoid.Value.kOff);
        super.disable();
    }
}