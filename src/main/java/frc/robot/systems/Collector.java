package frc.robot.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Collector extends ParadigmSystem {

    private TalonSRX collector;

    public Collector(XboxController controller) {
        super("Collector", controller);
    }

    @Override
    public void update() {
        if (controller.getXButtonPressed()) {
            collector.set(ControlMode.PercentOutput, 0.2); // Activate Intake
        } else if (controller.getXButtonReleased()) {
            collector.set(ControlMode.PercentOutput, 0); // Disable Intake
        }
    }

    @Override
    public void enable() {
        collector = new TalonSRX(Constants.INTAKE_PWM);
        collector.setInverted(true);
        collector.set(ControlMode.PercentOutput, 0); // Disable Intake
        super.enable();
    }

    @Override
    public void disable() {
        collector.set(ControlMode.PercentOutput, 0); // Disable Intake
        super.disable();
    }
}