package frc.robot.systems;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.systems.ParadigmSystem;

public class Shooter extends ParadigmSystem {

    public Shooter(XboxController controller) {
        super("Shooter", controller);
    }

    @Override
    public void update() {

    }

    @Override
    public void enable() {
        PWMTalonSRX shooter = new PWMTalonSRX(3);
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();
    }
}