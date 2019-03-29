package frc.robot.systems.Drive;

import edu.wpi.first.wpilibj.*;
import frc.robot.Constants;
import frc.robot.systems.ParadigmSystem;

public class Driver extends ParadigmSystem {

    private BandAidDrive drive;
    private SpeedControllerGroup leftMotors;
    private SpeedControllerGroup rightMotors;

    private final double TURN_SENSE = 0.635;

    public Driver(XboxController controller) {
        super("Driver", controller);
    }

    @Override
    public void update() {
        // Curvature drive
        double xSpeed = controller.getY(GenericHID.Hand.kLeft);
        double zRotation = controller.getX(GenericHID.Hand.kRight);

        if (Math.abs(xSpeed) < 0.1) {
            drive.tankDrive(-zRotation * TURN_SENSE, zRotation * TURN_SENSE);
        } else {
            drive.curvatureDrive(xSpeed, zRotation);
        }
    }

    @Override
    public void enable() {
        // Left-side motors
        VictorSP top_Left = new VictorSP(Constants.DRIVE_TOP_LEFT);
        VictorSP extra_Left = new VictorSP(Constants.DRIVE_EXTRA_LEFT);
        VictorSP bottom_Left = new VictorSP(Constants.DRIVE_BOTTOM_LEFT);

        // Right-side motors
        VictorSP top_Right = new VictorSP(Constants.DRIVE_TOP_RIGHT);
        VictorSP extra_Right = new VictorSP(Constants.DRIVE_EXTRA_RIGHT);
        VictorSP bottom_Right = new VictorSP(Constants.DRIVE_BOTTOM_RIGHT);

        // LR SpeedControllers
        leftMotors = new SpeedControllerGroup(top_Left, extra_Left, bottom_Left);
        rightMotors = new SpeedControllerGroup(top_Right, extra_Right, bottom_Right);
        leftMotors.setInverted(true);
        rightMotors.setInverted(true);

        drive = new BandAidDrive(leftMotors, rightMotors); // Initialize DifferentialDrive
        super.enable();
    }

    @Override
    public void disable() {
        drive.stopMotor(); // Stop motors when robot shuts down
        super.disable();
    }

    public BandAidDrive getDrive() {
        return drive;
    }
}