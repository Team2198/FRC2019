package frc.robot.systems;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.systems.ParadigmSystem;

public class Driver extends ParadigmSystem {

    private DifferentialDrive drive;

    public Driver(XboxController controller) {
        super("Driver", controller);
    }

    @Override
    public void update() {
        // Curvature drive
        double xSpeed = controller.getY(GenericHID.Hand.kLeft);
        double zRotation = controller.getX(GenericHID.Hand.kRight);
        boolean quickTurn = controller.getStickButton(GenericHID.Hand.kRight);
        drive.curvatureDrive(xSpeed, zRotation, quickTurn);
    }

    @Override
    public void enable() {
        // TODO: Set PWMs
        // PWM for Leftside motors
        VictorSP top_Left = new VictorSP(2);
        VictorSP bottom_Left = new VictorSP(2);

        // PWM for Rightside motors
        VictorSP top_Right = new VictorSP(2);
        VictorSP bottom_Right = new VictorSP(2);

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