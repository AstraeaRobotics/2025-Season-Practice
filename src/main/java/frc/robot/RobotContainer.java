// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.GrabberWinchConstants.GrabberWinchStates;
import frc.robot.commands.Autos.AutoDrop;
import frc.robot.commands.Grabber.SetGrabberState;
import frc.robot.commands.Tankdrive.JoystickDrive;
import frc.robot.commands.Winch.SetWinchState;
import frc.robot.subsystems.GrabberSubsystem;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.subsystems.TankDrivebase;
import frc.robot.subsystems.WinchSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  private final GrabberSubsystem m_grabber = new GrabberSubsystem();
  private final WinchSubsystem m_winch = new WinchSubsystem();
  private final PS4Controller m_controller = new PS4Controller(Constants.OperatorConstants.kDriverControllerPort);

  private final TankDrivebase m_driveSubsystem = new TankDrivebase();
  JoystickButton kCircle = new JoystickButton(m_controller, PS4Controller.Button.kCircle.value);
  JoystickButton kTriangle = new JoystickButton(m_controller, PS4Controller.Button.kTriangle.value);
  JoystickButton kCross = new JoystickButton(m_controller, PS4Controller.Button.kCross.value);
  JoystickButton kSquare = new JoystickButton(m_controller, PS4Controller.Button.kSquare.value);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    m_driveSubsystem.setDefaultCommand(new JoystickDrive(m_driveSubsystem, m_controller::getLeftY, m_controller::getLeftX));
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

    kCircle.onTrue(new SetWinchState(m_winch, GrabberWinchStates.kLowered));
    kTriangle.onTrue(new SetWinchState(m_winch, GrabberWinchStates.kGround));
    kCross.onTrue(new SetGrabberState(m_grabber, GrabberWinchStates.kLowered));
    kSquare.onTrue(new SetGrabberState(m_grabber, GrabberWinchStates.kRaised));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new AutoDrop(m_grabber, m_winch);
  }
}
