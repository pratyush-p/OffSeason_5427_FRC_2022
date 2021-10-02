package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MovePulley extends CommandBase {
    private double speed;

    public MovePulley(double speed) {
        this.speed = speed;
    }

    @Override
    public void initialize() {
        RobotContainer.getPulley().movePulley(speed);
    }

    @Override
    public void execute() {
        RobotContainer.getPulley().movePulley(speed);
    }
    
    @Override
    public boolean isFinished() {

            return !RobotContainer.getJoystick().getRawButton(Constants.PULLEY_BUTTON);

    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getPulley().stopPulley();
    } 
}
