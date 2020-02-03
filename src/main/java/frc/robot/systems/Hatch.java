package frc.robot.systems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Constants;

public class Hatch extends ParadigmSystem {

    private XboxController controller2;

    private Compressor compressor;
    private DoubleSolenoid gripper;
    private DoubleSolenoid hatchReleasePistons;

    public Hatch(XboxController controller, XboxController controller2) {
        super("Hatch Gripper", controller);
        this.controller2 = controller2;
    }

    @Override
    public void update() {
        // Hatch Mechanism
        if (controller.getBumperPressed(Hand.kRight)) {
            //log("Player 1 Pressed Right Bumper, Grabbing Hatch!");
            grab();
        } else if (controller.getBumperPressed(Hand.kLeft)) {
            //log("Player 1 Pressed Left Bumper, Releasing Hatch!");
            release();
        } else if (controller2.getBumperPressed(Hand.kRight)){
            //log("Player 2 Pressed Right Bumper, Pushing Hatch Mechanism!");
            pushOut();
        } else if (controller2.getBumperPressed(Hand.kLeft)){
            //log("Player 2 pressed Left Bumper, Pulling Hatch Mechanism!");
            pullIn();
        }

       log("<Compressor> Pressure level: " + compressor.getCompressorCurrent() + "PSI");
    }

    public void grab(){
        gripper.set(DoubleSolenoid.Value.kReverse);
    }

    public void release(){
        gripper.set(DoubleSolenoid.Value.kForward);
    }

    public void pushOut(){
        hatchReleasePistons.set(DoubleSolenoid.Value.kForward);
    }

    public void pullIn(){
        hatchReleasePistons.set(DoubleSolenoid.Value.kReverse);
    }

    @Override
    public void enable() {
        compressor = new Compressor(Constants.PCM_PIN); // Initialize compressor
        compressor.setClosedLoopControl(true); // Start compressor              

        // Initialize release mechanism
        hatchReleasePistons = new DoubleSolenoid(Constants.PCM_PIN, Constants.SOLENOID_FORWARD[0], Constants.SOLENOID_REVERSE[0]);
        //hatchReleasePistons.set(DoubleSolenoid.Value.kReverse);

        // Initialize gripper mechanism
        gripper = new DoubleSolenoid(Constants.PCM_PIN, Constants.SOLENOID_FORWARD[1], Constants.SOLENOID_REVERSE[1]);
        super.enable();
    }

    @Override
    public void disable() {
        //hatchReleasePistons.set(DoubleSolenoid.Value.kReverse);
        gripper.set(DoubleSolenoid.Value.kForward);
        //hatchReleasePistons.set(DoubleSolenoid.Value.kOff);
        super.disable();
    }
}