package frc.robot.systems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;

public class Driver extends ParadigmSystem {

    private DifferentialDrive drive;
    private final double TURN_SENSE = 0.675;

    public Driver(XboxController controller) {
        super("Driver", controller);
    }

    @Override
    public void update() {
        // Curvature drive
        double xSpeed = controller.getY(GenericHID.Hand.kLeft);
        double zRotation = controller.getX(GenericHID.Hand.kRight);
        boolean quickTurn = controller.getStickButton(GenericHID.Hand.kRight);

        if (Math.abs(xSpeed) < 0.1) {
            drive.tankDrive(zRotation * TURN_SENSE, -zRotation * TURN_SENSE);
        } else {
            drive.curvatureDrive(xSpeed, zRotation, quickTurn);
        }
        }

    @Override
    public void enable() {
        // Left-side motors
        VictorSP top_Left = new VictorSP(Constants.DRIVE_TOP_LEFT);
        VictorSP bottom_Left = new VictorSP(Constants.DRIVE_BOTTOM_LEFT);

        // Right-side motors
        VictorSP top_Right = new VictorSP(Constants.DRIVE_TOP_RIGHT);
        VictorSP bottom_Right = new VictorSP(Constants.DRIVE_BOTTOM_RIGHT);

        // LR SpeedControllers
        SpeedControllerGroup leftMotors = new SpeedControllerGroup(top_Left, bottom_Left);
        SpeedControllerGroup rightMotors = new SpeedControllerGroup(top_Right, bottom_Right);

        drive = new DifferentialDrive(leftMotors, rightMotors); // Initialize DifferentialDrive
        super.enable();
    }

    @Override
    public void disable() {
        drive.stopMotor(); // Stop motors when robot shuts down
        super.disable();
    }

    public DifferentialDrive getDrive() {
        return drive;
    }
}