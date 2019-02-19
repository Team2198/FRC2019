package frc.robot.systems;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import frc.robot.systems.ParadigmSystem;

public class Collector extends ParadigmSystem {

    PWMTalonSRX collector;

    public Collector(XboxController controller) {
        super("Collector", controller);
    }

    @Override
    public void update() {
        if (controller.getXButtonPressed()){
            collector.set(1); // Activate intake
        } else if (controller.getXButtonReleased()){
            collector.set(0);
        }
    }

    @Override
    public void enable() {
        collector = new PWMTalonSRX(Constants.collectorPWM);
        super.enable();
    }

    @Override
    public void disable() {
        collector.disable();
        super.disable();
    }
}