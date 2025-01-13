// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TankDriveBase;

public class DriveToDistance extends Command {
  TankDriveBase driveBase;
  double currentDistance;
  double targetDistance;
  double speed;

  PIDController drivePID; 

  /** Creates a new DriveToDistance. */
  public DriveToDistance(TankDriveBase driveBase, double targetDistance, double speed) {
    this.driveBase = driveBase;
    currentDistance = driveBase.getLeftEncoderPosition();
    this.targetDistance += currentDistance;
    this.speed = speed;
    
    drivePID = new PIDController(0.01, 0, 0);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveBase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivePID.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentDistance = driveBase.getLeftEncoderPosition();
    
    double error = targetDistance - currentDistance;
    double driveSpeed = drivePID.calculate(error);
    
    if (Math.abs(error) < 0.05) {
      driveBase.setMotors(0);
    } else {
      driveBase.setMotors(driveSpeed);
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
    currentDistance = driveBase.getLeftEncoderPosition();
    return((targetDistance - 0.05 <= currentDistance) && ( targetDistance + 0.05 >= currentDistance));
  }
}