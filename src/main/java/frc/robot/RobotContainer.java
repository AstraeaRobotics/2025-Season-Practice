// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.GrabberConstants.GrabberStates;
import frc.robot.Constants.WinchConstants.WinchStates;
import frc.robot.commands.TeleopDrive;
import frc.robot.commands.Grabber.PivotGrabber;
import frc.robot.commands.Grabber.SetGrabberState;
import frc.robot.commands.Winch.SetWinchState;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.TankDriveBase;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ElevatorSubsystem elevatorSub = new ElevatorSubsystem();
  private final TankDriveBase m_TankDriveBase = new TankDriveBase();
  private final GrabberSubsystem m_GrabberSubsystem = new GrabberSubsystem();

  private final PS4Controller m_Controller = new PS4Controller(0);
  private final JoystickButton kTriangle = new JoystickButton(m_Controller,PS4Controller.Button.kTriangle.value);
  private final JoystickButton kCircle = new JoystickButton(m_Controller,PS4Controller.Button.kCircle.value);
  private final JoystickButton kSquare = new JoystickButton(m_Controller,PS4Controller.Button.kSquare.value);
  private final JoystickButton kr1 = new JoystickButton(m_Controller,PS4Controller.Button.kR1.value);
  private final JoystickButton kl1 = new JoystickButton(m_Controller,PS4Controller.Button.kL1.value);
  private final JoystickButton kCross = new JoystickButton(m_Controller,PS4Controller.Button.kCross.value);
  private final JoystickButton kr2 = new JoystickButton(m_Controller,PS4Controller.Button.kR2.value);
  private final JoystickButton kl2 = new JoystickButton(m_Controller,PS4Controller.Button.kL2.value);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_TankDriveBase.setDefaultCommand(new TeleopDrive(m_TankDriveBase, m_Controller::getLeftY, m_Controller::getRightY));
    // Configure the trigger bindings
    configureBindings();
  }

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
    // cancelling on release.
    kTriangle.onTrue(new SetWinchState(elevatorSub, WinchStates.kBottom));
    kCircle.onTrue(new SetWinchState(elevatorSub, WinchStates.kMiddle));
    kSquare.onTrue(new SetWinchState(elevatorSub, WinchStates.kTop));
    kCross.onTrue(new SetGrabberState(m_GrabberSubsystem, GrabberStates.kBottom));
    kl2.onTrue(new SetGrabberState(m_GrabberSubsystem, GrabberStates.kMiddle));
    kr2.onTrue(new SetGrabberState(m_GrabberSubsystem, GrabberStates.kTop));
    kr1.whileTrue(new PivotGrabber(m_GrabberSubsystem, 0.1));
    kl1.whileTrue(new PivotGrabber(m_GrabberSubsystem, -0.1));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
