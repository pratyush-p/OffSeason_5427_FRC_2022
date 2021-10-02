package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class SteelTalonsController extends WPI_TalonSRX
{
    boolean reverse;
    double bias;
    public SteelTalonsController(int deviceNumber, boolean reverse, double bias) 
    {
        super(deviceNumber);
        this.reverse =  reverse;
        this.bias = bias;
        setInverted(reverse);

    }

    @Override
    public void set(double speed)
    {
        super.set(speed * bias);    
    }
    
}
