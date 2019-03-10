package frc.robot.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Shooter extends ParadigmSystem {

    private TalonSRX shooter;
    private TalonSRX collector;

    private boolean shooting = false;

    public Shooter(XboxController controller) {
        super("Shooter", controller);
    }

    @Override
    public void update() {
        shooter();
        if (controller.getXButtonPressed()) {
            collector.set(ControlMode.PercentOutput, 0.99); // Activate Intake
        } else if (controller.getXButtonReleased()) {
            collector.set(ControlMode.PercentOutput, 0); // Disable Intake
        } else if (controller.getBackButtonPressed()) {
            collector.set(ControlMode.PercentOutput, -0.3); // Activate Outtake
        } else if (controller.getBackButtonReleased()) {
            collector.set(ControlMode.PercentOutput, 0); // Disable Outtake
        }
        intake();
        //log(collector.getMotorOutputVoltage());
        //macro();
    }

    private void shooter() {
        if (controller.getYButtonPressed()) {
            shooter.set(ControlMode.PercentOutput, 0.9); // Activate shooter
        } else if (controller.getYButtonReleased()) {
            shooter.set(ControlMode.PercentOutput, 0); // Disable shooter
        } else if (controller.getStartButtonPressed()) {
            shooter.set(ControlMode.PercentOutput, -0.3); // Activate shooter
        } else if (controller.getStartButtonReleased()) {
            shooter.set(ControlMode.PercentOutput, 0); // Disable shooter
        }
    }
    /**
     * 
     */

    private void macro() {
        if (controller.getBumperPressed(GenericHID.Hand.kRight) && !shooting) {
            shooting = true;
            Timer t = new Timer();
            t.start();
            do {
                if (t.get() < 0.3) {
                    collector.set(ControlMode.PercentOutput, -0.9); // Activate Intake
                    shooter.set(ControlMode.PercentOutput, -0.35); // Activate shooter
                } else if (t.get() < 1.0) {
                    shooter.set(ControlMode.PercentOutput, 0.95); // Activate shooter
                    if (t.get() > 0.6) {
                        collector.set(ControlMode.PercentOutput, 0.9); // Activate Intake
                    }
                }
            } while (!t.hasPeriodPassed(0.3));
            shooting = false;

        }
    }

    private void intake() {

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