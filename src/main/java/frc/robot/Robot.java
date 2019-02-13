/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends IterativeRobot {

  // Selector vars
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private DifferentialDrive drive;
  private Compressor compressor;
  private XboxController controller;
  private DoubleSolenoid shaft1;
  private DoubleSolenoid shaft2;

  private Timer autoTimer;

  @Override
  public void robotInit() { // Initialize Robot

    /* m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser); */
    
    // TODO: Set PWMs
    // PWM for Leftside motors
    PWMTalonSRX top_Left = new PWMTalonSRX(2);
    PWMTalonSRX bottom_Left = new PWMTalonSRX(2);
    // PWM for Rightside motors
    PWMTalonSRX top_Right = new PWMTalonSRX(2);
    PWMTalonSRX bottom_Right = new PWMTalonSRX(2);
    // LR SpeedControllers
    SpeedControllerGroup leftMotors = new SpeedControllerGroup(top_Left, bottom_Left);
    SpeedControllerGroup rightMotors = new SpeedControllerGroup(top_Right, bottom_Right);
    
    drive = new DifferentialDrive(leftMotors, rightMotors); // Initialize DifferentialDrive
    controller = new XboxController(0); // Initialize Controller TODO
    compressor = new Compressor(0); // Initialize compressor TODO
    compressor.setClosedLoopControl(true); // Start compressor
    // TODO: Set Solenoid Channels
    shaft1 = new DoubleSolenoid(1, 2);
    shaft2 = new DoubleSolenoid(2, 3);
  }

  @Override
  public void teleopPeriodic() { // Teleop UPS
    // Curvature Drive
    double xSpeed = controller.getY(GenericHID.Hand.kLeft);
    double zRotation = controller.getX(GenericHID.Hand.kRight);
    boolean quickTurn = controller.getStickButton(GenericHID.Hand.kRight);
    drive.curvatureDrive(xSpeed, zRotation, quickTurn);

    // TODO: Better implementation ?
    // Hatch Mechanism
    if (controller.getBumper(GenericHID.Hand.kLeft)) { // Left Bumper is pressed
      // hatch is extended
      shaft1.set(DoubleSolenoid.Value.kForward);
      shaft2.set(DoubleSolenoid.Value.kForward);
    } else if (controller.getBumper(GenericHID.Hand.kRight)) { // Right Bumper is pressed
      // Retract both solenoids
      shaft1.set(DoubleSolenoid.Value.kReverse);
      shaft2.set(DoubleSolenoid.Value.kReverse);
    } else if (controller.getBumperReleased(GenericHID.Hand.kLeft) || controller.getBumperReleased(GenericHID.Hand.kRight)) { // If either bumper is released
      // Turn off solenoids
      shaft1.set(DoubleSolenoid.Value.kOff);
      shaft2.set(DoubleSolenoid.Value.kOff);
    }
  }

  @Override
  public void robotPeriodic() { // UP Every "Robot Packet" / Debug output
    System.out.println("<Compressor> Status: " + compressor.getClosedLoopControl());
    System.out.println("<Compressor> Pressure level: " + compressor.getPressureSwitchValue() + "PSI");
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    autoTimer = new Timer(); // Initialize autonomous timer
    autoTimer.start(); // Start timer
    
    m_autoSelected = m_chooser.getSelected();
    // autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    if (autoTimer.get() < 3.0) {
      drive.tankDrive(-0.2, -0.2, false);
    } else { 
      drive.stopMotor();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  @Override
  public void disabledInit(){ // Shutdown
    drive.stopMotor(); // Stop motors when robot shuts down
  }
}