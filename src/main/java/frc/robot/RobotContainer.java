// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.PivotConstants.PivotSpeeds;
import frc.robot.Constants.PivotConstants.PivotStates;
import frc.robot.commands.Autos;
import frc.robot.commands.DriveToDistance;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.pivot.MoveToPivotState;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.PivotSubsystemNew;
import frc.robot.subsystems.TankDriveSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.pivot.SetPivotState;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final PS4Controller m_controller = new PS4Controller(0);
  
  JoystickButton kTriangle=new JoystickButton(m_controller, PS4Controller.Button.kTriangle.value);
  JoystickButton kCross=new JoystickButton(m_controller, PS4Controller.Button.kCross.value);
  JoystickButton kSquare=new JoystickButton(m_controller, PS4Controller.Button.kSquare.value);
  JoystickButton kCircle=new JoystickButton(m_controller, PS4Controller.Button.kCircle.value);
  PivotSubsystemNew pivotSub1;
  TankDriveSubsystem tankSub;
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
      
 pivotSub1.setDefaultCommand(new MoveToPivotState(pivotSub1, PivotSpeeds.kMedium));
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
    kTriangle.onTrue(new SetPivotState(pivotSub1, PivotStates.kground1));
    kSquare.onTrue(new SetPivotState(pivotSub1, PivotStates.kMid1));
    kCross.onTrue(new SetPivotState(pivotSub1, PivotStates.kHigh1));
    kCircle.onTrue(new DriveToDistance(tankSub, 0.1, 0.1));
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

