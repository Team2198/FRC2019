package frc.robot.systems;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Ramp extends ParadigmSystem {

    PWMTalonSRX rampPWM;

    public Ramp(XboxController controller) {
        super("Ramp", controller);
    }

    @Override
    public void update() {
        if (controller.getAButtonPressed()){
            rampPWM.set(1); // Deploy Ramp
        } else if (controller.getAButtonReleased()){
            rampPWM.set(0); // Rollback Ramp
        }
    }

    @Override
    public void enable() {
        rampPWM = new PWMTalonSRX(Constants.rampPWM);
        super.enable();
    }

    @Override
    public void disable() {
        rampPWM.disable();
        super.disable();
    }
}
