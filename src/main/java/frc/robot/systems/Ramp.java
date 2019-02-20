package frc.robot.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Ramp extends ParadigmSystem {

    TalonSRX ramp;

    public Ramp(XboxController controller) {
        super("Ramp", controller);
    }

    @Override
    public void update() {
        double xSpeed = controller.getY(Hand.kLeft);
        ramp.set(ControlMode.PercentOutput, xSpeed); // Rollback Ramp
    }

    @Override
    public void enable() {
        ramp = new TalonSRX(Constants.RAMP_PWM);
        ramp.setInverted(true);
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();
    }
}
