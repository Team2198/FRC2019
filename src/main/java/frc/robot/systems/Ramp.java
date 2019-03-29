package frc.robot.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class Ramp extends ParadigmSystem {

    private TalonSRX ramp;
    private boolean deploying = false;
    private double position = 0;
    private Counter rampCounter;

    private static final double GROUND_POS = 90;

    public Ramp(XboxController controller) {
        super("Ramp", controller);
    }

    @Override
    public void update() {
        double speed = controller.getY(GenericHID.Hand.kLeft);
        ramp.set(ControlMode.PercentOutput, speed);
        updatePos();
    }    
        /*updatePos();
        if (!controller.getAButtonReleased()) return;
        deployRamp();*/


    // Smooth deployment
    private void deployRamp() {
        deploying = true;
        double speed = 0.997;
        while (deploying) {
            speed -= position / 10;
            ramp.set(ControlMode.PercentOutput, speed); // Roll out Ramp
        }
    }

    // Smooth retraction
    private void retractRamp() {
        deploying = true;
        double speed = 0.0;     
        while (deploying) {
            speed += position / 10;
            ramp.set(ControlMode.PercentOutput, speed); // Rollback Ramp
        }
    }

    private void updatePos() {
        //if (!deploying) return;
        position += rampCounter.get();
        rampCounter.reset();
        //if (position >= GROUND_POS) {
          //  deploying = false;
        //}
        SmartDashboard.putNumber("Ramp Pos", position);
    }

    @Override
    public void enable() {
        ramp = new TalonSRX(Constants.RAMP_ID);
        //ramp.setInverted(true);
        rampCounter = new Counter(new DigitalInput(1));
        ramp.set(ControlMode.PercentOutput, 0);
        super.enable();
    }

    @Override
    public void disable() {
        ramp.set(ControlMode.PercentOutput, 0);
        super.disable();
    }
}
