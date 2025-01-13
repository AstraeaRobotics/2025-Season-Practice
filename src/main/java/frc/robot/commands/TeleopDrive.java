// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TankDriveBase;

public class TeleopDrive extends Command {
  TankDriveBase driveBase;
  DoubleSupplier xSpeed;
  DoubleSupplier ySpeed;
  /** Creates a new TankDrive. */
  public TeleopDrive(TankDriveBase driveBase, DoubleSupplier xSpeed, DoubleSupplier ySpeed) {
    this.driveBase = driveBase;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveBase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double forwardSpeed = xSpeed.getAsDouble();
    double turnSpeed = ySpeed.getAsDouble();

    double leftMotorSpeed = forwardSpeed + turnSpeed;
    double rightMotorSpeed = forwardSpeed - turnSpeed;

    if(Math.abs(leftMotorSpeed) >= 0.01 || Math.abs(rightMotorSpeed) >= 0.01) {
      driveBase.moveMotors(leftMotorSpeed, rightMotorSpeed);
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