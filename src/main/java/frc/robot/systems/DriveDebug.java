package frc.robot.systems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;

public class DriveDebug extends ParadigmSystem {

    private DifferentialDrive drive;
    private final double TURN_SENSE = 0.675;

    private VictorSP PWM1;
    private Talon PWM2;
    private Talon PWM3;
    private VictorSP PWM4;

    public DriveDebug(XboxController controller) {
        super("Driver Debug", controller);
    }

    @Override
    public void update() {
        if (controller.getYButtonPressed()) {
            PWM1.set(1);
        } else if (controller.getYButtonReleased()) {
            PWM1.set(0);

        } else if (controller.getBButtonPressed()) {
            PWM2.set(1);
        } else if (controller.getBButtonReleased()) {
            PWM2.set(0);

        } else if (controller.getAButtonPressed()) {
            PWM3.set(1);
        } else if (controller.getAButtonReleased()) {
            PWM3.set(0);

        } else if (controller.getXButtonPressed()) {
            PWM4.set(1);
        } else if (controller.getXButtonReleased()) {
            PWM4.set(0);
        }

    }

    @Override
    public void enable() {

        PWM1 = new VictorSP(1);
        PWM2 = new Talon(6);
        PWM3 = new Talon(5);
        PWM4 = new VictorSP(4);

        super.enable();
    }

    @Override
    public void disable() {
        PWM1.set(0);
        PWM2.set(0);
        PWM3.set(0);
        PWM4.set(0);
        super.disable();
    }

    public DifferentialDrive getDrive() {
        return drive;
    }
}