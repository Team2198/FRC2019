package frc.robot.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Shooter extends ParadigmSystem {

    /**
     * 
     * Intake X
     * Shoot Y
     * Outtake A
     * ReverseShoot B
     */

    private TalonSRX shooter;
    private TalonSRX collector;

    private boolean shooting = false;

    public Shooter(XboxController controller) {
        super("Shooter", controller);
    }

    @Override
    public void update() {
        shooter();
        intake();
        //log(collector.getMotorOutputVoltage());
        macro();
    }

    private void shooter() {
        if (controller.getYButtonPressed()) {
            shooter.set(ControlMode.PercentOutput, 0.90); // Activate shooter
        } else if (controller.getYButtonReleased()) {
            shooter.set(ControlMode.PercentOutput, 0); // Disable shooter
        } else if (controller.getBButtonPressed()) {
            shooter.set(ControlMode.PercentOutput, -0.3); // Activate reverse shooter
        } else if (controller.getBButtonReleased()) {
            shooter.set(ControlMode.PercentOutput, 0); // Disable reverse shooter
        }
    }

    private void macro() {
        double offset = -.7;
        if (controller.getStickButtonPressed(GenericHID.Hand.kRight) && !shooting) {
            shooting = true;
            Timer t = new Timer();
            t.start();
            do {
                if (t.get() <= 0.3-offset) {
                    collector.set(ControlMode.PercentOutput, -0.4); // Activate Intake
                    shooter.set(ControlMode.PercentOutput, -0.3); // Activate shooter
                } else if (t.get() <= 3.2-offset) {
                    collector.set(ControlMode.PercentOutput, 0);
                    shooter.set(ControlMode.PercentOutput, 0.9); // Activate shooter
                    if (t.get() > 1.8-offset) {
                        collector.set(ControlMode.PercentOutput, 0.9); // Activate Intake
                    }
                }
            } while (!t.hasPeriodPassed(3.2-offset)); 
            shooter.set(ControlMode.PercentOutput, 0);
            collector.set(ControlMode.PercentOutput, 0);
            t.stop();
            shooting = false;   
        }
        
    }

    private void intake() {
        if (controller.getXButtonPressed()) {
            collector.set(ControlMode.PercentOutput, 0.9); // Activate Intake
        } else if (controller.getXButtonReleased()) {
            collector.set(ControlMode.PercentOutput, 0); // Disable Intake
        } else if (controller.getAButtonPressed()) {
            collector.set(ControlMode.PercentOutput, -0.4); // Activate Outtake
        } else if (controller.getAButtonReleased()) {
            collector.set(ControlMode.PercentOutput, 0); // Disable Outtake
        }
    }

    @Override
    public void enable() {
        shooter = new TalonSRX(Constants.SHOOTER_ID);
        shooter.set(ControlMode.PercentOutput, 0);
        collector = new TalonSRX(Constants.INTAKE_ID);
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