package frc.robot.systems;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.systems.ParadigmSystem;

public class Collector extends ParadigmSystem {

    public Collector(XboxController controller) {
        super("Collector", controller);
    }

    @Override
    public void update() {
    }

    @Override
    public void enable() {
        PWMTalonSRX collector = new PWMTalonSRX(5);
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();
    }
}