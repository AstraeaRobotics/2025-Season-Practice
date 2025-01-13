// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.Constants.GrabberConstants;
import frc.robot.Constants.GrabberConstants.GrabberStates;
import frc.robot.Constants.WinchConstants.WinchStates;
import frc.robot.commands.Auto.MoveIntake;
import frc.robot.commands.Grabber.PivotGrabber;
import frc.robot.commands.Grabber.SetGrabberState;
import frc.robot.commands.Winch.SetWinchState;
import frc.robot.commands.Auto.DriveToDistance;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;
import frc.robot.subsystems.WinchSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TheAssignment extends SequentialCommandGroup {
  /** Creates a new IntakeCone. */
  public TheAssignment(GrabberSubsystem grabberSub, WinchSubsystem winchSub, TankDriveSubsystem driveSub, double driveSpeed, double distance, double intakeSpeed) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ParallelCommandGroup(
        new DriveToDistance(driveSub, driveSpeed, distance),
        new SetGrabberState(grabberSub, GrabberStates.kMid),
        new PivotGrabber(grabberSub, 0.1),
        new SetWinchState(winchSub, WinchStates.kBottom)
      ),

      new ParallelDeadlineGroup(
        new MoveIntake(grabberSub, intakeSpeed),
        new WaitCommand(1.5)
      ),

      new ParallelCommandGroup(
        new SetGrabberState(grabberSub, GrabberStates.kHigh),
        new PivotGrabber(grabberSub, 0.1),
        new SetWinchState(winchSub, WinchStates.kTop),
        new DriveToDistance(driveSub, -driveSpeed, distance)
      ),

      new ParallelDeadlineGroup(
        new MoveIntake(grabberSub, -intakeSpeed),
        new WaitCommand(1.5)
      )
    );
  }
}


