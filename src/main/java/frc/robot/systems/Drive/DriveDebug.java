package frc.robot.systems.Drive;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.systems.ParadigmSystem;

public class DriveDebug extends ParadigmSystem {

    private DifferentialDrive drive;
    private final double TURN_SENSE = 0.675;
    private final double TEST_SPEED = 0.5;

    private VictorSP PWM1;
    private VictorSP PWM2;
    private VictorSP PWM3;
    private VictorSP PWM4;

    public DriveDebug(XboxController controller) {
        super("Driver Debug", controller);
    }

    @Override
    public void update() {
        if (controller.getYButtonPressed()) {
            PWM1.set(TEST_SPEED);
        } else if (controller.getYButtonReleased()) {
            PWM1.set(0);

        } else if (controller.getBButtonPressed()) {
            PWM2.set(TEST_SPEED);
        } else if (controller.getBButtonReleased()) {
            PWM2.set(0);

        } else if (controller.getAButtonPressed()) {
            PWM3.set(TEST_SPEED);
        } else if (controller.getAButtonReleased()) {
            PWM3.set(0);

        } else if (controller.getXButtonPressed()) {
            PWM4.set(TEST_SPEED);
        } else if (controller.getXButtonReleased()) {
            PWM4.set(0);
        }
        log("PWM1 Speed = " + PWM1.getSpeed());
        log("PWM2 Speed = " + PWM2.getSpeed());
        log("PWM3 Speed = " + PWM3.getSpeed());
        log("PWM4 Speed = " + PWM4.getSpeed());
    }

    @Override
    public void enable() {

        PWM1 = new VictorSP(Constants.DRIVE_TOP_RIGHT);
        PWM2 = new VictorSP(Constants.DRIVE_BOTTOM_RIGHT);
        PWM3 = new VictorSP(Constants.DRIVE_BOTTOM_LEFT);
        PWM4 = new VictorSP(Constants.DRIVE_TOP_LEFT);

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