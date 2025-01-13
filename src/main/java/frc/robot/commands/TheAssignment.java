// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.Constants.GrabberConstants;
import frc.robot.commands.Auto.MoveIntake;
import frc.robot.commands.Grabber.PivotGrabber;
import frc.robot.commands.Auto.DriveToDistance;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.TankDriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TheAssignment extends SequentialCommandGroup {
  /** Creates a new IntakeCone. */
  public TheAssignment(GrabberSubsystem grabberSub, TankDriveSubsystem driveSub, double driveSpeed, double distance, double intakeSpeed) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new ParallelCommandGroup(
     new DriveToDistance(driveSub, driveSpeed, distance),
     new PivotGrabber(grabberSub, GrabberConstants.GrabberStates.kMid, 0.1)
     ),
    new MoveIntake(grabberSub, intakeSpeed),
     
    new DriveToDistance(driveSub, -driveSpeed, distance),

    new MoveIntake(grabberSub, -intakeSpeed));
  }
}


