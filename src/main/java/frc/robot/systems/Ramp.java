package frc.robot.systems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Ramp extends ParadigmSystem {

    private TalonSRX ramp;
    private boolean deploying = false;
    private double position = 0;
    private Counter rampCounter;

    private static final double GROUND_POS = 2;

    public Ramp(XboxController controller) {
        super("Ramp", controller);
    }

    @Override
    public void update() {
        updatePos();
        if (!controller.getXButtonReleased()) return;
        deployRamp();
    }

    // Smooth deployment
    private void deployRamp() {
        deploying = true;
        double speed = 0.997;
        while (deploying) {
            speed -= position / 10;
            ramp.set(ControlMode.PercentOutput, speed); // Rollback Ramp
        }
    }

    private void updatePos() {
        if (!deploying) return;
        position += rampCounter.get();
        rampCounter.reset();
        if (position >= GROUND_POS) {
            deploying = false;
        }
    }

    @Override
    public void enable() {
        ramp = new TalonSRX(Constants.RAMP_PWM);
        ramp.setInverted(true);
        rampCounter = new Counter(new DigitalInput(1));
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();
    }
}
