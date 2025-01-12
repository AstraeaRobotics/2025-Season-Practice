// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Tankdrive.DriveToDistance;
import frc.robot.subsystems.GrabberSubsystem;
import frc.robot.subsystems.TankDrivebase;
import frc.robot.subsystems.WinchSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ConeAuto extends SequentialCommandGroup {
  /** Creates a new ConeAuto. */
  public ConeAuto(GrabberSubsystem grabberSubsystem, WinchSubsystem winchSubsystem, TankDrivebase drivebase) {
    addCommands(
      new DriveToDistance(drivebase, 10, 0.1, false),
      new AutoPickup(grabberSubsystem, winchSubsystem),
      new DriveToDistance(drivebase, 10, 0.1, true),
      new AutoDrop(grabberSubsystem, winchSubsystem)
    );
  }
}
