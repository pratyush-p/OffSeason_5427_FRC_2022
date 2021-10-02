package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;


public class DriveWithJoystick extends CommandBase
{
    @Override
    public void initialize()
    {
        Robot.m_robotContainer.getDriveTrain().takeJoystickInputs(RobotContainer.getJoystick());
    }

    @Override
    public void execute()
    {
        Robot.m_robotContainer.getDriveTrain().takeJoystickInputs(RobotContainer.getJoystick());
    }

    @Override 
    public boolean isFinished()
    {
        return false;
    }

    @Override
    public void end(boolean interrupted)
    {
        Robot.m_robotContainer.getDriveTrain().stop();
    }

}


