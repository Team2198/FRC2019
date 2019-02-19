package frc.robot.systems;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import frc.robot.systems.ParadigmSystem;

public class Shooter extends ParadigmSystem {

    PWMTalonSRX shooter;

    public Shooter(XboxController controller) {
        super("Shooter", controller);
    }

    @Override
    public void update() {
        if (controller.getYButtonPressed()){
            shooter.set(1); // Activate shooter
        } else if (controller.getYButtonReleased()){
            shooter.set(0); // Disable shooter
        }
    }

    @Override
    public void enable() {
        shooter = new PWMTalonSRX(Constants.shooterPWM);
        super.enable();
    }

    @Override
    public void disable() {
        shooter.disable();
        super.disable();
    }
}