// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
import frc.robot.subsystems.TankDriveSub;

public class TankDriveCommand extends Command {
  /** Creates a new TankDriveCommand. */
  TankDriveSub driveSub;
  PS4Controller controller;
  Joystick joystick;

  public TankDriveCommand(TankDriveSub driveSub, PS4Controller controller,Joystick joystick ) {
    
    this.driveSub = driveSub;
    this.controller = controller;
    this.joystick = joystick;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double forward = -joystick.getY();
        double rotation = joystick.getX();
        if (Math.abs(forward) < 0.1 && Math.abs(rotation) < 0.1) {
            forward = -controller.getLeftY();
            rotation = controller.getRightX();

    }
    driveSub.tankDrive(forward, rotation);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }


}
