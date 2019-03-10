package frc.robot.systems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import frc.robot.pixy2api.Pixy2;

public class PixyVision extends ParadigmSystem {

    private Pixy2 pixy;

    public PixyVision(XboxController controller) {
        super("Pixy Vision", controller);
    }

    @Override
    public void update() {

    }

    @Override
    public void enable() {
        pixy = Pixy2.createInstance(Pixy2.LinkType.UART);
        pixy.init(Constants.UART);
        super.enable();
    }

    @Override
    public void disable() {

        super.disable();
    }
}
