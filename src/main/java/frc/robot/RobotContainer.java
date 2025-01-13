// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Auto.DriveToDistance;
import frc.robot.subsystems.TankDriveSubsystem;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.WinchSubsystem;
import frc.robot.commands.Grabber.*;
import frc.robot.Constants.GrabberConstants.*;
import frc.robot.commands.JoystickDrive;
import frc.robot.commands.TheAssignment;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final PS4Controller m_controller = new PS4Controller(0);

  private final GrabberSubsystem m_grabbersubsystem = new GrabberSubsystem();
  private final TankDriveSubsystem m_TankDriveSubsystem = new TankDriveSubsystem();
  private final WinchSubsystem m_winchsubsystem = new WinchSubsystem();
  
  JoystickButton kTriangle = new JoystickButton(m_controller, PS4Controller.Button.kTriangle.value);
  JoystickButton kCross = new JoystickButton(m_controller, PS4Controller.Button.kCross.value);
  JoystickButton kSquare = new JoystickButton(m_controller, PS4Controller.Button.kSquare.value);
  JoystickButton kCircle = new JoystickButton(m_controller, PS4Controller.Button.kCircle.value);
  JoystickButton kL1 = new JoystickButton(m_controller, PS4Controller.Button.kL1.value);
  JoystickButton kL2 = new JoystickButton(m_controller, PS4Controller.Button.kL2.value);
  JoystickButton kL3 = new JoystickButton(m_controller, PS4Controller.Button.kL3.value);
  JoystickButton kR1 = new JoystickButton(m_controller, PS4Controller.Button.kR1.value);
  JoystickButton kR2 = new JoystickButton(m_controller, PS4Controller.Button.kR2.value);
  JoystickButton kR3 = new JoystickButton(m_controller, PS4Controller.Button.kR3.value);
  
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
      m_TankDriveSubsystem.setDefaultCommand(new JoystickDrive(m_TankDriveSubsystem, m_controller::getLeftY));
  
    // Configure the trigger bindings
    configureBindings();}
  

/**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
  
    kCircle.onTrue(new DriveToDistance(m_TankDriveSubsystem, 0.1, 0.1));
    kTriangle.onTrue(new SetGrabberState(m_grabbersubsystem, GrabberStates.kLow));
    kCross.onTrue(new SetGrabberState(m_grabbersubsystem, GrabberStates.kMid));
    kSquare.onTrue(new SetGrabberState(m_grabbersubsystem, GrabberStates.kHigh));

    kL1.onTrue(new PivotGrabber(m_grabbersubsystem, GrabberStates.kLow, 0.1));
    kL2.onTrue(new PivotGrabber(m_grabbersubsystem, GrabberStates.kMid, 0.1));
    kL3.onTrue(new PivotGrabber(m_grabbersubsystem, GrabberStates.kHigh, 0.1));

    kR3.onTrue(new TheAssignment(m_grabbersubsystem, m_TankDriveSubsystem, 0.1, 0.1, 0.1));
    

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  } }

