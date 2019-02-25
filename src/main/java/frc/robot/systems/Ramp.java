package frc.robot.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Ramp extends ParadigmSystem {

    TalonSRX ramp;

    public Ramp(XboxController controller) {
        super("Ramp", controller);
    }

    @Override
    public void update() {
        if (!controller.getXButtonReleased()) return;
        deployRamp();
    }

    private void deployRamp(){
        Timer ramper = new Timer();
        ramper.start();
        double xSpeed = 1;
        while (ramper.get() < 2) {
            // Smooth downward motion
            xSpeed -= 0.01;
            xSpeed = xSpeed * xSpeed;
                    ramp.set(ControlMode.PercentOutput, xSpeed); // Rollback Ramp
        }
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
