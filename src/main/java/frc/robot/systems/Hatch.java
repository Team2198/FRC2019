package frc.robot.systems;

import com.ctre.phoenix.CANifier;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Hatch extends ParadigmSystem {

    private Compressor compressor;
    /*private DoubleSolenoid[] solenoids = {new DoubleSolenoid(Constants.SOLENOID_FORWARD[1], Constants.SOLENOID_FORWARD[1]),
            new DoubleSolenoid(Constants.SOLENOID_FORWARD[2], Constants.SOLENOID_REVERSE[2])};*/

    public Hatch(XboxController controller) {
        super("Hatch", controller);
    }

    @Override
    public void update() {
        // TODO: Better implementation ?
        // Hatch Mechanism
        /*if (controller.getBumper(GenericHID.Hand.kLeft)) { // Left Bumper is pressed
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
        }*/

        log("<Compressor> Status: " + compressor.getClosedLoopControl());
        log("<Compressor> Pressure level: " + compressor.getPressureSwitchValue() + "PSI");
    }

    @Override
    public void enable() {
        compressor = new Compressor(Constants.COMPRESSOR_PIN); // Initialize compressor
        compressor.setClosedLoopControl(true); // Start compressor
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();
    }
}
