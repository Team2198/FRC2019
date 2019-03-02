package frc.robot.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Shooter extends ParadigmSystem {

    private TalonSRX shooter;

    public Shooter(XboxController controller) {
        super("Shooter", controller);
    }

    @Override
    public void update() {
        if (controller.getYButtonPressed()) {
            shooter.set(ControlMode.PercentOutput, 0.97); // Activate shooter
        } else if (controller.getYButtonReleased()) {
            shooter.set(ControlMode.PercentOutput, 0); // Disable shooter
        }
    }

    @Override
    public void enable() {
        shooter = new TalonSRX(Constants.SHOOTER_PWM);
        shooter.set(ControlMode.PercentOutput, 0);
        super.enable();
    }

    @Override
    public void disable() {
        shooter.set(ControlMode.PercentOutput, 0); // Disable shooter
        super.disable();
    }
}