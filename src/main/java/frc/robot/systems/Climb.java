package frc.robot.systems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Constants;

public class Climb extends ParadigmSystem {

    private Compressor compressor;
    private DoubleSolenoid climbPistons;

    public Climb(XboxController controller) {
        super("Climber", controller);
    }

    @Override
    public void update() {
        // 
        if (controller.getStartButtonReleased() && controller.getBackButtonReleased()) {

            in();
        } else if (controller.getAButtonReleased()) {
            
            out();
        }  
    }

    public void in(){
        climbPistons.set(DoubleSolenoid.Value.kReverse);
    }

    public void out(){
        climbPistons.set(DoubleSolenoid.Value.kForward);
    }

    @Override
    public void enable() {
        // Initialize climb mechanism
        climbPistons = new DoubleSolenoid(Constants.PCM_PIN, Constants.SOLENOID_FORWARD[2], Constants.SOLENOID_REVERSE[2]);
        climbPistons.set(DoubleSolenoid.Value.kReverse);
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();
    }
}