// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.MovePulley;
import frc.robot.commands.MoveShooter;
import frc.robot.commands.MoveTransport;
import frc.robot.commands.ShootAuto;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pulley;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);



  private SpeedController leftOne, leftTwo, rightOne, rightTwo;
  private SpeedControllerGroup left, right;
  private DifferentialDrive drive;
  private DriveTrain driveTrain;
  private static Joystick joy;
//drive train
  private Button intakeButton;
  private SpeedController intakeSpeedController;
  private static Intake intake;
//intake
  private Button pulleyButton;
  private SpeedController pulleySpeedController;
  private static Pulley pulley;
//pully

private Button transportButton;
private SpeedController transportSpeedController;
private static Transport transport;
private AnalogInput transportProximity;
//transport

private SpeedController elevatorLeft, elevatorRight;
private DigitalInput limitSwitchA, limitSwitchB;
private Encoder encoderOne, encoderTwo;
private static Elevator elevator;
private Button elevatorUp, elevatorDown;
//elevator
private Button autonButton;

private Button shootButton, shooterTeleopButton;
private SpeedController shooterLeft, shooterRight;
private static Shooter shooter;
//shooter




  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer()
   {
    // Configure the button bindings
    leftOne = new SteelTalonsController(0,false,1);
    leftTwo = new SteelTalonsController(1,false,1);
    rightOne = new SteelTalonsController(2,false,1);
    rightTwo = new SteelTalonsController(3,false,1);

    left = new SpeedControllerGroup(leftOne, leftTwo);
    right = new SpeedControllerGroup(rightOne, rightTwo);

    drive = new DifferentialDrive(left, right);

    driveTrain = new DriveTrain(left, right, drive);
    driveTrain.setDefaultCommand(new DriveWithJoystick());
    //drive train ^
    intakeSpeedController = new WPI_VictorSPX(Constants.INTAKE_MOTOR);
    intake = new Intake(intakeSpeedController);
    transportSpeedController = new WPI_VictorSPX(Constants.TRANSPORT_MOTOR);

    transport = new Transport(transportSpeedController, transportProximity);

    pulleySpeedController = new WPI_VictorSPX(Constants.PULLEY_MOTOR);
    pulley = new Pulley(pulleySpeedController);

    shooterLeft = new WPI_VictorSPX(Constants.SHOOTER_MOTOR_TOP);
    shooterRight = new WPI_VictorSPX(Constants.SHOOTER_MOTOR_BOTTOM);
    shooter = new Shooter(shooterLeft, shooterRight);

    elevatorLeft = new WPI_VictorSPX(Constants.ELEVATOR_LEFT_MOTOR);
    elevatorRight = new WPI_VictorSPX(Constants.ELEVATOR_RIGHT_MOTOR);
    limitSwitchA = new DigitalInput(Constants.ELEVATOR_LIMIT_SWITCHA);
    limitSwitchB = new DigitalInput(Constants.ELEVATOR_LIMIT_SWITCHB);

    encoderOne = new Encoder(Constants.ENCODER_ONE_SOURCEA, Constants.ENCODER_ONE_SOURCEB);
    encoderTwo = new Encoder(Constants.ENCODER_TWO_SOURCEA, Constants.ENCODER_TWO_SOURCEB);
    elevator = new Elevator(elevatorLeft, elevatorRight, limitSwitchA, limitSwitchB, encoderOne, encoderTwo);




    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() 
  {
    joy = new Joystick(0);
    intakeButton = new JoystickButton(joy, Constants.INTAKE_BUTTON);


    intakeButton.whileHeld(new MoveIntake(Constants.INTAKE_TELEOP_SPEED));


    transportButton = new JoystickButton(joy, Constants.TRANSPORT_BUTTON);

    transportButton.whileHeld(new MoveTransport(Constants.TRANSPORT_TELEOP_SPEED));



    pulleyButton = new JoystickButton(joy, Constants.PULLEY_BUTTON);
    pulleyButton.whileHeld(new MovePulley(Constants.PULLEY_TELEOP_SPEED));

  
  

    shootButton = new JoystickButton(joy, Constants.SHOOTER_BUTTON);
    shootButton.whileHeld(new MoveShooter(Constants.SHOOTER_TELEOP_SPEED, Constants.SHOOTER_TELEOP_SPEED));

    shooterTeleopButton = new JoystickButton(joy, Constants.SHOOTER_TELEOP);
    shooterTeleopButton.whileHeld(new MoveShooter(Constants.SHOOTER_TELEOP_SPEED, Constants.SHOOTER_TELEOP_SPEED));


    elevatorUp = new JoystickButton(joy, Constants.ELEVATOR_UP_BUTTON);
    elevatorDown = new JoystickButton(joy, Constants.ELEVATOR_DOWN_BUTTON);


    elevatorUp.whileHeld(new MoveElevator(Constants.ELEVATOR_SPEED, Constants.ELEVATOR_SPEED));

    elevatorDown.whileHeld(new MoveElevator(-1 * Constants.ELEVATOR_SPEED, -1 * Constants.ELEVATOR_SPEED));


    autonButton = new JoystickButton(joy, Constants.AUTON_BUTTON);
    autonButton.whileHeld(new ShootAuto(Constants.PULLEY_TELEOP_SPEED, Constants.SHOOTER_TELEOP_SPEED, Constants.SHOOTER_TELEOP_SPEED, Constants.TRANSPORT_TELEOP_SPEED));
  }


  public Command getAutonomousCommand() {

    return m_autoCommand;
  }


  public DriveTrain getDriveTrain()
  {
    return driveTrain;
  }


  public static Joystick getJoystick()
  {
    return joy;
  }

  public static Intake getIntake() {
    return intake;
  }

  public static Pulley getPulley() {
    return pulley;
  }

  public static Transport getTransport()
  {
    return transport;
  }

  public static Elevator getElevator() {
    return elevator;

  }

  public static Shooter getShooter() {
    return shooter;
  }
}










