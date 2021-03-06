package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveIntake extends CommandBase {
 
    private double speed;

    public MoveIntake(double speed) {
        this.speed = speed;
    }

    @Override
    public void initialize() {
        RobotContainer.getIntake().moveIntake(speed);
        
    }

    @Override
    public void execute() {
        RobotContainer.getIntake().moveIntake(speed);
    }

    @Override
    public boolean isFinished() {

       
        return !RobotContainer.getJoystick().getRawButton(Constants.INTAKE_BUTTON);
 
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getIntake().stopIntake();
    }
}
