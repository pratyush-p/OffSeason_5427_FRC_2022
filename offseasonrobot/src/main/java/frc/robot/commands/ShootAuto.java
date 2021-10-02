package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;


public class ShootAuto extends CommandBase{
    private double pulley_speed;
    private double shooter_speed1;
    private double shooter_speed2;
    private double transport_speed;

    public ShootAuto(double pulley_speed, double shooter_speed1, double shooter_speed2, double transport_speed) {
        this.pulley_speed = pulley_speed;
        this.shooter_speed1 = shooter_speed1;
        this.shooter_speed2 = shooter_speed2;
        this.transport_speed = transport_speed;
    }

    @Override
    public void initialize() {
        RobotContainer.getPulley().movePulley(pulley_speed);
        RobotContainer.getShooter().moveShooter(shooter_speed1, shooter_speed2);
        RobotContainer.getTransport().moveTransport(transport_speed);
    }

    @Override
    public void execute() {
        RobotContainer.getPulley().movePulley(pulley_speed);
        RobotContainer.getShooter().moveShooter(shooter_speed1, shooter_speed2);
        RobotContainer.getTransport().moveTransport(transport_speed);
    }

    @Override
    public boolean isFinished() {

        return !RobotContainer.getJoystick().getRawButton(Constants.AUTON_BUTTON);

    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getPulley().stopPulley();
        RobotContainer.getShooter().stopShooter();
        RobotContainer.getTransport().stopTransport();
    }
}
