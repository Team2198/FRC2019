package frc.robot.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Shooter extends ParadigmSystem {

    private TalonSRX shooter;
    private TalonSRX collector;

    public Shooter(XboxController controller) {
        super("Shooter", controller); 
    }

    @Override
    public void update() {
        if (controller.getYButtonPressed()) {
            shooter.set(ControlMode.PercentOutput, 0.9); // Activate shooter
        } else if (controller.getYButtonReleased()) {
            shooter.set(ControlMode.PercentOutput, 0); // Disable shooter
        } else if  (controller.getStartButtonPressed()) {
            shooter.set(ControlMode.PercentOutput, -0.5); // Activate shooter
        } else if (controller.getStartButtonReleased()){
            shooter.set(ControlMode.PercentOutput, 0); // Disable shooter
        }

        intake();
    }

    private void intake() {
        if (controller.getXButtonPressed()) {
            collector.set(ControlMode.PercentOutput, 0.9); // Activate Intake
        } else if (controller.getXButtonReleased()) {
            collector.set(ControlMode.PercentOutput, 0); // Disable Intake
        } else if (controller.getBackButtonPressed()){
            collector.set(ControlMode.PercentOutput, -0.5); // Activate Outtake
        } else if (controller.getBackButtonReleased()){
            collector.set(ControlMode.PercentOutput, 0); // Disable Outtake
        }
    }

    @Override
    public void enable() {
        shooter = new TalonSRX(Constants.SHOOTER_PWM);
        shooter.set(ControlMode.PercentOutput, 0);
        collector = new TalonSRX(Constants.INTAKE_PWM);
        collector.setInverted(true);
        collector.set(ControlMode.PercentOutput, 0); // Disable Intake
        super.enable();
    }

    @Override
    public void disable() {
        shooter.set(ControlMode.PercentOutput, 0); // Disable shooter
        collector.set(ControlMode.PercentOutput, 0); // Disable Intake
        super.disable();
    }
}