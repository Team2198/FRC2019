package frc.robot.systems;

import edu.wpi.first.wpilibj.XboxController;

public class Webcam extends ParadigmSystem {

    public Webcam(XboxController controller, Driver driver, Hatch hatch) {
        super("Webcam", controller);
    }

    @Override
    public void update() {

    }

    @Override
    public void enable() {
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();
    }

}