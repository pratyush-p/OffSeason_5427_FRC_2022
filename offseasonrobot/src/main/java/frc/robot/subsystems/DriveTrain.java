package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase
{
    private SpeedControllerGroup right;
    private SpeedControllerGroup left;
    private DifferentialDrive drive;    

    public DriveTrain(SpeedController left2, SpeedController right2, DifferentialDrive drive) 
    {
        //this.right = right;
        //this.left = left;
        this.drive = drive;
    }

    public void takeJoystickInputs(Joystick joy)
    {
        drive.arcadeDrive(joy.getY(), joy.getZ());
        
    }
    public void moveTankDrive(double leftSpeed, double rightSpeed) 
    {
        left.set(leftSpeed);
        right.set(rightSpeed);

    }  

    public void stop()
    {
        left.stopMotor();
        right.stopMotor();

    }
}