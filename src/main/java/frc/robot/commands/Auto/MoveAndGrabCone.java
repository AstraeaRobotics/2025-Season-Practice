// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.GrabberConstants.GrabberStates;
import frc.robot.Constants.WinchConstants.WinchStates;
import frc.robot.commands.Grabber.IntakeCone;
import frc.robot.commands.Grabber.SetGrabberState;
import frc.robot.commands.Winch.SetWinchState;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.TankDriveBase;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class MoveAndGrabCone extends SequentialCommandGroup {
  /** Creates a new MoveAndGrabCone. */
  public MoveAndGrabCone(GrabberSubsystem m_grabberSubsystem, ElevatorSubsystem m_elevatorSubsystem,double intakeSpeed1, double intakeSpeed2) {
   
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
 

      new ParallelCommandGroup(
        new SetWinchState(m_elevatorSubsystem, WinchStates.kBottom),
        new SetGrabberState(m_grabberSubsystem, GrabberStates.kBottom)),

      new ParallelDeadlineGroup(
        new WaitCommand(4),
        new IntakeCone(m_grabberSubsystem, intakeSpeed1)
        ),


      new ParallelCommandGroup(
        new SetWinchState(m_elevatorSubsystem, WinchStates.kTop),
        new SetGrabberState(m_grabberSubsystem, GrabberStates.kTop)),

      new ParallelDeadlineGroup(
        new WaitCommand(2),
        new IntakeCone(m_grabberSubsystem, intakeSpeed2)
        ));
  }
}
