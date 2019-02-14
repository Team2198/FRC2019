package frc.robot.systems.hatch;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.systems.ParadigmSystem;

public class Hatch extends ParadigmSystem {

    private Compressor compressor;
    private DoubleSolenoid[] solenoids = {new DoubleSolenoid(1, 2), new DoubleSolenoid(2, 3)};

    public Hatch(XboxController controller) {
        super("Hatch", controller);
    }

    @Override
    public void update() {
        // TODO: Better implementation ?
        // Hatch Mechanism
        if (controller.getBumper(GenericHID.Hand.kLeft)) { // Left Bumper is pressed
            // hatch is extended
            solenoids[0].set(DoubleSolenoid.Value.kForward);
            solenoids[1].set(DoubleSolenoid.Value.kForward);
        } else if (controller.getBumper(GenericHID.Hand.kRight)) { // Right Bumper is pressed
            // Retract both solenoids
            solenoids[0].set(DoubleSolenoid.Value.kReverse);
            solenoids[1].set(DoubleSolenoid.Value.kReverse);
        } else if (controller.getBumperReleased(GenericHID.Hand.kLeft) || controller.getBumperReleased(GenericHID.Hand.kRight)) { // If either bumper is released
            // Turn off solenoids
            solenoids[0].set(DoubleSolenoid.Value.kOff);
            solenoids[1].set(DoubleSolenoid.Value.kOff);
        }
    }

    @Override
    public void enable() {
        // TODO: Set Solenoid Channels
        compressor = new Compressor(0); // Initialize compressor
        compressor.setClosedLoopControl(true); // Start compressor
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();
    }
}
