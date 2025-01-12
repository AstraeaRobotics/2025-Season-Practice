// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TankDriveBase;

public class TankDrive extends Command {
  TankDriveBase driveBase;
  DoubleSupplier yspeed;
  /** Creates a new TankDrive. */
  public TankDrive(TankDriveBase driveBase, DoubleSupplier yspeed) {
    this.yspeed = yspeed;
    this.driveBase = driveBase;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveBase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Math.abs(yspeed.getAsDouble())>=.01){
      driveBase.setMotors(yspeed.getAsDouble());
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveBase.setMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}