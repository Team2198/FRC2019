package frc.robot.systems;

import com.ctre.phoenix.CANifier;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Hatch extends ParadigmSystem {

    private Compressor compressor;
    private DoubleSolenoid solenoid1;
    private DoubleSolenoid solenoid2;

    public Hatch(XboxController controller) {
        super("Hatch", controller);
    }

    @Override
    public void update() {
        // TODO: Better implementation ?
        // Hatch Mechanism
        if (controller.getBumper(GenericHID.Hand.kLeft)) { // Left Bumper is pressed
            // hatch is extended
            solenoid1.set(DoubleSolenoid.Value.kForward);
            solenoid2.set(DoubleSolenoid.Value.kForward);
        } else if (controller.getBumper(GenericHID.Hand.kRight)) { // Right Bumper is pressed
            // Retract both solenoids
            solenoid1.set(DoubleSolenoid.Value.kReverse);
            solenoid2.set(DoubleSolenoid.Value.kReverse);
        } else if (controller.getBumperReleased(GenericHID.Hand.kLeft) || controller.getBumperReleased(GenericHID.Hand.kRight)) { // If either bumper is released
            // Turn off solenoids
            solenoid1.set(DoubleSolenoid.Value.kOff);
            solenoid2.set(DoubleSolenoid.Value.kOff);
        }

        log("<Compressor> Status: " + compressor.getClosedLoopControl());
        log("<Compressor> Pressure level: " + compressor.getCompressorCurrent() + "PSI");
    }

    @Override
    public void enable() {
        compressor = new Compressor(Constants.COMPRESSOR_PIN); // Initialize compressor
        compressor.setClosedLoopControl(true); // Start compressor
        /*solenoid1 = new DoubleSolenoid(Constants.SOLENOID_FORWARD[0], Constants.SOLENOID_REVERSE[0]);
        solenoid2 = new DoubleSolenoid(Constants.SOLENOID_FORWARD[1], Constants.SOLENOID_REVERSE[1]);*/
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();
    }
}
