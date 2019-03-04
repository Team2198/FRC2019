package frc.robot.systems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vision extends ParadigmSystem {

    private final double kP = -0.1f; // Proportional control constant

    private Driver drive;
    private Hatch hatch;

    private NetworkTable table;
    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry ta;

    public Vision(XboxController controller, Driver driver, Hatch hatch) {
        super("Vision", controller);
        this.drive = driver;
        this.hatch = hatch;
    }

    @Override
    public void update() {
        //read values periodically
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);

        //post to smart dashboard periodically
        SmartDashboard.putNumber("Limelight X", x);
        SmartDashboard.putNumber("Limelight Y", y);
        SmartDashboard.putNumber("Limelight Area", area);

        if (controller.getYButton()){
            double steering_adjust = kP * x;
            drive.getDrive().tankDrive(steering_adjust, -steering_adjust);
        }
    }

    @Override
    public void enable() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
        super.enable();
    }

    @Override
    public void disable() {
        super.disable();
    }
}