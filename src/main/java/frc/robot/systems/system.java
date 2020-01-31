package frc.robot.systems;

import edu.wpi.first.wpilibj.XboxController;

public class system extends ParadigmSystem {

	public system(String name, XboxController controller) {
		super(name, controller);
	}

	@Override
	public void update() {
        if (controller.getAButtonPressed()){
            
        }
	}
    
}