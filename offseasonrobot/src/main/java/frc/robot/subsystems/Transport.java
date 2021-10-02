package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Transport extends SubsystemBase {


    private SpeedController transportSpeedController;
    private AnalogInput transportProximity;
    //^ is unused

    public Transport(SpeedController transportSpeedController, AnalogInput transportProximity) {
        this.transportSpeedController = transportSpeedController;
        this.transportProximity = transportProximity;
        //^ is unused
    }

    public void moveTransport(double speed) {
        transportSpeedController.set(speed);
    }

    public void stopTransport() {
        transportSpeedController.stopMotor();
    }
}
