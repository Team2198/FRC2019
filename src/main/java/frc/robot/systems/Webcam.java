package frc.robot.systems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.CameraServer;


public class Webcam extends ParadigmSystem {

    public Webcam(XboxController controller, Driver driver, Hatch hatch) {
        super("Webcam", controller);
    }

    @Override
    public void update() {
        //idk where this goes rn xd
        CameraServer.getInstance().startAutomaticCapture();

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