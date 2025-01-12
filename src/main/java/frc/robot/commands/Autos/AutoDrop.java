// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.GrabberWinchConstants.GrabberWinchStates;
import frc.robot.commands.Grabber.SetGrabberState;
import frc.robot.commands.Grabber.SetIntake;
import frc.robot.commands.Winch.SetWinchState;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.WinchSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoDrop extends SequentialCommandGroup {
  /** Creates a new AutoDrop. */
  public AutoDrop(GrabberSubsystem grabber, WinchSubsystem winch) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ParallelDeadlineGroup(new WaitCommand(1), new SetWinchState(winch, GrabberWinchStates.kLowered)),
      new ParallelDeadlineGroup(new WaitCommand(1), new SetGrabberState(grabber, GrabberWinchStates.kLowered)),
      new ParallelDeadlineGroup(new WaitCommand(1), new SetIntake(grabber, -0.1)),
      new ParallelDeadlineGroup(new WaitCommand(1), new SetGrabberState(grabber, GrabberWinchStates.kRaised)),
      new ParallelDeadlineGroup(new WaitCommand(1), new SetWinchState(winch, GrabberWinchStates.kRaised))
    );
  }
}
